package com.maky.game.lines.model;

import java.util.Iterator;

import com.maky.app.model.Model;
import com.maky.app.resource.RLinesMap;
import com.maky.app.resource.ResourceManager;
import com.maky.game.lines.LinesResources;
import com.maky.game.lines.model.level.Level;
import com.maky.game.lines.view.ILinesView;
import com.maky.util.log.Logger;

public class LinesModel extends Model implements ILinesModel {

	private Level level;
	private int levelNumber;
	private int score;

	public void setLevel(int number) {
		levelNumber = number;
		int res;
		switch (number) {
		case Level.UNDEFINED:
		case Level.L1:
			res = LinesResources.MAP_LEVEL1;
			break;
		case Level.L2:
			res = LinesResources.MAP_LEVEL2;
			break;
		case Level.L3:
			res = LinesResources.MAP_LEVEL3;
			break;
		case Level.L4:
			res = LinesResources.MAP_LEVEL4;
			break;
		case Level.L5:
			res = LinesResources.MAP_LEVEL5;
			break;
		case Level.L6:
			res = LinesResources.MAP_LEVEL6;
			break;
		case Level.L7:
			res = LinesResources.MAP_LEVEL7;
			break;
		default:
			Logger.getInstance(this).logError("ERROR Unknown level number " + number);
			return;
		}
		RLinesMap levelRes = (RLinesMap) ResourceManager.getInstance().getResource(res);
		level = new Level(levelRes.getLevelData(), levelRes.getBalls(), this);
		score = 0;
		notifyView(ILinesView.NOTIFY_START_LEVEL);
	}

	public void notifyView(int notificationType) {
		Iterator it = views.iterator();
		while (it.hasNext()) {
			ILinesView view = (ILinesView) it.next();
			switch (notificationType) {
			case ILinesView.NOTIFY_BONUS:
				view.notifyBonus(0);
				break;
			case ILinesView.NOTIFY_HIDE_MENU:
				view.hideMenu();
				break;
			case ILinesView.NOTIFY_SHOW_MENU:
				view.showMenu();
				break;
			case ILinesView.NOTIFY_START_LEVEL:
				view.startLevel(level.getMap(), level.getBalls(), levelNumber, score);
				break;
			case ILinesView.NOTIFY_STOP_LEVEL:
				view.stopLevel();
				break;
			case ILinesView.NOTIFY_UPDATE_CELL:
				view.updateCell(0, 0, (short)0);
				break;
			case ILinesView.NOTIFY_UPDATE_LEVEL:
				view.updateLevel(level.getMap());
				break;
			case ILinesView.NOTIFY_UPDATE_SCORE:
				view.updateScore(score);
				break;
			case ILinesView.NOTIFY_WIN:
				view.win();
				break;
			case ILinesView.NOTIFY_LOSE:
				view.lose();
				break;
			default:
				Logger.getInstance(this).logError(" ERROR unknown notification from model to view");
			}
		}
	}

	public void selectCell(int column, int raw) {
		level.selectCell(column, raw);
	}

	public void moveBall(int fromCol, int fromRaw, int toCol, int toRaw) {
		int oldScore = score;
		score = level.moveBall(fromCol, fromRaw, toCol, toRaw, score);
		notifyView(ILinesView.NOTIFY_UPDATE_LEVEL);
		if (oldScore != score) {
			notifyView(ILinesView.NOTIFY_UPDATE_SCORE);
		}
		if (level.isLose()) {
			notifyView(ILinesView.NOTIFY_STOP_LEVEL);
			notifyView(ILinesView.NOTIFY_LOSE);
		} else if (level.isWin()) {
			notifyView(ILinesView.NOTIFY_STOP_LEVEL);
			notifyView(ILinesView.NOTIFY_WIN);			
		}
	}

}
