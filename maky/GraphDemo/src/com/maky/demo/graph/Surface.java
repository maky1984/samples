package com.maky.demo.graph;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Surface extends SurfaceView implements SurfaceHolder.Callback, Runnable {

	private SurfaceHolder mainHolder = getHolder();
	private Util util;
	private Bitmap buffer;
	private Canvas bufferCanvas;

	private Rect fullScreen;
	private final Matrix defaultMatrix = new Matrix();

	private IComponent scene;

	// Debug fields
	public boolean debug = true;
	private long time;
	private int frames;
	private int fps;
	private String frameText = "";

	public Surface(Context context) {
		super(context);
		mainHolder.addCallback(this);
	}

	public Surface(Context context, AttributeSet set) {
		super(context, set);
		mainHolder.addCallback(this);
	}

	private void init() {
		util = new Util(getWidth(), getHeight());
		buffer = Bitmap.createBitmap(getWidth(), getHeight(), Config.RGB_565);
		bufferCanvas = new Canvas(buffer);
	}

	private void fillConstants() {
		int width = getWidth();
		int height = getHeight();
		fullScreen = new Rect(0, 0, width - 1, height - 1);
	}

	private void startProcess(IComponent component) {
		scene = component;
	}

	private void stopProcess() {
		scene = null;
	}

	public Util getUtil() {
		return util;
	}

	public Rect getFullScreenRect() {
		return fullScreen;
	}

	private void update() {
		scene.update();
	}

	private void draw() {
		scene.draw(bufferCanvas);
	}

	private boolean touch(MotionEvent event) {
		return scene.touch(event);
	}

	private boolean keyPressed(int keyCode) {
		return scene.keyPressed(keyCode);
	}

	private void waitTick(long loopTime) {
		long delay = 40 /* 1000 / 25 */- loopTime;
		if (delay > 0) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		boolean work = true;
		time = System.currentTimeMillis();
		while (work) {
			long time2 = System.currentTimeMillis();
			if (debug) {
				frames++;
				if (time2 - time > 1000) {
					time = time2;
					long memUsage = Runtime.getRuntime().freeMemory();
					frameText = (new StringBuffer(Integer.toString(frames)).append(" fps, ").append(
							Long.toString(memUsage)).append(" freeMem")).toString();
					fps = frames;
					frames = 0;
				}
			}
			update();
			draw();
			Canvas canvas = mainHolder.lockCanvas();
			if (canvas == null) {
				work = false;
			} else {
				canvas.drawBitmap(buffer, defaultMatrix, null);
				if (debug) {
					Paint paint = new Paint();
					paint.setColor(Color.WHITE);
					paint.setTextSize(10);
					canvas.drawText(frameText, 10, 10, paint);
				}
				mainHolder.unlockCanvasAndPost(canvas);
			}
			waitTick(System.currentTimeMillis() - time2);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyPressed(keyCode)) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (touch(event)) {
			return true;
		}
		return super.onTouchEvent(event);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	public void surfaceCreated(final SurfaceHolder holder) {
		init();
		fillConstants();
		startProcess(new Screen(this));
		new Thread(this).start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		stopProcess();
	}

	public int getFPS() {
		return fps;
	}
}
