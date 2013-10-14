package com.maky.demo.graph;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class Screen implements IComponent {
	
	private final boolean USE_RANDOM_SCENE = false;
	
	Surface surface;
	
	private int[] data;
	private final int width;
	private final int height;
	private Model2D model;
	
	public Screen(Surface surface) {
		this.surface = surface;
		width = surface.getWidth();
		height = surface.getHeight();
		//model = Model2D.createTestModel2DV1(USE_RANDOM_SCENE);
		model = Model2D.createTestModelTriangle(0, 0, 30, 20, 40, 10, 250, 150, 250);
		data = new int[width * height];
	}
	
	@Override
	public void start() {
	}

	@Override
	public void update() {
		model.fillData(data, 0, width, width, height, width, height);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(data, 0, width, 0, 0, width, height, false, null);
	}

	@Override
	public void destroy() {
	}

	@Override
	public boolean touch(MotionEvent event) {
		return false;
	}

	@Override
	public boolean keyPressed(int keyCode) {
		return false;
	}
}