package com.maky.arkanoid;

public class Util {

	private final int origWidth = 800;
	private final int origHeight = 480;
	
	private final float glWidth = 2;
	private final float glHeight = 2;

	private final float width;
	private final float height;

	private final float xScale;
	private final float yScale;

	public Util() {
		width = glWidth;
		height = glHeight;
		xScale = width / origWidth;
		yScale = height / origHeight;
	}
	
	public Util(float w, float h) {
		width = w;
		height = h;
		xScale = width / origWidth;
		yScale = height / origHeight;
	}
	
	// -----------------------------------------
	// Values that returns pixels
	// -----------------------------------------
	
	public int getWidth() {
		return origWidth;
	}
	
	public int getHeight() {
		return origHeight;
	}
	
	public int getSX_PX(float x) {
		return (int)((x + 1) / xScale);
	}
	
	public int getSY_PX(float y) {
		return (int)((y + 1) / yScale);
	}

	// ------------------------------------------
	// Values that returns float values
	// ------------------------------------------
	
	public float getSX_GL(float x) {
		return xScale * x - 1;
	}

	public float getSY_GL(float y) {
		return yScale * y - 1;
	}
}
