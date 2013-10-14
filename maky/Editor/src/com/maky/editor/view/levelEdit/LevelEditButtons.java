package com.maky.editor.view.levelEdit;

import com.maky.app.controller.commander.Command;
import com.maky.app.view.View;
import com.maky.app.view.graphics.elements.Grid;
import com.maky.environment.AppColor;
import com.maky.environment.AppGraphics;

public class LevelEditButtons extends Grid {

	private static final int BUTTON_SAVE_LEVEL = 1;
	private static final int BUTTON_CLEAR_LEVEL = 2;

	public LevelEditButtons(int x, int y, int bodyWidth, int bodyHeight, int thickness, View view) {
		super(x, y, bodyWidth, bodyHeight, thickness, new short[][] { { BUTTON_SAVE_LEVEL }, { BUTTON_CLEAR_LEVEL } },
				view);
	}

	public void cellSelected(int column, int raw, short value) {
		switch (value) {
		case BUTTON_SAVE_LEVEL:
			getView().getUICommander().post(Command.COMMAND_RESOURCE_EDITOR_SAVE_LEVEL);
			break;
		case BUTTON_CLEAR_LEVEL:
			getView().getUICommander().post(Command.COMMAND_RESOURCE_EDITOR_CLEAR_LEVEL);
			break;
		default:
			break;
		}
	}

	public void drawCell(AppGraphics gr, int x, int y, int column, int raw, short value) {
		AppColor color;
		if (value == BUTTON_SAVE_LEVEL) {
			color = AppColor.ORANGE;
		}
		if (value == BUTTON_CLEAR_LEVEL) {
			color = AppColor.GREEN;
		} else {
			color = AppColor.BLACK;
		}
		gr.setColor(color);
		gr.fillRect(x, y, cellWidth, cellHeight);
	}

	public void drawCursor(AppGraphics gr, int x, int y, int column, int raw, short value) {
		drawCell(gr, x, y, column, raw, value);
	}

}
