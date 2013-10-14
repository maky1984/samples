package com.maky.arkanoid;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.maky.collision.CollisionMap;

import android.view.MotionEvent;

public class ArkBorder implements IArkanoidView {

	private final ArkanoidGame game;
	
	public ArkBorder(ArkanoidGame game) {
		this.game = game;
	}
	
	@Override
	public void onStart(GL10 gl, EGLConfig config, GameLayoutGL game) {
		CollisionMap collisionMap = this.game.getCollisionMap();
		int width = game.getUtil().getWidth();
		int height = game.getUtil().getHeight();
		for (int x=0;x<width;x++) {
			collisionMap.addPoint(x, 0, this);
			collisionMap.addPoint(x, height - 1, this);
		}
		for (int y=0;y < height; y++) {
			collisionMap.addPoint(0, y, this);
			collisionMap.addPoint(width - 1, y, this);
		}
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
