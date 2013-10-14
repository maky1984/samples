package com.maky.arkanoid;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.maky.util.log.Logger;

import android.view.MotionEvent;

public class ArkBall implements IArkanoidView {

	private ArkanoidGame game;
	
	private final int heightPx, widthPx, xPx, yPx;

	private float x,y,z;
	private FloatBuffer vertexBuffer;
	private float[] coords;
	
	private float dx,dy, angel, step;

	private final int QUOTER_CIRCLE_NUMBER = 35;
	private final int VERTICIES = QUOTER_CIRCLE_NUMBER * 4; // more than needed
	private float theta = 0;

	private Util util;
	
	public ArkBall(int width, int height, int x, int y, ArkanoidGame game) {
		heightPx = height;
		widthPx = width;
		xPx = x;
		yPx = y;
		this.game = game;
	}
	
	public int getRadius() {
		return heightPx/2;
	}

	private void initShapes(Util util) {
		float w = util.getSX_GL(widthPx);
		float h = util.getSY_GL(heightPx);
		x = util.getSX_GL(xPx);
		y = util.getSY_GL(yPx);
		coords = new float[VERTICIES * 3];
		ByteBuffer bb = ByteBuffer.allocateDirect(VERTICIES * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		for (int i = 0; i < VERTICIES * 3; i += 3) {
			coords[i + 0] = (float) Math.cos(theta) * w/2;
			coords[i + 1] = (float) Math.sin(theta) * h/2;
			coords[i + 2] = 0;
			vertexBuffer.put(coords[i + 0]);
			vertexBuffer.put(coords[i + 1]);
			vertexBuffer.put(coords[i + 2]);
			theta += Math.PI / QUOTER_CIRCLE_NUMBER;
		}
		vertexBuffer.position(0);
		step = 0.01f;
		angel = (float) Math.PI / 4;
	}

	@Override
	public void onStart(GL10 gl, EGLConfig config, GameLayoutGL game) {
		// Set the background frame color
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

		util = game.getUtil();
		
		// initialize the triangle vertex array
		initShapes(util);

		// Enable use of vertex arrays
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

	}

	@Override
	public void onDraw(GL10 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(x + dx, y + dy, z);
		// Redraw background color
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// Draw the triangle
		gl.glColor4f(0.63671875f, 0.76953125f, 0.22265625f, 0.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, VERTICIES);
		gl.glPopMatrix();
	}

	@Override
	public void onUpdate() {
		dx += Math.cos(angel) * step;
		dy += Math.sin(angel) * step;
		int xPixels = util.getSX_PX(x + dx);
		int yPixels = util.getSY_PX(y + dy);
		Object collisionObject = game.getCollisionMap().checkCollision(xPixels, yPixels); 
		if (collisionObject != null) {
			Logger.getInstance(this).logInfo("Collision detected xPixels = " + xPixels + " yPixels = " + yPixels);
		}
	}

	@Override
	public boolean onTouch(MotionEvent event) {
		return false;
	}

}
