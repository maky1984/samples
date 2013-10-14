package com.maky.demo.graph;

import android.graphics.Rect;

public class Util {

	public int ORIG_WIDTH = 800;
	public int ORIG_HEIGHT = 480;

	private final int width;
	private final int height;

	private final float xScale;
	private final float yScale;

	public Util(int w, int h) {
		width = w;
		height = h;
		xScale = ((float) w) / (float) ORIG_WIDTH;
		yScale = ((float) h) / (float) ORIG_HEIGHT;
	}

	public int getSX(float x) {
		return (int)(xScale * x);
	}

	public Rect getSRect(Rect rect) {
		return new Rect(getSX(rect.left), getSY(rect.top), getSX(rect.right), getSY(rect.bottom));
	}

	public int getSY(int y) {
		return (int)(yScale * y);
	}

}
