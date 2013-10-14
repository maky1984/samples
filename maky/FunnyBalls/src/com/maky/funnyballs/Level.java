package com.maky.funnyballs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.view.MotionEvent;

import com.maky.funnyballs.utility.Logger;

/**
 * @author  michael
 */
public class Level extends GameComponent {

	private static short ALPHA_DEFAULT = 255;
	private static int REMOVE_BALL_ANIMATION_TIME = 500;
	private final Rect SIZE_MENU_BUTTON = new Rect(675, 295, 784, 343);
	private final Rect SIZE_EXIT_BUTTON = new Rect(682, 384, 786, 438);
	
	private class Cell {
		short value;
		short alpha = ALPHA_DEFAULT;
		short newValue;
		boolean removed;
	}

	private final int[] IMAGE_BACKGROUNDS = new int[] { R.drawable.fon1, R.drawable.fon2, R.drawable.fon3,
			R.drawable.fon4, R.drawable.fon5 };
	private final int[] IMAGE_BALLS = new int[] { R.drawable.ball1, R.drawable.ball2, R.drawable.ball3,
			R.drawable.ball4, R.drawable.ball5, R.drawable.ball6, R.drawable.ball7, R.drawable.ball8, R.drawable.ball9,
			R.drawable.ball10 };
	private final int CELL_WIDTH = 40;
	private final int CELL_HEIGHT = 40;
	private final int FUTURE_CELL_DX = 7;
	private final int FUTURE_CELL_DY = 7;
	private final int TABLE_LEFT = 0;
	private final int TABLE_TOP = 0;
	private final int SCORE_LEFT = 660;
	private final int SCORE_TOP = 55;
	private final int TEXT_TOP = 200;
	private final int TEXT_LEFT = 250;
	private final int SCORE_MSG_LEFT = 680;
	private final int SCORE_MSG_TOP = 170;
	private final Rect SCORE_TEXT_SIZES = new Rect(682, 164, 778, 206);

	private int tableLeft;
	private int tableTop;
	private int cellWidth;
	private int cellHeight;
	private int futureBallDX;
	private int futureBallDY;
	private Rect levelScorePos;
	private Rect textScorePos;
	private Rect fullScreen;
	private int scoreMsgLeft;
	private int scoreMsgTop;

	/**
	 * @uml.property  name="parent"
	 * @uml.associationEnd  
	 */
	private Menu parent;
	/**
	 * @uml.property  name="gameState"
	 * @uml.associationEnd  
	 */
	private State gameState;
	private int levelNumber;
	private int subLevelNumber;

	private Bitmap background;
	private Bitmap scoreUi;
	private Bitmap groundCell;
	private Bitmap wall;
	private Bitmap strongWall;
	private Bitmap balls[];

	/**
	 * @uml.property  name="model"
	 * @uml.associationEnd  
	 */
	private LevelModel model;
	/**
	 * @uml.property  name="map"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	private Cell[][] map;
	private int selectedX;
	private int selectedY;
	private boolean shiftDirection;
	private int shiftSelectedY;
	private int alphaAnimDelta;
	private boolean showBackground;
	private boolean showLevelNumber;
	
	private int score;
	private int paintedScore = -1;
	/**
	 * @uml.property  name="partialComponent"
	 * @uml.associationEnd  
	 */
	private IGameComponent partialComponent;
	/**
	 * @uml.property  name="scoreText"
	 * @uml.associationEnd  
	 */
	private IGameComponent scoreText;
	/**
	 * @uml.property  name="levelNumberText"
	 * @uml.associationEnd  
	 */
	private IGameComponent levelNumberText;
	private Paint scoreFilterPaint;
	private Paint futurePaint;
	private ColorMatrixColorFilter grayedFilter;
	private ColorMatrixColorFilter scoreLighterFilter;
	
