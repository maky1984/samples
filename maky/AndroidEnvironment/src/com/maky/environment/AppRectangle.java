package com.maky.environment;

import android.graphics.Rect;

public class AppRectangle {
	private Rect rect;

	public AppRectangle(int x, int y, int w, int h) {
		rect = new Rect(x, y, w, h);
	}

	public boolean contains(int x, int y) {
		return rect.contains(x, y);
	}
}
