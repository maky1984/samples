package com.maky.arkanoid;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.maky.util.log.Logger;

public class GameLayoutGL extends GLSurfaceView implements Renderer {

	private static final int FPS_LIMIT = 25;
	
	private Util util;
	private ArkanoidMenu menu;
	private ArkanoidGame game;
	private IArkanoidView currentView;

	private boolean debug = true;
	private int fps;
	private long time;
	private int frames = 0;
	private String frameText = "";

	public static native void testMethod(Bitmap bitmap);

	public GameLayoutGL(Context context) {
		super(context);
		setRenderer(this);
	}

	public GameLayoutGL(Context context, AttributeSet set) {
		super(context, set);
		setRenderer(this);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		Logger.getInstance(this).logInfo("GameLayoutGL onDrawFrame");
		long time2 = System.currentTimeMillis();
		if (debug) {
			frames++;
			if (time2 - time > 1000) {
				time = time2;
				long memUsage = Runtime.getRuntime().freeMemory();
				frameText = Integer.toString(frames) + " fps, " + Long.toString(memUsage) + " freeMem";
				fps = frames;
				frames = 0;
			}
		}
		update();
		draw(gl);
		if (debug) {
			// drawText(frameText, gl);
			Logger.getInstance(this).logInfo(frameText);
			Log.d("ARK", frameText);
		}
		waitTick(System.currentTimeMillis() - time2);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		testMethod(null);
		util = new Util();
		menu = new ArkanoidMenu();
		game = new ArkanoidGame(util);
		currentView = game;
		currentView.onStart(gl, config, this);
		time = System.currentTimeMillis();
	}

	private void update() {
		currentView.onUpdate();
	}

	private void draw(GL10 gl) {
		currentView.onDraw(gl);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!currentView.onTouch(event)) {
			System.exit(0);
		}
		return super.onTouchEvent(event);
	}

	private void waitTick(long loopTime) {
		long delay = 1000 / FPS_LIMIT - loopTime; // where 5 is frames per second
		if (delay > 0) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Util getUtil() {
		return util;
	}

	// private void drawText(String text, GL10 gl) {
	// // Create an empty, mutable bitmap
	// Bitmap bitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_4444);
	// // get a canvas to paint over the bitmap
	// Canvas canvas = new Canvas(bitmap);
	// bitmap.eraseColor(0);
	//
	// // Draw the text
	// Paint textPaint = new Paint();
	// textPaint.setTextSize(32);
	// textPaint.setAntiAlias(true);
	// textPaint.setARGB(0xff, 0x00, 0x00, 0x00);
	// // draw the text centered
	// canvas.drawText(text, 0, 0, textPaint);
	//
	// int[] textures = new int[1];
	//
	// // Generate one texture pointer...
	// gl.glGenTextures(1, textures, 0);
	// // ...and bind it to our array
	// gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
	//
	// // Create Nearest Filtered Texture
	// gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
	// gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
	//
	// // Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
	// gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
	// gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
	//
	// // Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
	// GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
	//
	// float textureCoordinates[] = {0.0f, 2.0f,
	// 2.0f, 2.0f,
	// 0.0f, 0.0f,
	// 2.0f, 0.0f };
	//
	// ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoordinates.length * 4);
	// byteBuf.order(ByteOrder.nativeOrder());
	// FloatBuffer textureBuffer = byteBuf.asFloatBuffer();
	// textureBuffer.put(textureCoordinates);
	// textureBuffer.position(0);
	//
	// gl.glEnable(GL10.GL_TEXTURE_2D);
	// // Tell OpenGL where our texture is located.
	// gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
	// // Tell OpenGL to enable the use of UV coordinates.
	// gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	// // Telling OpenGL where our UV coordinates are.
	// gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
	//
	// // ... here goes the rendering of the mesh ...
	// gl.glDrawElements(GL10.GL_TEXTURE, 2, GL10.GL_FLOAT, textureBuffer);
	// // Disable the use of UV coordinates.
	// gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	// // Disable the use of textures.
	// gl.glDisable(GL10.GL_TEXTURE_2D);
	//
	// // Clean up
	// bitmap.recycle();
	//
	// }

}