	public Level(Menu parent, State state) {
		super(parent.gameControl);
		this.parent = parent;
		this.gameState = state;
		levelNumber = state.getCurrentLevel();
		subLevelNumber = state.getCurrentSubLevel();
		short[][] map = LinesResources.maps[levelNumber][subLevelNumber];
		this.map = new Cell[map.length][];
		for (int i = 0; i < map.length; i++) {
			this.map[i] = new Cell[map[i].length];
			for (int j = 0; j < map[i].length; j++) {
				this.map[i][j] = new Cell();
				this.map[i][j].value = map[i][j];
			}
		}
		model = new LevelModel(map, LinesResources.balls[levelNumber][subLevelNumber]);
		selectedX = -1;
		selectedY = -1;
	}

	@Override
	public void init() {
		super.init();
		background = BitmapFactory.decodeResource(resources, IMAGE_BACKGROUNDS[levelNumber]);
		groundCell = BitmapFactory.decodeResource(resources, R.drawable.cell);
		// wall = BitmapFactory.decodeResource(resources, R.drawable.wall);
		scoreUi = BitmapFactory.decodeResource(resources, R.drawable.scoreui);
		short[] levelBalls = model.getBalls();
		balls = new Bitmap[levelBalls.length];
		for (short i : levelBalls) {
			int imageNum = LinesResources.ballNumber(i);
			balls[imageNum] = BitmapFactory.decodeResource(resources, IMAGE_BALLS[imageNum]);
		}
		tableLeft = util.getSX(TABLE_LEFT);
		tableTop = util.getSY(TABLE_TOP);
		cellWidth = util.getSX(CELL_WIDTH);
		cellHeight = util.getSY(CELL_HEIGHT);
		futureBallDX = util.getSX(FUTURE_CELL_DX);
		futureBallDY = util.getSY(FUTURE_CELL_DY);
		fullScreen = gameControl.getFullScreenRect();
		int scoreLeft = util.getSX(SCORE_LEFT);
		int scoreTop = util.getSY(SCORE_TOP);
		levelScorePos = new Rect(scoreLeft, scoreTop, fullScreen.right, fullScreen.bottom);
		textScorePos = util.getSRect(SCORE_TEXT_SIZES);
		scoreMsgLeft = util.getSX(SCORE_MSG_LEFT);
		scoreMsgTop = util.getSY(SCORE_MSG_TOP);
		scoreFilterPaint = new Paint();
		scoreLighterFilter = new ColorMatrixColorFilter(new float[]{ 
				1,0,0,0,130,
				0,1,0,0,130,
				0,0,1,0,130,
				0,0,0,1,0
		});
		scoreFilterPaint.setColorFilter(scoreLighterFilter);
		grayedFilter = new ColorMatrixColorFilter(new float[]{ 
				1,0,0,0,-130,
				0,1,0,0,-130,
				0,0,1,0,-130,
				0,0,0,1,0
		});
		futurePaint = new Paint();
		futurePaint.setAlpha(125);
		StringBuffer msg = new StringBuffer("LEVEL ");
		msg.append(gameState.getCurrentLevel() + 1);
		msg.append("-");
		msg.append(gameState.getCurrentSubLevel() + 1);
		levelNumberText = gameControl.getTextComponent(msg.toString(), util.getSX(TEXT_LEFT), util.getSY(TEXT_TOP), null);
	}

	@Override
	public void start() {
		showLevelNumber = true;
	}

	@Override
	public synchronized void update() {
		short[][] model = this.model.getMap();
		for (int i = 0; i < model.length; i++) {
			for (int j = 0; j < model[i].length; j++) {
				Cell cell = map[i][j];
				short newValue = model[i][j];
				refresh(cell, newValue);
			}
		}
		if (selectedX != -1) {
			if (shiftDirection) {
				shiftSelectedY++;
				if (shiftSelectedY == 5) {
					shiftDirection = false;
				}
			} else {
				shiftSelectedY--;
				if (shiftSelectedY <= 0) {
					shiftDirection = true;
				}
			}
		}
	}

