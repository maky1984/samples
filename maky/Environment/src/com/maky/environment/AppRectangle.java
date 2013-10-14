package com.maky.environment;

import java.awt.Rectangle;

public class AppRectangle {
	private Rectangle rect;

	public AppRectangle(int x, int y, int w, int h) {
		rect = new Rectangle(x, y, w, h);
	}

	public boolean contains(int x, int y) {
		return rect.contains(x, y);
	}
}
