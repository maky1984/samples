package com.maky.environment;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;

public class AppGraphics {

	private Graphics graphics;

	public AppGraphics() {
	}
	
	public AppGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public boolean drawImage(AppImage arg0, int x, int y, Object observer) {
		return graphics.drawImage(arg0.getImage(), x, y, (ImageObserver)observer);
	}
	
	public boolean drawImage(AppImage arg0, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			Object observer) {
		return graphics.drawImage(arg0.getImage(), dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, (ImageObserver) observer);
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		graphics.drawLine(x1, y1, x2, y2);
	}

	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoint) {
		graphics.drawPolyline(xPoints, yPoints, nPoint);
	}

	public void drawString(String str, int x, int y) {
		graphics.drawString(str, x, y);
	}

	public void fillRect(int x, int y, int width, int height) {
		graphics.fillRect(x, y, width, height);
	}

	public AppColor getColor() {
		return new AppColor(graphics.getColor());
	}

	public AppFont getFont() {
		return new AppFont(graphics.getFont());
	}

	public FontMetrics getFontMetrics(Font arg0) {
		return graphics.getFontMetrics(arg0);
	}

	public void setColor(AppColor arg0) {
		graphics.setColor(arg0.getColor());
	}

	public void setFont(AppFont font) {
		graphics.setFont(font.getFont());
	}

	public void translate(int arg0, int arg1) {
		graphics.translate(arg0, arg1);
	}

	public void drawChars(char[] data, int offset, int length, int x, int y) {
		graphics.drawChars(data, offset, length, x, y);
	}

	public void drawRenderedImage(AppImage image, AffineTransform at) {
		((Graphics2D)graphics).drawRenderedImage((RenderedImage)(image.getImage()), at);
	}

	public void clearWithColor(AppColor color, int width, int height) {
		((Graphics2D)graphics).setBackground(color.getColor());
		graphics.clearRect(0, 0, width, height);
	}
	
	public void setAlphaComposite(float alpha) {
		((Graphics2D)graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}
}
