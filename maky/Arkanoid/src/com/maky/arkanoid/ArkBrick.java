package com.maky.arkanoid;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

public class ArkBrick implements IArkanoidView {

	private ArkanoidGame game;
	
	private final int HEIGHT = 100;
	private final int WIDTH = 100;
	private final int xPx, yPx;

	private float x,y,z;
	private float[] coords;
	private FloatBuffer vertexBuffer;

	public ArkBrick(int x, int y, ArkanoidGame game) {
		xPx = x;
		yPx = y;
		this.game = game;
	}
	
	private void initShapes(Util util) {
		float w = util.getSX_GL(WIDTH);
		float h = util.getSY_GL(HEIGHT);
		x = util.getSX_GL(xPx);
		y = util.getSY_GL(yPx);
		coords = new float[]{ -w/2, -h/2, 0, -w/2, h/2, 0, w/2, h/2, 0, w/2, -h/2, 0};
		ByteBuffer bb = ByteBuffer.allocateDirect(coords.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(coords);
		vertexBuffer.position(0);
	}
	
	@Override
	public void onStart(GL10 gl, EGLConfig config, GameLayoutGL game) {
		initShapes(game.getUtil());
	}

	@Override
	public void onDraw(GL10 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(x, y, z);
		// Draw the triangle
		gl.glColor4f(0.63671875f, 0.76953125f, 0.22265625f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		gl.glPopMatrix();
	}

	@Override
	public void onUpdate() {
	}

	@Override
	public boolean onTouch(MotionEvent event) {
		return false;
	}
}