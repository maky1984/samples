package com.maky.arkanoid;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

public interface IArkanoidView {

	public void onStart(GL10 gl, EGLConfig config, GameLayoutGL game);
	
	public void onDraw(GL10 gl);

	public void onUpdate();

	public boolean onTouch(MotionEvent event);
}
