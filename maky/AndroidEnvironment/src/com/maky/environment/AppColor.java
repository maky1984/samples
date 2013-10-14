package com.maky.environment;

import android.graphics.Color;

public class AppColor {

	public static final AppColor RED = new AppColor(Color.RED);
	public static final AppColor BLACK = new AppColor(Color.BLACK);
	public static final AppColor WHITE = new AppColor(Color.WHITE);
	public static final AppColor GREEN = new AppColor(Color.GREEN);
	public static final AppColor BLUE = new AppColor(Color.BLUE);
	public static final AppColor YELLOW = new AppColor(Color.YELLOW);
	public static final AppColor ORANGE = new AppColor(Color.CYAN);
	public static final AppColor PINK = new AppColor(Color.MAGENTA);
	public static final AppColor TRANSP = new AppColor(Color.TRANSPARENT);

	private final int color;

	public AppColor(int color) {
		this.color = color;
	}

	public AppColor(int r, int g, int b) {
		color = Color.rgb(r, g, b);
	}

	public int getColor() {
		return color;
	}

	public boolean equals(Object obj) {
		return color == ((AppColor) obj).color;
	}

	public int hashCode() {
		return color;
	}
}
