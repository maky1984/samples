package com.maky.editor.view.levelEdit;

import com.maky.app.view.graphics.elements.Grid;
import com.maky.editor.view.ResourceEditorView;
import com.maky.environment.AppColor;
import com.maky.environment.AppGraphics;

public class LevelEdit extends Grid {

	private short cursorValue;

	public LevelEdit(int offsetX, int offsetY, int bodyWidth, int bodyHeight, int thickness, short[][] grid,
			short[] values, ResourceEditorView view) {
		super(offsetX, offsetY, bodyWidth, bodyHeight, thickness, grid, view);
		if (values.length > 0) {
			cursorValue = values[0];
		}
	}

	public void cellSelected(int column, int raw, short value) {
		setValue(column, raw, cursorValue);
	}

	public void drawCell(AppGraphics gr, int x, int y, int column, int raw, short value) {
		gr.setColor(LevelEditValues.colorByValue(value));
		gr.fillRect(x, y, cellWidth, cellHeight);
	}

	public void drawCursor(AppGraphics gr, int x, int y, int column, int raw, short value) {
		drawCell(gr, x, y, column, raw, value);
		gr.setColor(AppColor.BLACK);
		gr.drawPolyline(new int[] { x, x + cellWidth - 1, x + cellWidth - 1, x, x }, new int[] { y, y,
				y + cellHeight - 1, y + cellHeight - 1, y }, 5);
		gr.drawPolyline(new int[] { x + 2, x + cellWidth - 3, x + cellWidth - 3, x + 2, x + 2 }, new int[] { y + 2,
				y + 2, y + cellHeight - 3, y + cellHeight - 3, y + 2 }, 5);
		gr.setColor(LevelEditValues.colorByValue(cursorValue));
		gr.drawPolyline(new int[] { x + 1, x + cellWidth - 2, x + cellWidth - 2, x + 1, x + 1 }, new int[] { y + 1,
				y + 1, y + cellHeight - 2, y + cellHeight - 2, y + 1 }, 5);
	}

	public void setCursorValue(byte value) {
		cursorValue = value;
		repaintCursor();
	}

	public void clearLevel() {
		cleanGrid(LevelEditScreen.EMPTY_CELL);
	}
	
	public short[][] getLevel() {
		return getGrid();
	}
}
