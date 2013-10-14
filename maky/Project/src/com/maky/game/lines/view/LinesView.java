package com.maky.game.lines.view;

import com.maky.app.controller.Controller;
import com.maky.app.controller.commander.Command;
import com.maky.app.model.Model;
import com.maky.app.view.View;
import com.maky.app.view.graphics.elements.Message;
import com.maky.game.lines.controller.LinesCommand;
import com.maky.game.lines.view.menu.LinesGameMenuV1;

public class LinesView extends View implements ILinesView {

	private class State {
		public int score;
		public int levelNumber;
		public int currentOpenLevel;
	}

	private class WinMessage extends Message {
		public WinMessage(View view) {
			super("You won! Score:" + state.score, view);
		}

		public void dismiss() {
			super.dismiss();
			getView().getUICommander().post(LinesCommand.COMMAND_GAME_NEXT_LEVEL, state.levelNumber);			
		}
	}

	private class LoseMessage extends Message {
		public LoseMessage(View view) {
			super("You lose!", view);
		}

		public void dismiss() {
			super.dismiss();
			showMenu();
		}
	}

	// Main screen elements
	private LinesGameMenuV1 menu;
	private GameLevelScreen level;
	private SplashScreen splash;
	private LevelMapScreen levelMap;
	private State state;

	public LinesView(Controller controller, Model model) {
		super(controller, model);
		state = new State();
		state.levelNumber = 1;
		state.currentOpenLevel = 1;
		splash = new SplashScreen(this);
		menu = new LinesGameMenuV1(commander, this);
		levelMap = new LevelMapScreen(this, 0);
	}

	public void show() {
		showSplashScreen();
	}

	public void showSplashScreen() {
		setCurrentScreen(splash);
	}

	public void hideMenu() {

	}

	public void showMenu() {
		setCurrentScreen(menu);
	}

	public void showLevelMapScreen() {
		levelMap.setCurrentOpenLevel(state.currentOpenLevel);
		setCurrentScreen(levelMap);
	}

	public void startLevel(short[][] map, short[] balls, int levelNumber, int score) {
		state.levelNumber = levelNumber;
		state.score = score;
		short[][] mapCopy = new short[map.length][];
		for (int i = 0; i < map.length; i++) {
			mapCopy[i] = new short[map[i].length];
			for (int j = 0; j < map[i].length; j++) {
				mapCopy[i][j] = map[i][j];
			}
		}
		level = new GameLevelScreen(mapCopy, balls, levelNumber, score, this);
		setCurrentScreen(level);
		level.updateLevel();
	}

	public void updateLevel(short[][] level) {
		this.level.updateLevel(level);
	}

	public void updateCell(int column, int raw, short newValue) {
		level.updateCell(column, raw, newValue);
	}

	public void notifyBonus(int value) {

	}

	public void stopLevel() {

	}

	public void updateScore(int score) {
		state.score = score;
		level.updateScore(score);
	}

	public void win() {
		showMessage(new WinMessage(this));
	}

	public void lose() {
		showMessage(new LoseMessage(this));
	}
}
