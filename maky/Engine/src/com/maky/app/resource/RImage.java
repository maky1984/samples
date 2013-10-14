package com.maky.app.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.maky.environment.AppGraphics;
import com.maky.environment.AppImage;
import com.maky.environment.GraphicsConfig;
import com.maky.util.log.Logger;

public class RImage extends Resource {

	private AppImage image;

	public final String DEFAULT_FORMAT = "png";
	private final int DEFAULT_WIDTH = GraphicsConfig.getScaledX(30);
	private final int DEFAULT_HEIGHT = GraphicsConfig.getScaledY(30);
	private char[] CHARS_NO_IMAGE = { 'N', 'o', ' ', 'i', 'm', 'a', 'g', 'e' };

	public RImage(int id) {
		super(id, TYPE_IMAGE);
	}

	private boolean checkFormat(String formatName) {
		String[] formats = AppImage.getImageWriterFormatNames();
		for (int i = 0; i < formats.length; i++) {
			if (formatName.equals(formats[i])) {
				return true;
			}
		}
		return false;
	}

	public AppImage getImage() {
		if (image == null) {
			try {
				load();
			} catch (IOException ex) {
				image = new AppImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, AppImage.TYPE_INT_RGB);
				char[] text = (String.valueOf(getId()) + String.valueOf(CHARS_NO_IMAGE)).toCharArray();
				image.getGraphics().drawChars(text, 1, text.length - 1, 5, 20);
			}
			Logger.getInstance(this).logError(" No image found in RImage resource. Default image created.");
		}
		return image;
	}

	public void load(InputStream in) throws IOException {
		image = AppImage.read(in);
	}

	public void save(OutputStream out) throws IOException {
		save(out, DEFAULT_FORMAT);
	}

	public void save(OutputStream out, String formatName) throws IOException {
		if (checkFormat(formatName)) {
			AppImage.write(image, formatName, out);
		} else {
			throw new IOException("Unknown image format");
		}
	}
	
	public void paint(AppGraphics gr, int x, int y) {
		gr.drawImage(getImage(), x, y, null);
	}

}
