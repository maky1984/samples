package com.maky.game.lines.view;

import com.maky.app.resource.RImage;
import com.maky.app.resource.ResourceManager;
import com.maky.app.view.View;
import com.maky.app.view.graphics.elements.Grid;
import com.maky.app.view.graphics.elements.Sprite;
import com.maky.environment.AppGraphics;
import com.maky.environment.AppImage;
import com.maky.game.lines.LinesResources;
import com.maky.game.lines.controller.LinesCommand;
import com.maky.game.lines.model.level.Level;
import com.maky.util.timing.IUpdateble;

public class LevelGrid extends Grid implements IUpdateble {

	private static final int NO_SELECTION = -1;
	private static final int SELECTED_BALL_ANIMATION_PERIOD = 7;

	// Logic related values
	private int selectedX;
	private int selectedY;

	// Graphics related values
	private final RImage[] ballsImages;
	private final AppImage[][] futureBallsImages;
	private final Sprite wallAnimation;
	
	private int selectedBallAnimationY;
	private boolean selectedBallAnimationDirection;

	private float[][] alphaCompositeMap;
	private short[][] screenGrid;
	private Sprite[][] removedWallAnimationMap;

	public LevelGrid(int offsetX, int offsetY, int bodyWidth, int bodyHeight, int thickness, short[][] map,
			short[] balls, View view) {
		super(offsetX, offsetY, bodyWidth, bodyHeight, thickness, map, view);
		int resImageId;
		ballsImages = new RImage[balls.length + 1];
		for (int i = 0; i < ballsImages.length; i++) {
			switch (i) {
			case 0:
				resImageId = LinesResources.IMAGE_BALL_EMPTY;
				break;
			case 1:
				resImageId = LinesResources.IMAGE_BALL_BLUE;
				break;
			case 2:
				resImageId = LinesResources.IMAGE_BALL_GREEN;
				break;
			case 3:
				resImageId = LinesResources.IMAGE_BALL_YELLOW;
				break;
			case 4:
				resImageId = LinesResources.IMAGE_BALL_RED;
				break;
			case 5:
				resImageId = LinesResources.IMAGE_BALL_WHITE;
				break;
			case 6:
				resImageId = LinesResources.IMAGE_BALL_ORANGE;
				break;
			case 7:
				resImageId = LinesResources.IMAGE_BALL_BLACK;
				break;
			default:
				resImageId = LinesResources.IMAGE_BALL_EMPTY;
			}
			ballsImages[i] = (RImage) ResourceManager.getInstance().getResource(resImageId);
		}
		futureBallsImages = new AppImage[ballsImages.length][];
		for (int i = 0; i < ballsImages.length; i++) {
			futureBallsImages[i] = new AppImage[SELECTED_BALL_ANIMATION_PERIOD];
			futureBallsImages[i][0] = ballsImages[i].getImage().getScaled(0.5f, 0.5f);
			futureBallsImages[i][1] = ballsImages[i].getImage().getScaled(0.99f, 0.99f);
			futureBallsImages[i][2] = ballsImages[i].getImage().getScaled(0.98f, 0.98f);
			futureBallsImages[i][3] = ballsImages[i].getImage().getScaled(0.97f, 0.97f);
			futureBallsImages[i][4] = ballsImages[i].getImage().getScaled(0.96f, 0.96f);
			futureBallsImages[i][5] = ballsImages[i].getImage().getScaled(0.95f, 0.95f);
			futureBallsImages[i][6] = ballsImages[i].getImage().getScaled(0.94f, 0.94f);
		}
		RImage wallSpriteImage = ResourceManager.getInstance().getImageResource(LinesResources.IMAGE_SPRITE_WALL); 
		wallAnimation = new Sprite(wallSpriteImage, 20, 1000/*milliseconds*/, false, getView());
		selectedX = NO_SELECTION;
		selectedY = NO_SELECTION;
		alphaCompositeMap = new float[map.length][];
		screenGrid = new short[map.length][];
		removedWallAnimationMap = new Sprite[map.length][];
		for (int i = 0; i < map.length; i++) {
			alphaCompositeMap[i] = new float[map[i].length];
			screenGrid[i] = new short[map[i].length];
			removedWallAnimationMap[i] = new Sprite[map[i].length];
		}
	}

