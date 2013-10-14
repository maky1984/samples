package com.maky.game.lines.view;

import com.maky.app.controller.commander.Command;
import com.maky.app.resource.RImage;
import com.maky.app.resource.ResourceManager;
import com.maky.app.view.ActiveElement;
import com.maky.app.view.Screen;
import com.maky.app.view.View;
import com.maky.environment.AppColor;
import com.maky.environment.AppFont;
import com.maky.environment.AppGraphics;
import com.maky.environment.GraphicsConfig;
import com.maky.game.lines.LinesResources;
import com.maky.game.lines.controller.LinesCommand;

public class LevelMapScreen extends Screen {

	private ActiveElement goToPlayLevelButton;

	private RImage levelMapImage;
	private int currentOpenLevel;
	private int currentChosenLevel;

	public LevelMapScreen(View view, int currentOpenLevel) {
		super(view);
		this.currentOpenLevel = currentOpenLevel;
		currentChosenLevel = currentOpenLevel;
		levelMapImage = ResourceManager.getInstance().getImageResource(LinesResources.IMAGE_LEVEL_MAP_BACKGROUND);
		goToPlayLevelButton = new ActiveElement(SplashScreen.BUTTON_X, SplashScreen.BUTTON_Y,
				LinesResources.IMAGE_SPLASH_BUTTON_OK, LinesResources.IMAGE_SPLASH_BUTTON_OK_HIGHLIGHTED,
				Command.COMMAND_SELECT, view);
	}

	public void setCurrentOpenLevel(int level) {
		currentOpenLevel = level;
	}

	public void show() {
		getView().getController().addListener(goToPlayLevelButton);
		getView().getController().addListener(this);
	}

	public void dismiss() {
		getView().getController().removeListener(goToPlayLevelButton);
		getView().getController().removeListener(this);
	}

	public void paint(AppGraphics gr) {
		levelMapImage.paint(gr, 0, 0);
		// TODO: temporary solution
		gr.setFont(GraphicsConfig.getDefaultFont());
		gr.setColor(AppColor.WHITE);
		gr.drawString("Current open level:" + String.valueOf(currentOpenLevel), 0, 20);
		gr.drawString("Current chosen level:" + String.valueOf(currentChosenLevel), 0, 40);
		goToPlayLevelButton.paint(gr);
	}

	public int handle(Command command) {
		int result = Command.RESULT_COMMAND_SKIPPED;
		switch (command.getId()) {
		case Command.COMMAND_UP:
			if (currentChosenLevel < currentOpenLevel) {
				currentChosenLevel++;
			}
			result = Command.RESULT_COMMAND_PROCESSED;
			break;
		case Command.COMMAND_DOWN:
			if (currentChosenLevel > 0) {
				currentChosenLevel--;
			}
			result = Command.RESULT_COMMAND_PROCESSED;
			break;
		case Command.COMMAND_SELECT:
			getView().getUICommander().post(LinesCommand.COMMAND_GAME_LEVEL_CHOOSEN, currentChosenLevel);
			result = Command.RESULT_COMMAND_PROCESSED;
			break;
		}
		return result;
	}

}
