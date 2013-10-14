package com.maky.arkanoid;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.maky.collision.CollisionMap;
import com.maky.math.UtilMath.MathPoint;

import android.view.MotionEvent;

public class ArkanoidGame implements IArkanoidView {

	private static final int ARK_BALL_HEIGHT = 100;
	private static final int ARK_BALL_WIDTH = 100;
	
	public static native void init();

	public static native void draw();

	public static native void moveLeft(int value);

	public static native void moveRight(int value);

	public static native void tick();
	
	private Util util;
	
	private ArkBall ball;
	private ArkBrick brick;
	
	private CollisionMap collisionMap;
	
	public ArkanoidGame(Util util) {
		this.util = util;
		collisionMap = new CollisionMap(util.getWidth(), util.getHeight());
		ball = new ArkBall(ARK_BALL_WIDTH, ARK_BALL_HEIGHT, 300, 200, this);
		brick = new ArkBrick(60, 60, this);
	}
	
	public void onStart(GL10 gl, EGLConfig config, GameLayoutGL game) {
		ball.onStart(gl, config, game);
		brick.onStart(gl, config, game);
	}

	@Override
	public void onDraw(GL10 gl) {
		ball.onDraw(gl);
		brick.onDraw(gl);
	}

	@Override
	public void onUpdate() {
		ball.onUpdate();
		brick.onUpdate();
	}
	
	public CollisionMap getCollisionMap() {
		return collisionMap;
	}
	
	@Override
	public boolean onTouch(MotionEvent event) {
		return ball.onTouch(event);
	}
}