	public synchronized void cellSelected(int column, int raw, short value) {
		if (column == selectedX && raw == selectedY) {
			selectedX = NO_SELECTION;
			selectedY = NO_SELECTION;
			repaintGrid();
		} else if (Level.hasBall(value)) {
			selectedX = column;
			selectedY = raw;
			selectedBallAnimationDirection = true;
			selectedBallAnimationY = 0;
			repaintGrid();
		} else if (Level.isEmptyGround(value) && selectedX != NO_SELECTION){
			int oldSelectionX = selectedX;
			int oldSelectionY = selectedY;
			selectedX = NO_SELECTION;
			selectedY = NO_SELECTION;
			getView().getUICommander().post(LinesCommand.COMMAND_GAME_MOVE_BALL, oldSelectionX, oldSelectionY, column,
					raw);
		}
	}

	public void drawCell(AppGraphics gr, int x, int y, int column, int raw, short value) {
		if (selectedX == column && selectedY == raw) {
			// draw ball in selected state
			drawSelected(gr, x, y, value);
		} else {
			if (!Level.hasNoCell(value)) {
				if (Level.isWall(value)) {
					wallAnimation.setX(x);
					wallAnimation.setY(y);
					wallAnimation.paint(gr);
				} else {
					ballsImages[0].paint(gr, x, y);
				}
				if (removedWallAnimationMap[column][raw] != null) {
					Sprite anim = removedWallAnimationMap[column][raw];
					if (anim.isActive()) {
						anim.setX(x);
						anim.setY(y);
						anim.paint(gr);
					} else {
						removedWallAnimationMap[column][raw] = null;
					}
				}
				if (Level.hasFuture(value)) {
					gr.drawImage(futureBallsImages[Level.getFutureNumber(value)][0],
							x + LinesResources.CELL_WIDTH / 4, y + LinesResources.CELL_HEIGHT / 4, null);
				} else if (Level.hasBall(value)) {
					ballsImages[Level.getBallNumber(value)].paint(gr, x, y);
				} else if (Level.hasBall(screenGrid[column][raw])) {
					if (Math.abs(alphaCompositeMap[column][raw]) > 0.1f) {
						gr.setAlphaComposite(alphaCompositeMap[column][raw]);
						alphaCompositeMap[column][raw] -= 0.2f;
						ballsImages[Level.getBallNumber(screenGrid[column][raw])].paint(gr, x, y);
						gr.setAlphaComposite(1.0f);
					} else {
						screenGrid[column][raw] = value;
						gr.setAlphaComposite(1.0f);
					}
				}
			}
		}
	}

	public void drawCursor(AppGraphics gr, int x, int y, int column, int raw, short value) {
	}

	private synchronized void drawSelected(AppGraphics gr, int x, int y, short value) {
		if (!Level.hasNoCell(value)) {
			ballsImages[0].paint(gr, x, y);
			if (Level.hasBall(value)) {
				// ballsImages[value].paint(gr, x, y - selectedBallAnimationY);
				gr.drawImage(futureBallsImages[Level.getBallNumber(value)][selectedBallAnimationY + 1], x + selectedBallAnimationY / 2, y
						+ selectedBallAnimationY / 2, null);
			}
		}
	}

	public void show() {
		getView().getUpdater().addComponent(this);
	}

	public void dismiss() {
		getView().getUpdater().removeComponent(this);
	}

	public synchronized void tick() {
		if (selectedBallAnimationDirection) {
			selectedBallAnimationY++;
		} else {
			selectedBallAnimationY--;
		}
		if (selectedBallAnimationY == 0) {
			selectedBallAnimationDirection = true;
		} else if (selectedBallAnimationY == SELECTED_BALL_ANIMATION_PERIOD - 2) {
			selectedBallAnimationDirection = false;
		}
	}

	public void updateLevel() {
		repaintGrid();
	}

	public void updateLevel(short[][] map) {
		updateGrid(map);
	}

	protected void updateGrid(short[][] grid) {
		short[][] origGrid = getGrid();
		for (int i = 0; i < origGrid.length; i++) {
			for (int j = 0; j < origGrid[i].length; j++) {
				if (origGrid[i][j] != grid[i][j]) {
					if (Level.hasBall(origGrid[i][j]) && !Level.hasBall(grid[i][j])) {
						alphaCompositeMap[i][j] = 1.0f;
						screenGrid[i][j] = origGrid[i][j];
					}
					if (Level.isWall(origGrid[i][j]) && !Level.isWall(grid[i][j])) {
						removedWallAnimationMap[i][j] = new Sprite(wallAnimation);
						removedWallAnimationMap[i][j].start();
					}
				}
				origGrid[i][j] = grid[i][j];
			}
		}
		repaintGrid();
	}
}
