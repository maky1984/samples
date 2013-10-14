package com.maky.environment;

import java.awt.Font;

public class AppFont {
	
	public static final int BOLD = Font.BOLD;
	public static final int ITALIC = Font.ITALIC;
	public static final int PLAIN = Font.PLAIN;

	private final Font font;
	
	public AppFont(Font font) {
		this.font = font;
	}
	
	public AppFont(String name, int style, int size) {
		font = new Font(name, style, size);
	}
	
	public Font getFont() {
		return font;
	}
}
