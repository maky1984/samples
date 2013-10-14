package com.maky.environment;

import java.awt.Canvas;
import java.awt.Graphics;

import com.maky.util.timing.IUpdateble;

public class AppCanvas extends Canvas implements IUpdateble {

	private static final long serialVersionUID = 1L;
	private final Painter painter;
	private AppImage bufferImage;
	private AppGraphics bufferGraphics;
	private AppGraphics graphics;

	public AppCanvas(Painter painter) {
		this.painter = painter;
		graphics = new AppGraphics();
	}

	public void update(Graphics g) {
		graphics.setGraphics(g);
		paint(graphics);
	}

	public void paint(Graphics g) {

	}

	public void paint(AppGraphics g) {
		if (bufferImage == null) {
			bufferImage = new AppImage(createImage(GraphicsConfig.getCanvasWidth(), GraphicsConfig.getCanvasHeight()));
			bufferGraphics = bufferImage.getGraphics();
		}
		// do normal redraw
		painter.paint(bufferGraphics);
		// transfer off-screen image to window
		g.drawImage(bufferImage, 0, 0, this);
	}

	public void tick() {
		repaint();
	}

	public Painter getPainter() {
		return painter;
	}

	public AppFontMetrics getFontMetrics(AppFont font) {
		return new AppFontMetrics(super.getFontMetrics(font.getFont()));
	}

}
