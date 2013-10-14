package com.maky.game.lines.view;

public interface ILinesView {

	public static final int NOTIFY_START_LEVEL = 0;
	public static final int NOTIFY_UPDATE_LEVEL = 1;
	public static final int NOTIFY_UPDATE_CELL = 2;
	public static final int NOTIFY_BONUS = 3;
	public static final int NOTIFY_STOP_LEVEL = 4;
	public static final int NOTIFY_SHOW_MENU = 5;
	public static final int NOTIFY_HIDE_MENU = 6;
	public static final int NOTIFY_UPDATE_SCORE = 7;
	public static final int NOTIFY_WIN = 8;
	public static final int NOTIFY_LOSE = 9;

	public void startLevel(short[][] map, short[] balls, int levelNumber, int score);

	public void updateLevel(short[][] level);

	public void updateCell(int column, int raw, short newValue);

	public void notifyBonus(int value);

	public void stopLevel();

	public void updateScore(int score);

	public void showMenu();

	public void hideMenu();
	
	public void showLevelMapScreen();
	
	public void win();
	
	public void lose();
}
