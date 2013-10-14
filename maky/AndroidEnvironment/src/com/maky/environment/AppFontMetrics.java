package com.maky.environment;

import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;;

public class AppFontMetrics {

	private final FontMetricsInt fontMetrics;
	private final Paint paint;

	public AppFontMetrics(FontMetricsInt fontMetrics, Paint paint) {
		this.fontMetrics = fontMetrics;
		this.paint = paint;
	}

	public int getMaxDescent() {
		return fontMetrics.descent;
	}

	public int getMaxAscent() {
		return fontMetrics.ascent;
	}
	
	public int stringWidth(String str) {
		return (int)paint.measureText(str);
	}
}
