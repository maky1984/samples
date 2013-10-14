package com.maky.funnyballs;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * @author  michael
 */
public abstract class GameComponent implements IGameComponent {

	/**
	 * @uml.property  name="gameControl"
	 * @uml.associationEnd  
	 */
	protected final GameLayout gameControl;
	/**
	 * @uml.property  name="util"
	 * @uml.associationEnd  
	 */
	protected final Util util;
	protected final Resources resources;
	protected final Paint paint;
	protected boolean initialized;
	
	public GameComponent(GameLayout control) {
		gameControl = control;
		util = control.getUtil();
		resources = control.getContext().getResources();
		paint = new Paint();
	}

	@Override
	public void init() {
		initialized = true;
	}
	/**
	 * @return
	 * @uml.property  name="initialized"
	 */
	@Override
	public boolean isInitialized() {
		return initialized;
	}
	@Override
	abstract public void start();
	@Override
	abstract public void update();
	@Override
	abstract public void draw(Canvas canvas);
	@Override
	abstract public void destroy();
	@Override
	abstract public boolean touch(MotionEvent event);
	@Override
	abstract public boolean keyPressed(int keyCode);
}
