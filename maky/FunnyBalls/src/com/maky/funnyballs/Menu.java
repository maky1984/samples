package com.maky.funnyballs;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.maky.funnyballs.utility.Logger;

/**
 * @author  michael
 */
public class Menu extends GameComponent {

	// menu specific fields
	private final Rect SIZE_BUTTON_OK = new Rect(353, 322, 465, 396);
//	private final Rect SIZE_MENU = new Rect(10, 30, 40, 50);
//	private final Rect SIZE_MENU_CONTINUE = new Rect(20, 304, 50, 60);
	private final Rect SIZE_MENUITEM_CONTINUE = new Rect(453, 105, 740, 152);
	private final Rect SIZE_MENUITEM_NEW_GAME = new Rect(493, 175, 691, 220);
	private final Rect SIZE_MENUITEM_HIGHSCORE = new Rect(483, 246, 701, 289);
	private final Rect SIZE_MENUITEM_CREDITS = new Rect(505, 315, 680, 362);
	private final Rect SIZE_MENUITEM_EXIT = new Rect(491, 388, 691, 436);
	private final Rect[] SIZE_MAP_LEVELS = new Rect[] { new Rect(155, 314, 238, 447), new Rect(512, 247, 595, 380), new Rect(252, 117, 335, 250),
			new Rect(606, 88, 689, 221), new Rect(360, 8, 443, 141) };

	private final static int SPLASH_SCREEN = 1;
	private final static int MENU_SCREEN = 2;
	private final static int MAP_SCREEN = 3;
	private final static int LEVEL_SCREEN = 4;
	private final static int HIGHSCORE_SCREEN = 5;
	private final static int CREDITS_SCREEN = 6;

	private Bitmap fonLogo;
	private Bitmap buttonOk;
	private Bitmap fonMenu;
	private Bitmap menu;
	private Bitmap menuContinue;
	private Bitmap map;
	private Bitmap path;
	private Bitmap flagUp;
	private Bitmap flagDown;

	private Rect fullScreen;
	private Rect okButtonPos;
	private Rect menuNewGameItemPos;
	private Rect menuContinueItemPos;
	private Rect[] mapFlagsPos;

	private volatile Timer timer;
	private boolean fading;
	private int fadeParameter;
	private boolean fadeIn;
	private boolean needUpdate;
	private boolean showPartialElement;

	/**
	 * @uml.property  name="level"
	 * @uml.associationEnd  
	 */
	private Level level;
	
	private int state;
	/**
	 * @uml.property  name="gameState"
	 * @uml.associationEnd  
	 */
	private State gameState;

	public Menu(GameLayout control) {
		super(control);
		timer = new Timer();
	}

	private synchronized void startFade() {
		fading = true;
		TimerTask task = new TimerTask() {
			public void run() {
				tick();
			}
		};
		timer.schedule(task, 0, 30);
	}

	private synchronized void startFadeOut() {
		if (!fading) {
			fadeParameter = 255;
			fadeIn = false;
			startFade();
		}
	}

	private synchronized void startFadeIn() {
		if (!fading) {
			fadeParameter = 0;
			fadeIn = true;
			startFade();
		}
	}

	private void fadeOutEnded() {
		switch (state) {
		case SPLASH_SCREEN:
			gotoMenu();
			fonLogo = null;
			buttonOk = null;
			break;
		case MENU_SCREEN:
			break;
		case MAP_SCREEN:
			gotoLevel();
			break;
		case LEVEL_SCREEN:
			gotoLevel();
			break;
		}
	}

	private void fadeInEnded() {
		switch (state) {
		case SPLASH_SCREEN:
			showPartialElement = true;
			break;
		case MENU_SCREEN:
			break;
		case MAP_SCREEN:
			showPartialElement = true;
			break;
		}
	}

