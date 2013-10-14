package com.maky.environment;

public class AppFont {

	public static final int BOLD = 0;
	public static final int ITALIC = 1;
	public static final int PLAIN = 2;

	private String name;
	private int style;
	private int size;
	
	public AppFont() {
	}

	public AppFont(String name, int style, int size) {
		this.name = name;
		this.style = style;
		this.size = size;
	}

	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getStyle() {
		return style;
	}
}
