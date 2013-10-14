package com.maky.game.lines.view;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.ICommandListener;
import com.maky.app.resource.RImage;
import com.maky.app.resource.ResourceManager;
import com.maky.app.view.Screen;
import com.maky.environment.AppGraphics;
import com.maky.environment.GraphicsConfig;
import com.maky.game.lines.LinesResources;
import com.maky.util.log.Logger;

public class GameLevelScreen extends Screen {

	private LevelGrid levelGrid;
	private RImage levelBackground;

	private char[] score;

	public GameLevelScreen(short[][] map, short[] balls, int levelNumber, int score, LinesView view) {
		super(view);
		short[][] levelMap = map;
		if (levelMap.length > 0) {
			ResourceManager RM = ResourceManager.getInstance();
			levelBackground = RM.getImageResource(LinesResources.IMAGE_LEVEL1_BACKGROUND + levelNumber - 1);
			int levelBodyHeight = levelMap[0].length * LinesResources.CELL_HEIGHT;
			int levelBodyWidth = levelMap.length * LinesResources.CELL_WIDTH;
			levelGrid = new LevelGrid(LevelGrid.CENTER, LevelGrid.CENTER, levelBodyWidth, levelBodyHeight, 0, map,
					balls, getView());
		} else {
			Logger.getInstance(this).logError(" ERROR in creting level screen. levelMap.length <= 0");
		}
		updateScore(score);
	}

	public void show() {
		levelGrid.show();
	}

	public void dismiss() {
		levelGrid.dismiss();
	}

	public void paint(AppGraphics gr) {
		levelBackground.paint(gr, GraphicsConfig.getScaledX(0), GraphicsConfig.getScaledY(0));
		levelGrid.paint(gr);
	}

	public void updateLevel() {
		levelGrid.updateLevel();
	}

	public void updateLevel(short[][] map) {
		levelGrid.updateLevel(map);
	}
	
	public void updateCell(int column, int raw, short newValue) {
		
	}

	public void updateScore(int score) {
		Logger.getInstance(this).logInfo("Update score:" + score);
		this.score = String.valueOf(score).toCharArray();
	}

	/**
	 * {@link ICommandListener#handle(Command)}
	 */
	public int handle(Command command) {
		return levelGrid.handle(command);
	}

}
