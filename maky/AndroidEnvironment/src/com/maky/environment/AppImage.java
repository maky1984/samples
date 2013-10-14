package com.maky.environment;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class AppImage {

	private static Bitmap.Config[] configs = { Bitmap.Config.RGB_565, Bitmap.Config.ALPHA_8, Bitmap.Config.ARGB_4444 };

	public static final int TYPE_INT_RGB = 0;
	public static final int TYPE_INT_ARGB = 1;
	public static final int TYPE_INT_ARGB_PRE = 2;

	private final Bitmap image;

	public AppImage(Bitmap image) {
		this.image = image;
	}

	public AppImage(int width, int height, int imageType) {
		image = Bitmap.createBitmap(GraphicsConfig.getScaledX(width), GraphicsConfig.getScaledY(height),
				configs[imageType]);
	}

	public Bitmap getImage() {
		return image;
	}

	public AppGraphics getGraphics() {
		return new AppGraphics();
	}

	public static AppImage read(InputStream in) {
		return scale(new AppImage(BitmapFactory.decodeStream(in)));
	}

	public AppImage getScaled(float scaledWidth, float scaledHeight) {
		return scale(this, scaledWidth, scaledHeight);
	}

	/**
	 * Scale image using default scaling parameters
	 * 
	 * @param image
	 * @return
	 */
	private static AppImage scale(AppImage image) {
		return scale(image, GraphicsConfig.getScaleWidth(), GraphicsConfig.getScaleHeight());
	}

	private static AppImage scale(AppImage image, float scaledWidth, float scaledHeight) {
		AppImage newImage = new AppImage(image.getWidth(), image.getHeight(), TYPE_INT_ARGB);
		AppGraphics g = newImage.getGraphics();
		Matrix matrix = new Matrix();
		matrix.setScale(scaledWidth, scaledHeight);
		g.drawRenderedImage(image, matrix);
		return newImage;
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}
}
