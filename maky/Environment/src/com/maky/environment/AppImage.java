package com.maky.environment;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class AppImage {

	public static final int TYPE_INT_RGB = BufferedImage.TYPE_INT_RGB;
	public static final int TYPE_INT_ARGB = BufferedImage.TYPE_INT_ARGB;
	public static final int TYPE_INT_ARGB_PRE = BufferedImage.TYPE_INT_ARGB_PRE;
	public static final int TYPE_INT_BGR = BufferedImage.TYPE_INT_BGR;

	private final Image image;

	public AppImage(Image image) {
		this.image = image;
	}

	public AppImage(int width, int height, int imageType) {
		image = new BufferedImage(GraphicsConfig.getScaledX(width), GraphicsConfig.getScaledY(height), imageType);
	}

	public Image getImage() {
		return image;
	}

	public AppGraphics getGraphics() {
		return new AppGraphics(image.getGraphics());
	}

	public static AppImage read(InputStream in) throws IOException {
		return scale(new AppImage(ImageIO.read(in)));
	}

	public AppImage getScaled(float scaledWidth, float scaledHeight) {
		return scale(this, scaledWidth, scaledHeight);
	}

	/**
	 * Scale image using default scaling parameters
	 * @param image
	 * @return
	 */
	private static AppImage scale(AppImage image) {
		return scale(image, GraphicsConfig.getScaleWidth(), GraphicsConfig.getScaleHeight());
	}
	
	private static AppImage scale(AppImage image, float scaledWidth, float scaledHeight) {
		AppImage newImage = new AppImage(image.getWidth(), image.getHeight(), TYPE_INT_ARGB);
		AppGraphics g = newImage.getGraphics();
		AffineTransform at = AffineTransform.getScaleInstance(scaledWidth, scaledHeight);
		g.drawRenderedImage(image, at);
		return newImage;
	}

	public static void write(AppImage image, String formatName, OutputStream out) throws IOException {
		Image img = image.getImage();
		if (img instanceof RenderedImage) {
			ImageIO.write((RenderedImage) img, formatName, out);
		} else {
			throw new IOException("Unsupported image object:" + img);
		}
	}

	public static String[] getImageWriterFormatNames() {
		return ImageIO.getWriterFormatNames();
	}

	public int getWidth(Object object) {
		return image.getWidth((ImageObserver) object);
	}

	public int getWidth() {
		return image.getWidth(null);
	}

	public int getHeight(Object object) {
		return image.getHeight((ImageObserver) object);
	}

	public int getHeight() {
		return image.getHeight(null);
	}
}
