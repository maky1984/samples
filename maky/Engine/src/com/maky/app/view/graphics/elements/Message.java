package com.maky.app.view.graphics.elements;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.ICommandListener;
import com.maky.app.view.View;
import com.maky.environment.AppColor;
import com.maky.environment.AppFont;
import com.maky.environment.AppGraphics;
import com.maky.environment.GraphicsConfig;

public class Message extends Frame implements ICommandListener {

	private String message;
	private AppFont font;
	
	public Message(Text text) {
		super(GraphicsConfig.getCenteredTextX(text.getWidth()), GraphicsConfig.getCenteredTextY(text.getHeight()), text.getWidth(),
				text.getHeight(), GraphicsConfig.getScaledX(2), text.getView());
		message = text.getString();
		setColor(AppColor.YELLOW);
		font = text.getFont();
	}

	public Message(String text, View view) {
		this(new Text(text, GraphicsConfig.getDefaultFont(), view));
	}

	public void paint(AppGraphics gr) {
		super.paint(gr);
		gr.setColor(AppColor.BLUE);
		gr.fillRect(getBodyLeftTopX(), getBodyLeftTopY(), bodyWidth, bodyHeight);
		gr.setColor(AppColor.ORANGE);
		gr.setFont(font);
		gr.drawString(message, getBodyLeftTopX(), getBodyLeftTopY() + 10);
	}

	public int handle(Command command) {
		int result = Command.RESULT_COMMAND_PROCESSED;
		switch (command.getId()) {
		case Command.COMMAND_ESC:
		case Command.COMMAND_SELECT:
			getView().removeMessage();
			getView().getUICommander().post(Command.COMMAND_MESSAGE_REMOVED);
			break;
		}
		return result;
	}
	
	public void dismiss() {
		
	}

}
