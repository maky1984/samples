package com.maky.app.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import com.maky.util.RandomAccessFileInputStream;
import com.maky.util.log.Logger;

public class ResourceManager {

	private static final String RESOURCE_DIRECTORY = "res/";
	private static final String RESOURCE_DESCRIPTOR_FILENAME = "description.dat";
	private static final String RESOURCE_DESCRIPTOR_HEADER = "# Description of the resources\r\n"
			+ "# Format:\r\n"
			+ "# TAB used as delimiter\r\n"
			+ "#<id>	<type>	<store_type>	<store_value>	<position>\r\n"
			+ "#\r\n"
			+ "# id - unique id of the resource\r\n"
			+ "#\r\n"
			+ "# type - one of\r\n"
			+ "#		byte array 		- 1\r\n"
			+ "#		image			- 2\r\n"
			+ "#		sound			- 3\r\n"
			+ "#		string array	- 4\r\n"
			+ "#		text			- 5\r\n"
			+ "#		bytes array		- 6\r\n"
			+ "#		lines game map	- 7\r\n"
			+ "#\r\n"
			+ "# store_type - one of\r\n"
			+ "#		f			- file\r\n"
			+ "#		u			- url\r\n"
			+ "#\r\n"
			+ "# store_value\r\n"
			+ "#	for f store_type - filename\r\n"
			+ "#	for u store_type - url string\r\n"
			+ "#\r\n"
			+ "# position - position in file (or downloaded file)\r\n"
			+ "#\r\n"
			+ "# Examples:\r\n"
			+ "#1	1	f	byteArray.dat	0\r\n"
			+ "#2	4	u	http://test/abs.dat	1000\r\n"
			+ "#\r\n"
			+ "# This file should be generated by the ResourceEditor.class\r\n";
	private static final String COMMENT_PREFIX = "#";
	private static final int RESOURCE_DESCRIPTOR_FORMAT_COUNTER = 5;

	// Directory for the resource descriptor file
	private String dir;

	// Map<resource id, Resource>
	private Map resources;

	private static ResourceManager instance;
	private Logger logger;

	private ResourceManager(String dir) {
		logger = Logger.getInstance(this);
		this.dir = dir;
		this.resources = new HashMap();
	}
	
	public static ResourceManager getInstance() {
		if (instance == null) {
			instance = new ResourceManager(RESOURCE_DIRECTORY);
			instance.startResourceManager();
		}
		return instance;
	}

	private void createResourceDescriptor(File file) throws IOException {
		if (file.exists()) {
			logger.logError(" ERROR. File already exists");
			return;
		}
		file.createNewFile();
		PrintStream out = new PrintStream(new FileOutputStream(file));
		out.println(RESOURCE_DESCRIPTOR_HEADER);
		out.flush();
		out.close();
	}

