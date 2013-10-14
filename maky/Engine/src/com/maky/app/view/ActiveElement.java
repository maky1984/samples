package com.maky.app.view;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.ICommandListener;
import com.maky.app.resource.RImage;
import com.maky.app.resource.ResourceManager;
import com.maky.app.view.graphics.elements.GraphicElement;
import com.maky.environment.AppGraphics;
import com.maky.util.log.Logger;

public class ActiveElement extends GraphicElement implements ICommandListener {

	public static final int NOT_VISIBLE = 0;
	public static final int VISIBLE_NOT_ACTIVE = 1;
	public static final int ACTIVE = 2;
	public static final int SELECTED = 3;

	private volatile int state = NOT_VISIBLE;
	private final RImage imageHighlited;
	private final RImage image;
	private final int width;
	private final int height;
	private final int commandId;

	public ActiveElement(int x, int y, int imageResId, int highlightedImageresId, int commandId, View view) {
		super(x, y, view);
		imageHighlited = ResourceManager.getInstance().getImageResource(imageResId);
		image = ResourceManager.getInstance().getImageResource(highlightedImageresId);
		width = image.getImage().getWidth();
		height = image.getImage().getHeight();
		state = VISIBLE_NOT_ACTIVE;
		this.commandId = commandId;
	}

	public int handle(Command command) {
		int result = Command.RESULT_COMMAND_SKIPPED;
		if (command.getId() == Command.COMMAND_MOUSE_MOVED) {
			int x = command.getParam(0);
			int y = command.getParam(1);
			if (x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + height) {
				if (state == VISIBLE_NOT_ACTIVE) {
					state = ACTIVE;
				}
			} else if (state == ACTIVE) {
				state = VISIBLE_NOT_ACTIVE;
			}
		} else if (command.getId() == Command.COMMAND_SELECT && command.getParam(0) != Command.UNDEFINED) {
			int x = command.getParam(0);
			int y = command.getParam(1);
			if (x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + height) {
				getView().getUICommander().post(commandId);
			}
			result = Command.RESULT_COMMAND_PROCESSED;
		}
		return result;
	}

	public void paint(AppGraphics gr) {
		switch (state) {
		case NOT_VISIBLE:
			break;
		case VISIBLE_NOT_ACTIVE:
			gr.drawImage(image.getImage(), getX(), getY(), null);
			break;
		case ACTIVE:
			gr.drawImage(imageHighlited.getImage(), getX(), getY(), null);
			break;
		case SELECTED:
			gr.drawImage(imageHighlited.getImage(), getX(), getY(), null);
			break;
		default:
			Logger.getInstance(this).logError(" State unknown state = " + state);
			break;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
