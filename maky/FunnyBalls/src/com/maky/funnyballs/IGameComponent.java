package com.maky.funnyballs;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface IGameComponent {

	public void init();
	
	public boolean isInitialized();
	
	public void start();
	
	public void update();

	public void draw(Canvas canvas);

	public void destroy();

	public boolean touch(MotionEvent event);
	
	public boolean keyPressed(int keyCode);
}
