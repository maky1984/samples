package com.maky.funnyballs;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
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

/**
 * @author  michael
 */
public class GameLayout extends SurfaceView implements SurfaceHolder.Callback, Runnable {

	private SurfaceHolder mainHolder = getHolder();
	/**
	 * @uml.property  name="util"
	 * @uml.associationEnd  
	 */
	private Util util;
	private Bitmap buffer;
	private Canvas bufferCanvas;
	/**
	 * @uml.property  name="textGenerator"
	 * @uml.associationEnd  
	 */
	private Text textGenerator;
	private SharedPreferences preferences;
	
	private Rect fullScreen;
	private final Matrix defaultMatrix = new Matrix();

	private List<IGameComponent> components;
	/**
	 * @uml.property  name="currentComponent"
	 * @uml.associationEnd  
	 */
	private IGameComponent currentComponent;

	private final int LOADING_SIGN_TOP = 350;
	private final int LOADING_SIGN_LEFT = 310;
	/**
	 * @uml.property  name="loadingSign"
	 * @uml.associationEnd  
	 */
	private IGameComponent loadingSign;
	// Debug fields
	public boolean debug = true;
	private long time;
	private int frames;
	private int fps;
	private String frameText = "";
	

	public GameLayout(Context context) {
		super(context);
		mainHolder.addCallback(this);
	}

	public GameLayout(Context context, AttributeSet set) {
		super(context, set);
		mainHolder.addCallback(this);
	}

	private void init() {
		util = new Util(getWidth(), getHeight());
		components = new ArrayList<IGameComponent>();
		textGenerator = new Text(this);
		loadingSign = textGenerator.getTextComponent("LOADING", util.getSX(LOADING_SIGN_LEFT), util.getSY(LOADING_SIGN_TOP), null);
	}

	private void loadMenuResources() {
		buffer = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
		bufferCanvas = new Canvas(buffer);
	}

	private void fillConstants() {
		int width = getWidth();
		int height = getHeight();
		fullScreen = new Rect(0, 0, width - 1, height - 1);
	}

	private void startProcess() {
		Menu menu = new Menu(this);
		add(menu);
		menu.start();
		currentComponent = menu;
	}

	private void stopProcess() {
		remove(currentComponent);
		currentComponent = null;
	}

	public void add(final IGameComponent component) {
		if (!component.isInitialized()) {
			component.init();
		}
		synchronized (components) {
			components.add(component);
		}
	}

	public void remove(IGameComponent component) {
		synchronized (components) {
			components.remove(component);
		}
		component.destroy();
	}

	/**
	 * @return
	 * @uml.property  name="util"
	 */
	public Util getUtil() {
		return util;
	}

	public Rect getFullScreenRect() {
		return fullScreen;
	}

	
	private void update() {
		synchronized (components) {
			for (IGameComponent component : components) {
				component.update();
			}
		}
	}

	private void draw() {
		synchronized (components) {
			for (IGameComponent component : components) {
				component.draw(bufferCanvas);
			}
		}
	}

	private boolean touch(MotionEvent event) {
		boolean result = false;
		synchronized (components) {
			for (IGameComponent component : components) {
				if (component.touch(event)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	private boolean keyPressed(int keyCode) {
		boolean result = false;
		synchronized (components) {
			for (IGameComponent component : components) {
				if (component.keyPressed(keyCode)) {
					result = true;
					break;
				}
			}
		}
		return result;
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
					frameText = Integer.toString(frames) + " fps, " + Long.toString(memUsage) + " freeMem";
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
		loadMenuResources();
		fillConstants();
		startProcess();
		new Thread(this).start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		stopProcess();
	}

	public IGameComponent getTextComponent(String msg, int x, int y, Paint paint, Rect destanation) {
		return textGenerator.getTextComponent(msg, x, y, paint, destanation);
	}

	public IGameComponent getTextComponent(String msg, int x, int y, Paint paint) {
		return textGenerator.getTextComponent(msg, x, y, paint);
	}
	
	/**
	 * @param preferences
	 * @uml.property  name="preferences"
	 */
	public void setPreferences(SharedPreferences preferences) {
		this.preferences = preferences;
	}
	
	/**
	 * @return
	 * @uml.property  name="preferences"
	 */
	public SharedPreferences getPreferences() {
		return preferences;
	}
	
	/**
	 * @return
	 * @uml.property  name="loadingSign"
	 */
	public IGameComponent getLoadingSign() {
		return loadingSign;
	}
	
	public int getFPS() {
		return fps;
	}
}
