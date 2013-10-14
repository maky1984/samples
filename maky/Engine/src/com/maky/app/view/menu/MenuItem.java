package com.maky.app.view.menu;

import com.maky.environment.AppColor;
import com.maky.environment.AppGraphics;
import com.maky.environment.GraphicsConfig;
import com.maky.environment.IPaintable;
import com.maky.util.timing.IUpdateble;

public class MenuItem implements IPaintable, IUpdateble {

	private final String title;
	private final String description;
	private boolean isActive;
	private final int commandId;

	private static final int WIDTH = GraphicsConfig.getCanvasWidth() - GraphicsConfig.getScaledX(100);
	private static final int HEIGHT = GraphicsConfig.getScaledY(100);

	public MenuItem(String title, String description, int commandId) {
		this.title = title;
		this.description = description;
		this.commandId = commandId;
	}

	public void paint(AppGraphics gr) {
		if (isActive) {
			gr.setColor(AppColor.GREEN);
		} else {
			gr.setColor(AppColor.BLACK);
		}
		// Paint background
		gr.fillRect(0, 0, WIDTH, HEIGHT);
		if (isActive) {
			gr.setColor(AppColor.BLACK);
		} else {
			gr.setColor(AppColor.GREEN);
		}
		// Paint menu title
		gr.drawString(title, 5, 10);
		// Paint tips (menu description)
		if (isActive) {
			gr.drawString(description, 0, HEIGHT);
		}
	}

	public void setActive() {
		isActive = true;
	}

	public void resetActive() {
		isActive = false;
	}

	public void tick() {

	}

	public int getCommandId() {
		return commandId;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

}
