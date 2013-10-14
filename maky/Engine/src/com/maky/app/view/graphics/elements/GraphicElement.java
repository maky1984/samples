package com.maky.app.view.graphics.elements;

import com.maky.app.view.View;
import com.maky.environment.AppGraphics;
import com.maky.environment.IPaintable;

public abstract class GraphicElement implements IPaintable {

	private final View view;
	private int x;
	private int y;

	protected GraphicElement(View view) {
		this.view = view;
	}
	
	public GraphicElement(int x, int y, View view) {
		this.view = view;
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	protected View getView() {
		return view;
	}

	public abstract void paint(AppGraphics gr);
	public abstract int getWidth();
	public abstract int getHeight();
}