	public synchronized void tick() {
		boolean cancelTimer = false;
		if (fading) {
			needUpdate = true;
			if (fadeIn) {
				fadeParameter += 3;
				if (fadeParameter >= 255) {
					fadeParameter = 255;
					fadeInEnded();
					cancelTimer = true;
				}
			} else {
				fadeParameter -= 3;
				if (fadeParameter <= 0) {
					fadeParameter = 0;
					try {
						fadeOutEnded();
					} catch (Throwable ex) {
						Logger.getInstance(this).logException("Fade out ended error", ex);
					}
					cancelTimer = true;
				}
			}
			if (cancelTimer) {
				timer.cancel();
				timer = new Timer();
				fading = false;
			}
		}
	}

	private void loadMenuAndMapResource() {
		if (fonMenu == null) {
			fonMenu = BitmapFactory.decodeResource(resources, R.drawable.fonmenu);
			if (gameState.isNew()) {
				menu = BitmapFactory.decodeResource(resources, R.drawable.menu);
			} else {
				menuContinue = BitmapFactory.decodeResource(resources, R.drawable.menucontinue);
			}
			map = BitmapFactory.decodeResource(resources, R.drawable.map);
			path = BitmapFactory.decodeResource(resources, R.drawable.mappath);
			flagDown = BitmapFactory.decodeResource(resources, R.drawable.flagdown);
			flagUp = BitmapFactory.decodeResource(resources, R.drawable.flagup);
		}
	}
	
	private void cleanMenuAndMapResources() {
		if (fonMenu != null) {
			fonMenu.recycle();
			if (menu == null) {
				menuContinue.recycle();
				menuContinue = null;
			} else {
				menu.recycle();
				menu = null;
			}
			map.recycle();
			path.recycle();
			flagDown.recycle();
			flagUp.recycle();
			fonMenu = null;
			map = null;
			path = null;
			flagDown = null;
			flagUp = null;
			System.gc();
		}
	}
	
	@Override
	public void init() {
		super.init();
		loadState();
		fonLogo = BitmapFactory.decodeResource(resources, R.drawable.logofunnyballs);
		buttonOk = BitmapFactory.decodeResource(resources, R.drawable.okbutton);
		loadMenuAndMapResource();
		okButtonPos = util.getSRect(SIZE_BUTTON_OK);
		menuNewGameItemPos = util.getSRect(SIZE_MENUITEM_NEW_GAME);
		menuContinueItemPos = util.getSRect(SIZE_MENUITEM_CONTINUE);
		mapFlagsPos = new Rect[SIZE_MAP_LEVELS.length];
		int i = 0;
		for (Rect pos : SIZE_MAP_LEVELS) {
			mapFlagsPos[i++] = util.getSRect(pos);
		}
		fullScreen = gameControl.getFullScreenRect();
		state = SPLASH_SCREEN;
	}

	@Override
	public void start() {
		startFadeIn();
	}

	@Override
	public void update() {
		if (needUpdate) {
			switch (state) {
			case SPLASH_SCREEN:
				paint.setAlpha(fadeParameter);
				break;
			case MENU_SCREEN:
				paint.setAlpha(255);
				break;
			case MAP_SCREEN:
				paint.setAlpha(fadeParameter);
				break;
			case LEVEL_SCREEN:
				break;
			default:
				Logger.getInstance(this).logError("Unknown state to update in Menu class");
			}
		}
	}

