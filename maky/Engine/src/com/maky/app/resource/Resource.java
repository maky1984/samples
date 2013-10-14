package com.maky.app.resource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import com.maky.util.RandomAccessFileOutputStream;
import com.maky.util.log.Logger;

abstract public class Resource {

	private int id;
	private int type;
	private int storeType;
	private String store;
	private long position;
	private boolean isLoaded;

	public static final int STORE_TYPE_FILE = 1;
	public static final int STORE_TYPE_URL = 2;

	public static final int TYPE_BYTES_ARRAY = 1;
	public static final int TYPE_IMAGE = 2;
	public static final int TYPE_SOUND = 3;
	public static final int TYPE_STRINGS_ARRAY = 4;
	public static final int TYPE_TEXT = 5;
	public static final int TYPE_BYTES_MATRIX = 6;
	public static final int TYPE_GAME_LINES_MAP = 7;

	protected Resource(int id, int type) {
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public void setSource(int storeType, String store, long position) {
		this.storeType = storeType;
		this.store = store;
		this.position = position;
	}

	/**
	 * Load resource data from the store at the position
	 * 
	 * @throws IOException
	 */
	public void load() throws IOException {
		if (isLoaded) return;
		switch (storeType) {
		case STORE_TYPE_FILE:
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File(store)));
			input.skip(position);
			load(input);
			isLoaded = true;
			break;
		case STORE_TYPE_URL:
			// TODO: Load resources from URL
			break;
		default:
			Logger.getInstance(this).logError(
					" ERROR Cant load resource id = " + id + ". Unknown store type " + storeType);
		}
	}

	/**
	 * Save resource data to the store at the position
	 * 
	 * @param position
	 * @throws IOException
	 */
	public void save() throws IOException {
		switch (storeType) {
		case STORE_TYPE_FILE:
			RandomAccessFile file = new RandomAccessFile(store, "rw");
			RandomAccessFileOutputStream out = new RandomAccessFileOutputStream(file);
			file.seek(position);
			save(out);
			break;
		case STORE_TYPE_URL:
			// TODO: Save resource to URL. Maybe upload mechanism. LOW PRIORITY
			break;
		default:
			Logger.getInstance(this).logError(
					" ERROR Cant save resource id = " + id + ". Unknown store type " + storeType);
		}
	}

	abstract public void load(InputStream in) throws IOException;

	abstract public void save(OutputStream out) throws IOException;
}
