package com.maky.app.view.graphics.elements;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.ICommandListener;
import com.maky.app.view.View;
import com.maky.environment.AppColor;
import com.maky.environment.AppGraphics;
import com.maky.environment.AppImage;

public abstract class Grid extends Frame implements ICommandListener {

	private static final int NO_CURSOR = -1;
	
	private final short[][] grid;
	protected final int cellWidth;
	protected final int cellHeight;
	private int cursorX;
	private int cursorY;
	
	// Buffering variables
	private final AppImage buffer;
	private final AppGraphics bufferGraphics;
	private boolean needRepaintBuffer;

	public Grid(int x, int y, int bodyWidth, int bodyHeight, int thickness, short[][] grid, View view) {
		super(x, y, bodyWidth, bodyHeight, thickness, view);
		this.grid = grid;
		int columnNumber = grid.length;
		if (columnNumber > 0) {
			cellWidth = getBodyWidth() / columnNumber;
			int rawNumber = grid[0].length;
			if (rawNumber > 0) {
				cellHeight = getBodyHeight() / rawNumber;
			} else {
				cellHeight = getBodyHeight();
			}
		} else {
			cellWidth = getBodyWidth();
			cellHeight = getBodyHeight();
		}
		buffer = view.createBufferImage(getBodyWidth(), getBodyHeight());
		bufferGraphics = buffer.getGraphics();
		cursorX = NO_CURSOR;
		cursorY = NO_CURSOR;
		needRepaintBuffer = true;
	}

	protected void updateGrid(short[][] grid) {
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				this.grid[i][j] = grid[i][j];
			}
		}
		repaintGrid();
	}

	protected short[][] getGrid() {
		return grid;
	}

	public void paint(AppGraphics gr) {
		super.paint(gr);
		if (needRepaintBuffer) {
			repaintGrid();
		}
		gr.drawImage(buffer, getBodyLeftTopX(), getBodyLeftTopY(), null);
	}

	protected void cleanGrid(byte defaultValue) {
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				this.grid[i][j] = defaultValue;
			}
		}
		repaintGrid();
	}
	
	protected void repaintGrid() {
		bufferGraphics.clearWithColor(AppColor.TRANSP, buffer.getWidth(), buffer.getHeight());
		paintGrid(bufferGraphics, grid);
	}

	protected void repaintCursor() {
		if (cursorX != NO_CURSOR && cursorY != NO_CURSOR) {
			drawCursor(bufferGraphics, cursorX * cellWidth, cursorY * cellHeight, cursorX, cursorY, grid[cursorX][cursorY]);
		}
	}

	protected void paintGrid(AppGraphics gr, short[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			int xOffset = cellWidth * i;
			for (int j = 0; j < grid[i].length; j++) {
				if (cursorX == i && cursorY == j) {
					// draw ball with cursor on it
					drawCursor(gr, xOffset, j * cellHeight, i, j, grid[i][j]);
				} else {
					drawCell(gr, xOffset, j * cellHeight, i, j, grid[i][j]);
				}
			}
		}
	}

	private void paintCursor(int oldCursorX, int oldCursorY) {
		drawCell(bufferGraphics, oldCursorX * cellWidth, oldCursorY * cellHeight, oldCursorX, oldCursorY,
				grid[oldCursorX][oldCursorY]);
		drawCursor(bufferGraphics, cursorX * cellWidth, cursorY * cellHeight, cursorX, cursorY, grid[cursorX][cursorY]);
	}

	protected void setValue(int column, int raw, short value) {
		grid[column][raw] = value;
		drawCell(bufferGraphics, column * cellWidth, raw * cellHeight, column, raw, value);
	}

	public int handle(Command command) {
		int result = Command.RESULT_COMMAND_PROCESSED;
		switch (command.getId()) {
		case Command.COMMAND_SELECT:
			int mouseX = command.getParam(0);
			if (mouseX != Command.UNDEFINED) {
				// Mouse event onClick
				int mouseY = command.getParam(1);
				mouseX -= getBodyLeftTopX();
				mouseY -= getBodyLeftTopY();
				if (mouseX > 0 && mouseX < grid.length * cellWidth && mouseY > 0
						&& mouseY < grid[0].length * cellHeight) {
					mouseX = mouseX / cellWidth;
					mouseY = mouseY / cellHeight;
					cellSelected(mouseX, mouseY, grid[mouseX][mouseY]);
				} else {
					result = Command.RESULT_COMMAND_SKIPPED;
				}
			} else {
				// Keyboard select event
				cellSelected(cursorX, cursorY, grid[cursorX][cursorY]);
				paintCursor(cursorX, cursorY);
			}
			break;
		case Command.COMMAND_LEFT:
			if (cursorX - 1 >= 0) {
				cursorX--;
				paintCursor(cursorX + 1, cursorY);
			} else {
				getView().beep();
			}
			break;
		case Command.COMMAND_RIGHT:
			if (cursorX + 1 < grid.length) {
				cursorX++;
				paintCursor(cursorX - 1, cursorY);
			} else {
				getView().beep();
			}
			break;
		case Command.COMMAND_UP:
			if (cursorY - 1 >= 0) {
				cursorY--;
				paintCursor(cursorX, cursorY + 1);
			} else {
				getView().beep();
			}
			break;
		case Command.COMMAND_DOWN:
			if (cursorY + 1 < grid[0].length) {
				cursorY++;
				paintCursor(cursorX, cursorY - 1);
			} else {
				getView().beep();
			}
			break;
		default:
			result = Command.RESULT_COMMAND_SKIPPED;
			break;
		}
		return result;
	}

	public abstract void cellSelected(int column, int raw, short value);

	public abstract void drawCursor(AppGraphics gr, int x, int y, int column, int raw, short value);

	public abstract void drawCell(AppGraphics gr, int x, int y, int column, int raw, short value);

}