	private void loadResourceDescriptor() throws IOException {
		RandomAccessFile file = new RandomAccessFile(dir + RESOURCE_DESCRIPTOR_FILENAME, "rw");
		RandomAccessFileInputStream in = new RandomAccessFileInputStream(file);
		if (in.available() == 0) {
			logger.logError(" ERROR resource descriptor file is empty.");
			return;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		if (!reader.ready()) {
			logger.logError(" ERROR buffered reader is not ready. ");
			return;
		}
		while (reader.ready()) {
			String line = reader.readLine();
			if (line.startsWith(COMMENT_PREFIX) || line.trim().equals("")) {
				logger.logInfo(" Read ... commented or empty line skipped: " + line);
			} else {
				// Parse resource descriptor
				String[] tokens = line.split("[\\t\\#]");
				if (tokens.length < RESOURCE_DESCRIPTOR_FORMAT_COUNTER) {
					logger.logError(" ERROR parameters in line are not accepted. Line: " + line);
				} else {
					logger.logInfo(" Accepting the resource descriptor: " + line);
					int id = Integer.parseInt(tokens[0].trim());
					int type = Integer.parseInt(tokens[1].trim());
					int storeType;
					char storeTypeChar = tokens[2].trim().charAt(0);
					if (storeTypeChar == 'f') {
						storeType = Resource.STORE_TYPE_FILE;
					} else if (storeTypeChar == 'u') {
						storeType = Resource.STORE_TYPE_URL;
					} else {
						logger.logError(" ERROR store type is unknown: " + tokens[2] + " descriptor skipped.");
						continue;
					}
					String store = dir + tokens[3].trim();
					long position = Long.parseLong(tokens[4].trim());
					// Create resource by descriptor
					Resource resource = createResource(id, type, storeType, store, position);
					if (resource == null) {
						logger.logError(" ERROR Cant create resource id = " + id + " type = " + type + " store = "
								+ store);
						continue;
					}
					resources.put(new Integer(id), resource);
				}
			}
		}
	}
	
	private void saveResourceDescriptor() {
		// TODO: implement it
	}

	private Resource createResource(int id, int type, int storeType, String store, long position) {
		Resource resource;
		switch (type) {
		case Resource.TYPE_BYTES_ARRAY:
			resource = new RBytesArray(id);
			break;
		case Resource.TYPE_IMAGE:
			resource = new RImage(id);
			break;
		case Resource.TYPE_SOUND:
			// TODO: Add sound resource implementation
			return null;
		case Resource.TYPE_STRINGS_ARRAY:
			resource = new RStringsArray(id);
			break;
		case Resource.TYPE_TEXT:
			// TODO: Add text resources implementation
			return null;
		case Resource.TYPE_BYTES_MATRIX:
			resource = new RBytesMatrix(id);
			break;
		case Resource.TYPE_GAME_LINES_MAP:
			resource = new RLinesMap(id);
			break;
		default:
			logger.logError(" Unknown resource type = " + type + " for id = " + id + " descriptor skipped");
			return null;
		}
		resource.setSource(storeType, store, position);
		return resource;
	}
	
	/**
	 * Add resource to resource manager without saving to file
	 * @param resource
	 */
	public void addResource(Resource resource) {
		resources.put(new Integer(resource.getId()), resource);
	}
	
	/**
	 * Add resource to the resource manager and save to the file for the future use
	 * @param resource
	 * @param storeType
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public Resource addAndSaveResource(Resource resource, int storeType, String filename) throws IOException {
		resource.setSource(storeType, filename, 0);
		resources.put(new Integer(resource.getId()), resource);

		File file = new File(RESOURCE_DIRECTORY + filename);
		if (file.exists()) {
			logger.logError(" ERROR. File already exists");
			return null;
		}
		file.createNewFile();
		OutputStream out = new FileOutputStream(file);
		resource.save(out);
		out.flush();
		out.close();
		saveResourceDescriptor();
		return resource;
	}

	public RImage getImageResource(int id) {
		return (RImage)getResource(id);
	}
	
	public Resource getResource(int id) {
		Resource resource = (Resource) resources.get(new Integer(id));
		try {
			resource.load();
		} catch (Exception ex) {
			logger.logException(ex);
			logger.logError(" ERROR load resource.");			
		}
		if (resource == null) {
			logger.logError(" ERROR Cant find resource id = " + id);
		}
		return resource;
	}

	private void startResourceManager() {
		logger.logDebug(" Resource directory: " + dir);
		File file = new File(dir + RESOURCE_DESCRIPTOR_FILENAME);
		if (!file.exists()) {
			try {
				createResourceDescriptor(file);
			} catch (IOException ex) {
				logger.logException(ex);
				logger.logError(" ERROR creating resource descriptor. END.");
				return;
			}
		}
		try {
			loadResourceDescriptor();
		} catch (Exception ex) {
			logger.logException(ex);
			logger.logError(" ERROR Cant load resource descriptor. END.");
			return;
		}
	}

	public static void main(String[] args) {
		String dir = RESOURCE_DIRECTORY;
		if (args.length > 1) {
			dir = args[1];
		}
		new ResourceManager(dir).startResourceManager();
	}
}
