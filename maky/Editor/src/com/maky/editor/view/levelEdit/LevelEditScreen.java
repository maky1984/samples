package com.maky.editor.view.levelEdit;

import com.maky.app.controller.commander.Command;
import com.maky.app.view.Screen;
import com.maky.app.view.graphics.elements.Frame;
import com.maky.app.view.graphics.elements.InputNumber;
import com.maky.app.view.graphics.elements.Message;
import com.maky.app.view.graphics.elements.Text;
import com.maky.editor.view.ResourceEditorView;
import com.maky.environment.GraphicsConfig;
import com.maky.util.log.Logger;

public class LevelEditScreen extends Screen {

	private static final String INPUT_ELEMENT_TITLE1 = "Enter column number";
	private static final String INPUT_ELEMENT_TITLE2 = "Enter raw number";

	private static final int STATE_INITIAL = 0;
	private static final int STATE_INPUT_COLUMN = 1;
	private static final int STATE_INPUT_RAW = 2;
	private static final int STATE_EDIT_GRID = 3;

	public static final byte EMPTY_CELL = -1;
	
	private final InputNumber input1;
	private final InputNumber input2;
	private LevelEdit levelGrid;
	private LevelEditValues valuesGrid;
	private LevelEditButtons buttons;

	private int columnNumber;
	private int rawNumber;

	private final int levelHeight;
	private final int levelWidth;

	private int state;

	public LevelEditScreen(ResourceEditorView view) {
		super(view);
		Text text1 = new Text(INPUT_ELEMENT_TITLE1, GraphicsConfig.getDefaultFont(), view);
		Text text2 = new Text(INPUT_ELEMENT_TITLE2, GraphicsConfig.getDefaultFont(), view);
		int x1 = GraphicsConfig.getCanvasWidth() / 2 - text1.getWidth() / 2;
		int x2 = GraphicsConfig.getCanvasWidth() / 2 - text2.getWidth() / 2;
		int y = GraphicsConfig.getCanvasHeight() / 2 - text1.getHeight() / 2;
		input1 = new InputNumber(x1, y, text1, GraphicsConfig.getScaledX(3), view);
		input2 = new InputNumber(x2, y, text2, GraphicsConfig.getScaledX(3), view);
		levelHeight = GraphicsConfig.getCanvasHeight() - GraphicsConfig.getCanvasHeight() / 5;
		levelWidth = GraphicsConfig.getCanvasWidth() - GraphicsConfig.getCanvasWidth() / 5;
		state = STATE_INITIAL;
	}

	public void show() {
		state = STATE_INPUT_COLUMN;
		getView().getController().addListener(input1);
		getView().getPainter().addComponent(input1);
	}

	public void dismiss() {
		switch (state) {
		case STATE_INPUT_COLUMN:
			getView().getController().removeListener(input1);
			getView().getPainter().removeComponent(input1);
			break;
		case STATE_INPUT_RAW:
			getView().getController().removeListener(input2);
			getView().getPainter().removeComponent(input2);
			break;
		case STATE_EDIT_GRID:
			getView().getController().removeListener(levelGrid);
			getView().getPainter().removeComponent(levelGrid);
			getView().getController().removeListener(valuesGrid);
			getView().getPainter().removeComponent(valuesGrid);
			getView().getController().removeListener(buttons);
			getView().getPainter().removeComponent(buttons);
			break;
		case STATE_INITIAL:
		default:
			break;
		}
	}

	private short[][] createDefaultLevel() {
		short[][] newLevel = new short[columnNumber][rawNumber];
		return newLevel;
	}

	private void buildEditPanels() {
		short[][] values = new short[1][5];
		for (int i = 0; i < values[0].length; i++) {
			values[0][i] = (short) (i + 1);
		}
		levelGrid = new LevelEdit(0, 0, levelWidth, levelHeight, Frame.THICKNESS_IMAGES_USED, createDefaultLevel(),
				values[0], (ResourceEditorView) getView());
		int frameSize = GraphicsConfig.getScaledX(5);
		valuesGrid = new LevelEditValues(levelGrid.getWidth(), 0, GraphicsConfig.getCanvasWidth() - levelGrid.getWidth() - 2
				* frameSize, levelHeight, frameSize, values, (ResourceEditorView) getView());
		buttons = new LevelEditButtons(0, levelHeight + 2 * levelGrid.getFrameHeight(), GraphicsConfig.getCanvasWidth() - 2
				* frameSize, GraphicsConfig.getCanvasHeight() - levelGrid.getHeight() - 2 * frameSize, frameSize, getView());
	}

	public int handle(Command command) {
		int result = Command.RESULT_COMMAND_PROCESSED;
		switch (command.getId()) {
		case Command.COMMAND_INPUT_NUMBER_ENTERED:
			int param = command.getParam(0);
			if (state == STATE_INPUT_COLUMN) {
				if (param >= levelWidth) {
					getView().showMessage(new Message("Column number is too big", getView()));
				} else {
					columnNumber = param;
					state = STATE_INPUT_RAW;
					getView().getController().removeListener(input1);
					getView().getPainter().removeComponent(input1);
					getView().getPainter().addComponent(input2);
					getView().getController().addListener(input2);
				}
			} else if (state == STATE_INPUT_RAW) {
				if (param >= levelHeight) {
					getView().showMessage(new Message("Raw number is too big", getView()));
				} else {
					rawNumber = param;
					buildEditPanels();
					levelGrid.clearLevel();
					state = STATE_EDIT_GRID;
					getView().getController().removeListener(input2);
					getView().getPainter().removeComponent(input2);
					getView().getPainter().addComponent(valuesGrid);
					getView().getController().addListener(valuesGrid);
					getView().getPainter().addComponent(buttons);
					getView().getController().addListener(buttons);
					getView().getPainter().addComponent(levelGrid);
					getView().getController().addListener(levelGrid);
				}
			} else {
				Logger.getInstance(this).logError(" Error: wrong state = " + state);
			}
			break;
		case Command.COMMAND_RESOURCE_EDITOR_LEVEL_VALUE_SELECTED:
			int value = command.getParam(0);
			levelGrid.setCursorValue((byte) value);
			break;
		case Command.COMMAND_RESOURCE_EDITOR_SAVE_LEVEL:
			getView().getUICommander().post(Command.COMMAND_RESOURCE_ADD_LEVEL, levelGrid.getLevel());
			break;
		case Command.COMMAND_RESOURCE_EDITOR_CLEAR_LEVEL:
			levelGrid.clearLevel();
			break;
		default:
			result = Command.RESULT_COMMAND_SKIPPED;
			break;
		}
		return result;
	}

}
