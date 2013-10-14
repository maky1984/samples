package com.maky.environment;

import java.awt.Color;

public class AppColor {
	
	public static final AppColor RED = new AppColor(Color.RED); 
	public static final AppColor BLACK = new AppColor(Color.BLACK);
	public static final AppColor WHITE = new AppColor(Color.WHITE);
	public static final AppColor GREEN = new AppColor(Color.GREEN);
	public static final AppColor BLUE = new AppColor(Color.BLUE);
	public static final AppColor YELLOW = new AppColor(Color.YELLOW);
	public static final AppColor ORANGE = new AppColor(Color.ORANGE);
	public static final AppColor PINK = new AppColor(Color.PINK);
	public static final AppColor TRANSP = new AppColor(new Color(0.0f, 0.0f, 0.0f, 0.0f));

	private final Color color;
	
	public AppColor(Color color) {
		this.color = color;
	}
	
	public AppColor(int r, int g, int b) {
		color = new Color(r,g,b);
	}

	public Color getColor() {
		return color;
	}
	
	public boolean equals(Object obj) {
		return color.equals(((AppColor)obj).getColor());
	}

	public int hashCode() {
		return color.hashCode();
	}
}
