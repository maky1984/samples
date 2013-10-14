package com.maky.environment;

public class GraphicsConfig {

	private static final int DEFAULT_CANVAS_WIDTH = 800;
	private static final int DEFAULT_CANVAS_HEIGHT = 480;

	private static int canvasWidth;
	private static int canvasHeight;

	private static float scaleWidth;
	private static float scaleHeight;

	private static AppFont defaultFont;

	public static final int FRAMES_PER_SECOND = 25;
	public static final int TICKLER_PERIOD = 1000 / FRAMES_PER_SECOND;

	static {
		canvasWidth = DEFAULT_CANVAS_WIDTH;
		canvasHeight = DEFAULT_CANVAS_HEIGHT;
		defaultFont = new AppFont("Default", AppFont.BOLD, 20);
		scaleWidth = 1;
		scaleHeight = 1;
	}

	public static void configure(int canvasWidth, int canvasHeight) {
		GraphicsConfig.canvasHeight = canvasHeight;
		GraphicsConfig.canvasWidth = canvasWidth;
		GraphicsConfig.scaleWidth = ((float) canvasWidth) / DEFAULT_CANVAS_WIDTH;
		GraphicsConfig.scaleHeight = ((float) canvasHeight) / DEFAULT_CANVAS_HEIGHT;
	}

	public static int getCanvasWidth() {
		return canvasWidth;
	}

	public static int getCanvasHeight() {
		return canvasHeight;
	}

	static final float getScaleWidth() {
		return scaleWidth;
	}

	static final float getScaleHeight() {
		return scaleHeight;
	}

	public static int getCanvasCenterX() {
		return getCanvasWidth() / 2;
	}

	public static int getCanvasCenterY() {
		return getCanvasHeight() / 2;
	}

	public static int getCenteredTextX(int width) {
		return getCanvasCenterX() - width / 2;
	}

	public static int getCenteredTextY(int height) {
		return getCanvasCenterY() - height / 2;
	}

	public static int getCenterX(int bodyWidth) {
		return getCanvasCenterX() - (bodyWidth / 2);
	}

	public static int getCenterY(int bodyHeight) {
		return getCanvasCenterY() - (bodyHeight / 2);
	}
	
	public static int getScaledX(int x) {
		return (int)(x * scaleWidth);
	}
	
	public static int getScaledY(int y) {
		return (int)(y * scaleHeight);
	}

	public static AppFont getDefaultFont() {
		return defaultFont;
	}
}
