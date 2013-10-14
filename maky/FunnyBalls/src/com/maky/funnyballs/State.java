package com.maky.funnyballs;

import com.maky.funnyballs.utility.Logger;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class State {

	private final SharedPreferences data;
	
	//Description of the game state
	private String IS_NEW = "isNew";
	private String MAX_PLAY_LEVEL = "maxPlayLevel";
	private String CURRENT_LEVEL = "currentLevel";
	private String CURRENT_SUB_LEVEL = "currentSubLevel";
	private String SCORE = "score";

	private final boolean IS_NEW_DEFAULT = true;
	private final int MAX_PLAY_LEVEL_DEFAULT = 0;
	private final int MAX_LEVEL = LinesResources.maps.length - 1;
	private final int MAX_SUB_LEVEL = LinesResources.maps[MAX_PLAY_LEVEL_DEFAULT].length - 1;
	private final int CURRENT_LEVEL_DEFAULT = 0;
	private final int CURRENT_SUB_LEVEL_DEFAULT = 0;

	public State(SharedPreferences preferences) {
		this.data = preferences;
	}
	
	public void nextLevel() {
		if (getCurrentSubLevel() < MAX_SUB_LEVEL) {
			setCurrentSubLevel(getCurrentSubLevel() + 1);
		} else if (getCurrentLevel() < LinesResources.maps.length) {
			setCurrentLevel(getCurrentLevel() + 1);
			setCurrentSubLevel(0);
			int nextLevel = getMaxPlayLevel();
			if (nextLevel < MAX_LEVEL) {
				nextLevel++;
			}
			setMaxPlayLevel(nextLevel);
		}
	}

	public void clear() {
		setNew(IS_NEW_DEFAULT);
		setMaxPlayLevel(MAX_PLAY_LEVEL_DEFAULT);
		setCurrentLevel(CURRENT_LEVEL_DEFAULT);
		setCurrentSubLevel(CURRENT_SUB_LEVEL_DEFAULT);
	}

	public void setNew(boolean isNew) {
		Editor editor = data.edit();
		editor.putBoolean(IS_NEW, isNew);
		editor.commit();
	}

	public boolean isNew() {
		return data.getBoolean(IS_NEW, IS_NEW_DEFAULT);
	}

	public void setMaxPlayLevel(int maxPlayLevel) {
		Editor editor = data.edit();
		editor.putInt(MAX_PLAY_LEVEL, maxPlayLevel);
		editor.commit();
	}

	public int getMaxPlayLevel() {
		int levelNumer = data.getInt(MAX_PLAY_LEVEL, MAX_PLAY_LEVEL_DEFAULT);
		if (levelNumer > MAX_LEVEL) {
			// TODO: Maybe set to 0
			setMaxPlayLevel(MAX_LEVEL);
			levelNumer = MAX_LEVEL;
			Logger.getInstance(this).logError("There error in state. Level is too high.");
		}
		return levelNumer;
	}

	public void setCurrentLevel(int currentLevel) {
		Editor editor = data.edit();
		editor.putInt(CURRENT_LEVEL, currentLevel);
		editor.commit();
	}

	public int getCurrentLevel() {
		return data.getInt(CURRENT_LEVEL, CURRENT_LEVEL_DEFAULT);
	}

	public void setCurrentSubLevel(int currentSubLevel) {
		Editor editor = data.edit();
		editor.putInt(CURRENT_SUB_LEVEL, currentSubLevel);
		editor.commit();
	}

	public int getCurrentSubLevel() {
		return data.getInt(CURRENT_SUB_LEVEL, CURRENT_SUB_LEVEL_DEFAULT);
	}

	public void setScore(int score) {
		Editor editor = data.edit();
		editor.putInt(SCORE, score);
		editor.commit();
	}

	public int getScore() {
		return data.getInt(SCORE, 0);
	}
}
