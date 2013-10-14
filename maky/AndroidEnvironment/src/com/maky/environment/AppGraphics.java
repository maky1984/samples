package com.maky.environment;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;

public class AppGraphics {

	private Canvas graphics;
	private Paint paint;

	public AppGraphics() {
		paint = new Paint();
	}

	public AppGraphics(Canvas graphics) {
		this();
		this.graphics = graphics;
	}
	
	public void setGraphics(Canvas graphics) {
		this.graphics = graphics;
	}

	public boolean drawImage(AppImage arg0, int x, int y, Object observer) {
		graphics.drawBitmap(arg0.getImage(), x, y, paint);
		return true;
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		graphics.drawLine(x1, y1, x2, y2, paint);
	}

	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoint) {
		for (int i=0;i<nPoint;i++) {
			graphics.drawLine(xPoints[i], yPoints[i], xPoints[i+1], yPoints[i+1], paint);
		}
	}

	public void drawString(String str, int x, int y) {
		graphics.drawText(str, x, y, paint);
	}

	public void fillRect(int x, int y, int width, int height) {
		graphics.drawRect(x, y, width, height, paint);
	}

	public AppColor getColor() {
		return new AppColor(paint.getColor());
	}

	public AppFont getFont() {
		return new AppFont();
	}

	public FontMetrics getFontMetrics() {
		return paint.getFontMetrics();
	}

	public void setColor(AppColor arg0) {
		paint.setColor(arg0.getColor());
	}

	public void setFont(AppFont font) {
		
	}

	public void translate(int arg0, int arg1) {
		graphics.translate(arg0, arg1);
	}

	public void drawChars(char[] data, int offset, int length, int x, int y) {
		graphics.drawText(data, offset, length, x, y, paint);
	}

	public void drawRenderedImage(AppImage image, Matrix matrix) {
		graphics.drawBitmap(image.getImage(), matrix, paint);
	}

	public void clearWithColor(AppColor color, int width, int height) {
		graphics.clipRect(0, 0, width, height);
		graphics.drawColor(color.getColor());
	}

	public void setAlphaComposite(float alpha) {
		int a = Math.round((255 * alpha));
		paint.setAlpha(a);
	}
}