	@Override
	public void draw(Canvas canvas) {
		switch (state) {
		case SPLASH_SCREEN:
			if (!showPartialElement) {
				canvas.drawColor(Color.BLACK);
			}
			canvas.drawBitmap(fonLogo, null, fullScreen, paint);
			if (showPartialElement) {
				canvas.drawBitmap(buttonOk, null, okButtonPos, null);
			}
			break;
		case MENU_SCREEN:
			if (map == null) {
				Logger.getInstance(this).logError("It might be already LEVEL STATE and resources are cleaned");
			} else {
				canvas.drawBitmap(fonMenu, null, fullScreen, paint);
				if (gameState.isNew()) {
					// TODO:
					// Use full screen image for menu - TEMPORARY
					canvas.drawBitmap(menu, null, fullScreen, paint);
				} else {
					canvas.drawBitmap(menuContinue, null, fullScreen, paint);
				}
			}
			break;
		case MAP_SCREEN:
			if (!showPartialElement) {
				canvas.drawColor(Color.BLACK);
			}
			canvas.drawBitmap(map, null, fullScreen, paint);
			if (showPartialElement) {
				canvas.drawBitmap(path, null, fullScreen, paint);
				for (int i = 0; i < gameState.getMaxPlayLevel(); i++) {
					canvas.drawBitmap(flagUp, null, mapFlagsPos[i], null);
				}
				canvas.drawBitmap(flagDown, null, mapFlagsPos[gameState.getMaxPlayLevel()], null);
			}
			break;
		case LEVEL_SCREEN:
			gameControl.getLoadingSign().draw(canvas);
			break;
		default:
			Logger.getInstance(this).logError("Unknown state to draw in Menu class");
		}
	}

	@Override
	public void destroy() {
		if (timer != null) {
			timer.cancel();
		}
	}

	@Override
	public boolean touch(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			int x = (int) event.getX();
			int y = (int) event.getY();
			switch (state) {
			case HIGHSCORE_SCREEN:
				gotoMenu();
				break;
			case SPLASH_SCREEN:
				showPartialElement = false;
				startFadeOut();
				break;
			case MENU_SCREEN:
				if (!gameState.isNew() && menuContinueItemPos.contains(x, y)) {
					gotoMap();
				} else if (menuNewGameItemPos.contains(x, y)) {
					gameState.clear();
					gotoMap();
				} else if (util.getSRect(SIZE_MENUITEM_CREDITS).contains(x, y)) {
					gotoCredits();
				} else if (util.getSRect(SIZE_MENUITEM_HIGHSCORE).contains(x, y)) {
					gotoHighscore();
				} else if (util.getSRect(SIZE_MENUITEM_EXIT).contains(x, y)) {
					gotoExit();
				}
				break;
			case MAP_SCREEN:
				for (int i = 0; i < mapFlagsPos.length; i++) {
					if (mapFlagsPos[i].contains(x, y)) {
						tryLevel(i);
						break;
					}
				}
				break;
			default:
				return false;
			}
			return true;
		}
		return true;
	}

	@Override
	public boolean keyPressed(int keyCode) {
		return false;
	}

	public void gotoMenu() {
		cleanLevel();
		loadMenuAndMapResource();
		state = MENU_SCREEN;
		needUpdate = true;
	}

	private void gotoMap() {
		loadMenuAndMapResource();
		state = MAP_SCREEN;
		needUpdate = true;
		startFadeIn();
	}

	private void gotoCredits() {

	}

	private void gotoHighscore() {
		state = HIGHSCORE_SCREEN;
	}

	public void gotoExit() {
		System.exit(0);
	}

	private void tryLevel(int i) {
		if (i <= gameState.getMaxPlayLevel()) {
			gameState.setNew(false);
			gameState.setCurrentLevel(i);
			showPartialElement = false;
			startFadeOut();
		} else {
			Logger.getInstance(this).logInfo("User cant play " + i + " level, current level=" + gameState.getMaxPlayLevel());
		}
	}
	
	private void gotoLevel() {
		state = LEVEL_SCREEN;
		cleanMenuAndMapResources();
		level = new Level(this, gameState);
		gameControl.add(level);
		gameControl.remove(this);
		needUpdate = true;
		level.start();
	}
	
	public void cleanLevel() {
		if (level != null) {
			gameControl.remove(level);
			gameControl.add(this);
			level = null;
			System.gc();
		}
	}
	
	public void finishLevel() {
		cleanLevel();
		if (gameState.getCurrentSubLevel() == 0) {
			gotoMap();
		} else {
			startFadeOut();
			//gotoLevel();
		}
	}

	private void loadState() {
		gameState = new State(gameControl.getPreferences());
	}
}
