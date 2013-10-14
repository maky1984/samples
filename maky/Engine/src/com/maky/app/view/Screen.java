package com.maky.app.view;

import com.maky.app.view.graphics.elements.GraphicElement;
import com.maky.environment.AppColor;
import com.maky.environment.AppGraphics;
import com.maky.environment.GraphicsConfig;

public abstract class Screen extends GraphicElement implements IScreen {

	public Screen(View view) {
		super(view);
	}

	public int getHeight() {
		return GraphicsConfig.getCanvasHeight();
	}

	public int getWidth() {
		return GraphicsConfig.getCanvasWidth();
	}
	
	public void paint(AppGraphics gr) {
		gr.setColor(AppColor.ORANGE);
		gr.drawString(" Screen paint not OVERRIDEN", GraphicsConfig.getScaledX(0), GraphicsConfig.getScaledY(0));
	}
}