	private void refresh(Cell cell, short newValue) {
		if (!LevelModel.hasBall(newValue) && LevelModel.hasBall(cell.value) && cell.removed) {
			cell.removed = false;
			cell.alpha -= alphaAnimDelta;
			cell.newValue = newValue;
		} else if (cell.alpha == ALPHA_DEFAULT) {
			cell.value = newValue;
		}
		if (cell.alpha < ALPHA_DEFAULT && cell.alpha > 10) {
			cell.alpha -= alphaAnimDelta;
		} else if (cell.alpha <= 10) {
			cell.alpha = ALPHA_DEFAULT;
			cell.value = cell.newValue;
		}
	}

	@Override
	public synchronized void draw(Canvas canvas) {
		if (showBackground) {
			canvas.drawColor(Color.BLACK);
			paint.setColorFilter(grayedFilter);
			canvas.drawBitmap(background, null, fullScreen, paint);
			paint.setColorFilter(null);
			gameControl.getLoadingSign().draw(canvas);
		} else {
			if (showLevelNumber) {
				paint.setColorFilter(grayedFilter);
				scoreFilterPaint.setColorFilter(grayedFilter);
			}
			canvas.drawBitmap(background, null, fullScreen, paint);
			canvas.drawBitmap(scoreUi, null, fullScreen, paint);
			if (score != paintedScore) {
		        StringBuffer buffer = new StringBuffer();
		        String string = Integer.toString(score);
		        for (int i = string.length(); i < 4; i++) {
		            buffer.append('0');
		        }
		        buffer.append(string);
				scoreText = gameControl.getTextComponent(buffer.toString(), scoreMsgLeft, scoreMsgTop, scoreFilterPaint, textScorePos);
				paintedScore = score;
			}
			scoreText.draw(canvas);
			if (showLevelNumber) {
				drawLevelGrid(canvas, grayedFilter);
				paint.setColorFilter(null);
				drawLevelNumber(canvas);
			} else {
				drawLevelGrid(canvas, null);
			}
		}
	}
	
	private void drawLevelNumber(Canvas canvas) {
		levelNumberText.draw(canvas);
	}

	private void drawLevelGrid(Canvas canvas, ColorMatrixColorFilter colorFilter) {
		paint.setColorFilter(colorFilter);
		futurePaint.setColorFilter(colorFilter);
		int x = tableLeft, y;
		Rect cellPos = new Rect();
		for (int column = 0; column < map.length; column++) {
			y = tableTop;
			cellPos.left = x;
			cellPos.top = y;
			cellPos.right = x + cellWidth;
			cellPos.bottom = y + cellHeight;
			for (int raw = 0; raw < map[column].length; raw++) {
				short cellValue = map[column][raw].value;
				if (LevelModel.hasCell(cellValue)) {
					if (LevelModel.isWall(cellValue)) {
						if (LevelModel.isStrongWall(cellValue)) {
							canvas.drawBitmap(wall, null, cellPos, paint);
						} else {
							canvas.drawBitmap(wall, null, cellPos, paint);
						}
					} else {
						canvas.drawBitmap(groundCell, null, cellPos, paint);
					}
					if (LevelModel.hasFuture(cellValue)) {
						Rect futureCellPos = new Rect(cellPos);
						futureCellPos.bottom = futureCellPos.bottom - futureBallDY;
						futureCellPos.left = futureCellPos.left + futureBallDX;
						futureCellPos.right = futureCellPos.right - futureBallDX;
						futureCellPos.top = futureCellPos.top + futureBallDY;
						int ballNumber = LevelModel.getFutureNumber(cellValue);
						canvas.drawBitmap(balls[ballNumber], null, futureCellPos, futurePaint);
					} else if (LevelModel.hasBall(cellValue)) {
						int ballNumber = LevelModel.getBallNumber(cellValue);
						Rect ballPos = cellPos;
						if (selectedX == column && selectedY == raw) {
							ballPos = new Rect(cellPos);
							ballPos.bottom -= shiftSelectedY;
							ballPos.top -= shiftSelectedY;
						}
						paint.setAlpha(map[column][raw].alpha);
						canvas.drawBitmap(balls[ballNumber], null, ballPos, paint);
						paint.setAlpha(ALPHA_DEFAULT);
					}
				}
				y += cellHeight;
				cellPos.top = y;
				cellPos.bottom = y + cellHeight;
			}
			x += cellWidth;
		}
	}

