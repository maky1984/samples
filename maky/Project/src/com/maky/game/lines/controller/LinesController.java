package com.maky.game.lines.controller;

import com.maky.app.controller.Controller;
import com.maky.app.controller.commander.Command;
import com.maky.game.lines.model.ILinesModel;
import com.maky.game.lines.model.LinesModel;
import com.maky.game.lines.model.level.Level;
import com.maky.game.lines.view.ILinesView;
import com.maky.util.log.Logger;

public class LinesController extends Controller {

	public LinesController(LinesModel model) {
		super(model);
	}

	private ILinesModel getModel() {
		return (ILinesModel) model;
	}

	private ILinesView getView() {
		return (ILinesView) view;
	}

	// Should handle game specific command
	public int handle(Command command) {
		int result = Command.RESULT_COMMAND_PROCESSED;
		switch (command.getId()) {
		case Command.COMMAND_ESC:
		case LinesCommand.COMMAND_GAME_EXIT:
			getModel().exit();
			break;
		case LinesCommand.COMMAND_GAME_NEW:
			getView().hideMenu();
			getView().showLevelMapScreen();
			break;
		case LinesCommand.COMMAND_GAME_HIGHSCORE:
			//getView().hideMenu();
			break;
		case LinesCommand.COMMAND_GAME_LEVEL_CHOOSEN:
			int levelNumber = command.getParam(0);
			Logger.getInstance(this).logDebug("Starting level: " + levelNumber);
			getModel().setLevel(levelNumber);
			break;
		case LinesCommand.COMMAND_GAME_NEXT_LEVEL:
			levelNumber = command.getParam(0);
			levelNumber++;
			Logger.getInstance(this).logDebug("Starting next level: " + levelNumber);
			getModel().setLevel(levelNumber);
			break;			
		case LinesCommand.COMMAND_GAME_MOVE_BALL:
			getModel().moveBall(command.getParam(0), command.getParam(1), command.getParam(2), command.getParam(3));
			break;
		default:
			result = super.handle(command);
			break;
		}
		return result;
	}

}
