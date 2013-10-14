package com.maky.editor.view.levelEdit;

import com.maky.app.controller.commander.Command;
import com.maky.app.view.graphics.elements.Grid;
import com.maky.editor.view.ResourceEditorView;
import com.maky.environment.AppColor;
import com.maky.environment.AppGraphics;

public class LevelEditValues extends Grid {

	public LevelEditValues(int x, int y, int bodyWidth, int bodyHeight, int thickness, short[][] values,
			ResourceEditorView view) {
		super(x, y, bodyWidth, bodyHeight, thickness, values, view);
	}

	public void cellSelected(int column, int raw, short value) {
		getView().getUICommander().post(Command.COMMAND_RESOURCE_EDITOR_LEVEL_VALUE_SELECTED, value);
	}

	public static AppColor colorByValue(short value) {
		switch (value) {
		case 0:
			return AppColor.WHITE;
		case 1:
			return AppColor.BLACK;
		case 2:
			return AppColor.GREEN;
		case 3:
			return AppColor.ORANGE;
		case 4:
			return AppColor.RED;
		case 5:
			return AppColor.YELLOW;
		default:
			return AppColor.WHITE;
		}
	}

	public void drawCell(AppGraphics gr, int x, int y, int column, int raw, short value) {
		gr.setColor(colorByValue(value));
		gr.fillRect(x, y, cellWidth, cellHeight);
	}

	public void drawCursor(AppGraphics gr, int x, int y, int column, int raw, short value) {
		drawCell(gr, x, y, column, raw, value);
		gr.setColor(AppColor.RED);
		gr.drawPolyline(new int[] { x, x + cellWidth - 1, x + cellWidth - 1, x, x }, new int[] { y, y,
				y + cellHeight - 1, y + cellHeight - 1, y }, 5);
	}

}
