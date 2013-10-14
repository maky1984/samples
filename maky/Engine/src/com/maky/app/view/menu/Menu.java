package com.maky.app.view.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.ICommandListener;
import com.maky.app.view.View;
import com.maky.app.view.graphics.elements.Frame;
import com.maky.app.view.graphics.elements.GraphicElement;
import com.maky.environment.AppGraphics;
import com.maky.util.log.Logger;

public abstract class Menu extends GraphicElement implements ICommandListener {

	private List items;
	private int activeNumber;

	private Frame frame;

	public Menu(int x, int y, int width, int height, int defaultActive, boolean useFrame, View view) {
		super(view);
		items = new ArrayList();
		activeNumber = defaultActive;
		if (useFrame) {
			frame = new Frame(width, height, Frame.THICKNESS_IMAGES_USED, getView());
		} else {
			frame = new Frame(x, y, width, height, 0, view);
		}
		setX(x);
		setY(y);
		buildMenu();
		MenuItem item = (MenuItem) items.get(defaultActive);
		if (item != null) {
			item.setActive();
		} else {
			Logger.getInstance(this).logError(
					" Error in Menu constructor. Defualt active number not found " + defaultActive);
		}
	}

	public Menu(int width, int height, int defaultActive, View view) {
		this(Frame.CENTER, Frame.CENTER, width, height, defaultActive, true, view);
	}

	protected abstract void buildMenu();

	protected abstract int itemSelected(MenuItem item);

	public void addItem(MenuItem item) {
		items.add(item);
	}

	public void paint(AppGraphics gr) {
		frame.paint(gr);
		int menuItemOffsetY = frame.getBodyLeftTopY();
		int menuItemOffsetX = frame.getBodyLeftTopX();
		Iterator it = items.iterator();
		while (it.hasNext()) {
			MenuItem item = (MenuItem) it.next();
			gr.translate(menuItemOffsetX, menuItemOffsetY);
			item.paint(gr);
			gr.translate(-menuItemOffsetX, -menuItemOffsetY);
			menuItemOffsetY += MenuItem.getHeight();
		}
	}

	public int handle(Command command) {
		int result = Command.RESULT_COMMAND_SKIPPED;
		switch (command.getId()) {
		// Move menu cursor down
		case Command.COMMAND_DOWN:
			if (activeNumber < 2) {
				MenuItem item = (MenuItem) items.get(activeNumber);
				item.resetActive();
				activeNumber++;
				item = (MenuItem) items.get(activeNumber);
				item.setActive();
			}
			result = Command.RESULT_COMMAND_PROCESSED;
			break;
		// Move menu cursor up
		case Command.COMMAND_UP:
			if (activeNumber > 0) {
				MenuItem item = (MenuItem) items.get(activeNumber);
				item.resetActive();
				activeNumber--;
				item = (MenuItem) items.get(activeNumber);
				item.setActive();
			}
			result = Command.RESULT_COMMAND_PROCESSED;
			break;
		case Command.COMMAND_SELECT:
			int mouseX = command.getParam(0);
			if (mouseX != Command.UNDEFINED) {
				// Mouse event onClick
				int mouseY = command.getParam(1);
				mouseX -= frame.getBodyLeftTopX();
				mouseY -= frame.getBodyLeftTopY();
				if (mouseX > 0 && mouseX < frame.getBodyWidth() && mouseY > 0 && mouseY < frame.getBodyHeight()) {
					mouseY = mouseY / MenuItem.getHeight();
					((MenuItem) items.get(activeNumber)).resetActive();
					activeNumber = mouseY;
					MenuItem item = (MenuItem) items.get(mouseY);
					item.setActive();
					result = itemSelected(item);
				} else {
					result = Command.RESULT_COMMAND_SKIPPED;
				}
			} else {
				MenuItem item = (MenuItem) items.get(activeNumber);
				result = itemSelected(item);
			}
			break;
		}
		return result;
	}
}
