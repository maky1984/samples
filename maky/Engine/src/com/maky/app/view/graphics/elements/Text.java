package com.maky.app.view.graphics.elements;

import com.maky.app.view.View;
import com.maky.environment.AppFont;
import com.maky.environment.AppFontMetrics;
import com.maky.environment.AppGraphics;

public class Text extends GraphicElement {

	private final String text;
	private final AppFont font;
	private final int width;
	private final int height;

	public Text(String text, AppFont font, View view) {
		super(view);
		this.text = text;
		this.font = font;
		AppFontMetrics fontMetrics = (view.getCanvas()).getFontMetrics(font);
		height = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
		width = fontMetrics.stringWidth(text);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void paint(AppGraphics gr) {
		gr.setFont(font);
		gr.drawString(text, 0, 0);
	}

	public String getString() {
		return text;
	}
	
	public AppFont getFont() {
		return font;
	}

}
