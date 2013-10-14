package com.maky.environment;

import java.awt.FontMetrics;

public class AppFontMetrics {

	private final FontMetrics fontMetrics;

	public AppFontMetrics(FontMetrics fontMetrics) {
		this.fontMetrics = fontMetrics;
	}

	public int getMaxDescent() {
		return fontMetrics.getMaxDescent();
	}

	public int getMaxAscent() {
		return fontMetrics.getMaxAscent();
	}
	
	public int stringWidth(String str) {
		return fontMetrics.stringWidth(str);
	}
}
