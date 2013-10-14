package com.maky.arkanoid;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

public class ArkanoidMenu implements IArkanoidView {

	@Override
	public void onStart(GL10 gl, EGLConfig config, GameLayoutGL game) {
	}
	
	@Override
	public void onDraw(GL10 gl) {
	}

	@Override
	public void onUpdate() {
	}

	@Override
	public boolean onTouch(MotionEvent event) {
		return false;
	}

}
