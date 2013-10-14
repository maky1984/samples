package com.maky.app.view.graphics.elements;

import com.maky.environment.AppGraphics;
import com.maky.environment.AppImage;
import com.maky.environment.IPaintable;

public class Font implements IPaintable {

	private int cursor;
	private AppImage fontImage;
	private int delta;

	public Font(AppImage fontImage, int length) {
		this.fontImage = fontImage;
		this.delta = fontImage.getWidth() / length;
	}

	public void setCursor(char c) {
		switch (c) {
		case '1':
			cursor = 0;
			break;
		case 'a':
			cursor = 10;
			break;
		}
	}

	@Override
	public void paint(AppGraphics gr) {
		gr.drawImage(fontImage, 0, 0, delta, fontImage.getHeight(), delta * cursor, 0, (delta + 1) * cursor,
				fontImage.getHeight(), null);
	}

}