	@Override
	public void destroy() {
		background.recycle();
		background = null;
	}

	private boolean checkGameEnd() {
		if (model.isEnd()) {
			if (model.isLose()) {
				processLose();
			} else {
				processWin();
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean touch(MotionEvent event) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		boolean result;
		alphaAnimDelta = 255 * 1000 / (REMOVE_BALL_ANIMATION_TIME * gameControl.getFPS());
		Logger.getInstance(this).logDebug("Touchevent recieved event = " + event);
		if (showLevelNumber && event.getAction() == MotionEvent.ACTION_UP) {
			showLevelNumber = false;
			scoreFilterPaint.setColorFilter(scoreLighterFilter);
			result = true;
		} else if (model.isEnd() && partialComponent != null && event.getAction() == MotionEvent.ACTION_UP) {
			gameControl.remove(partialComponent);
			if (model.isWin()) {
				gameState.nextLevel();
			}
			parent.finishLevel();
			showBackground = true;
			result = true;
		} else if (util.getSRect(SIZE_MENU_BUTTON).contains(x,y)) {
			parent.gotoMenu();
			result = true;
		} else if (util.getSRect(SIZE_EXIT_BUTTON).contains(x,y)) {
			parent.gotoMenu();
			result = true;
		} else {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				if ( x > levelScorePos.left) {
					result = true;
				} else {
					x -= tableLeft;
					y -= tableTop;
					if (x > 0 && x < map.length * cellWidth && y > 0 && y < map[0].length * cellHeight) {
						final int column = x / cellWidth;
						final int raw = y / cellHeight;
						if (selectedX == -1 && model.isSelectable(column, raw)) {
							selectedX = column;
							selectedY = raw;
						} else if (selectedX != -1) {
							if (model.isEmpty(column,raw)) {
								Thread thread = new Thread() {
									@Override
									public void run() {
										super.run();
										List<Integer> updates = new ArrayList<Integer>();
										score = model.moveBall(selectedX, selectedY, column, raw, score, updates);
										if (!checkGameEnd()) {
											score = model.addRandomBalls(score, updates);
										}
										Iterator<Integer> it = updates.iterator();
										while(it.hasNext()) {
											int removedColumn = it.next();
											int removedRaw = it.next();
											map[removedColumn][removedRaw].removed = true;
										}
										selectedX = -1;
										selectedY = -1;
										shiftSelectedY = 0;
										shiftDirection = false;
									}
								};
								thread.start();
							} else {
								selectedX = -1;
								selectedY = -1;
								shiftSelectedY = 0;
								shiftDirection = false;
							}
						} else {
							checkGameEnd();
						}
						result = true;
					} else {
						result = false;
					}
				}
			} else {
				result = true;
			}
		}
		return result;
	}
	
	private void processLose() {
		partialComponent = gameControl.getTextComponent("LEVEL FAILED", util.getSX(TEXT_LEFT), util.getSY(TEXT_TOP), null);
		gameControl.add(partialComponent);
	}
	
	private void processWin() {
		partialComponent = gameControl.getTextComponent("LEVEL COMPLETED", util.getSX(TEXT_LEFT), util.getSY(TEXT_TOP), null);
		gameControl.add(partialComponent);
	}
	
	@Override
	public boolean keyPressed(int keyCode) {
		return false;
	}

}
