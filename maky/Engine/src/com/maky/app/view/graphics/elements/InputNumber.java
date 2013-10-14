package com.maky.app.view.graphics.elements;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.ICommandListener;
import com.maky.app.view.View;
import com.maky.environment.AppColor;
import com.maky.environment.AppFont;
import com.maky.environment.AppFontMetrics;
import com.maky.environment.AppGraphics;
import com.maky.environment.GraphicsConfig;
import com.maky.util.log.Logger;

public class InputNumber extends Frame implements ICommandListener {

	private final String title;
	private volatile char[] string;
	private final AppFont font;
	private final int fontAscent;
	private final int fontDescent;

	public InputNumber(int x, int y, Text text, int borderThickness, View view) {
		super(x, y, text.getWidth(), text.getHeight() * 2, borderThickness, view);
		setColor(AppColor.RED);
		string = new char[0];
		this.title = text.getString();
		font = GraphicsConfig.getDefaultFont();
		AppFontMetrics fontMetrics = (view.getCanvas()).getFontMetrics(font);
		fontAscent = fontMetrics.getMaxAscent();
		fontDescent = fontMetrics.getMaxDescent();
	}

	public void paint(AppGraphics gr) {
		super.paint(gr);
		gr.setColor(AppColor.WHITE);
		gr.fillRect(getBodyLeftTopX(), getBodyLeftTopY(), bodyWidth, bodyHeight);
		gr.setColor(AppColor.BLACK);
		gr.setFont(this.font);
		gr.drawString(title, getBodyLeftTopX(), getBodyLeftTopY() + fontAscent);
		gr.drawChars(string, 0, string.length, getBodyLeftTopX(), getBodyLeftTopY() + bodyHeight - fontDescent);
	}

	public int handle(Command command) {
		int result = Command.RESULT_COMMAND_PROCESSED;
		switch (command.getId()) {
		case Command.COMMAND_NUMBER:
			if (string.length < 10) {
				int param = command.getParam(0);
				char[] newString = new char[string.length + 1];
				for (int i = 0; i < string.length; i++) {
					newString[i] = string[i];
				}
				newString[string.length] = (char) param;
				string = newString;
			} else {
				result = Command.RESULT_COMMAND_SKIPPED;
			}
			break;
		case Command.COMMAND_BACK:
			if (string.length > 0) {
				char[] newString = new char[string.length - 1];
				for (int i = 0; i < string.length - 1; i++) {
					newString[i] = string[i];
				}
				string = newString;
			} else {
				result = Command.RESULT_COMMAND_SKIPPED;
			}
			break;
		case Command.COMMAND_SELECT:
			int value;
			try {
				value = Integer.parseInt(String.valueOf(string));
				getView().getUICommander().post(Command.COMMAND_INPUT_NUMBER_ENTERED, value);
			} catch (NumberFormatException ex) {
				value = 0;
				Logger.getInstance(this).logError(" InputNumber error ");
				Logger.getInstance(this).logException(ex);
				getView().showMessage(new Message(" Error in number: " + ex.getMessage(), getView()));
			}
			break;
		default:
			result = Command.RESULT_COMMAND_SKIPPED;
			break;
		}
		return result;
	}
}
