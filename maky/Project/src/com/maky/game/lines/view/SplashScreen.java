package com.maky.game.lines.view;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.ICommandListener;
import com.maky.app.resource.RImage;
import com.maky.app.resource.ResourceManager;
import com.maky.app.view.ActiveElement;
import com.maky.app.view.IScreen;
import com.maky.app.view.Screen;
import com.maky.app.view.View;
import com.maky.environment.AppColor;
import com.maky.environment.AppGraphics;
import com.maky.environment.AppImage;
import com.maky.environment.GraphicsConfig;
import com.maky.environment.IPaintable;
import com.maky.game.lines.LinesConfig;
import com.maky.game.lines.LinesResources;
import com.maky.util.timing.IUpdateble;

public class SplashScreen extends Screen implements IUpdateble {

	public static final int BUTTON_X = LinesConfig.WIDTH / 2 - GraphicsConfig.getScaledX(130 / 2);
	public static final int BUTTON_Y = LinesConfig.HEIGHT - LinesConfig.HEIGHT / 4 - GraphicsConfig.getScaledY(74 / 2);

	private RImage background = ResourceManager.getInstance().getImageResource(LinesResources.IMAGE_SPLASH_BACKGROUND);
	private ActiveElement button;

	private final AppImage buf;
	private final AppGraphics bufgr;

	private boolean showButton;
	private int fade;
	private static final int FADE_TIME_SECONDS = 3;

	public SplashScreen(View view) {
		super(view);
		button = new ActiveElement(BUTTON_X, BUTTON_Y, LinesResources.IMAGE_SPLASH_BUTTON_OK,
				LinesResources.IMAGE_SPLASH_BUTTON_OK_HIGHLIGHTED, Command.COMMAND_SELECT, view);
		view.getUpdater().addComponent(this);
		showButton = false;
		buf = view.createBufferImage(getWidth(), getHeight());
		bufgr = buf.getGraphics();
	}

	/**
	 * {@link IScreen#show()}
	 */
	public void show() {
		getView().getController().addListener(this);
		getView().getController().addListener(button);
	}

	/**
	 * {@link IScreen#dismiss()}
	 */
	public void dismiss() {
		getView().getController().removeListener(this);
		getView().getController().removeListener(button);
	}

	/**
	 * {@link ICommandListener#handle(Command)}
	 */
	public int handle(Command command) {
		int result = Command.RESULT_COMMAND_SKIPPED;
		switch (command.getId()) {
		case Command.COMMAND_SELECT:
			result = Command.RESULT_COMMAND_PROCESSED;
			if (showButton) {
				((LinesView) getView()).showMenu();
			} else {
				fade = FADE_TIME_SECONDS * GraphicsConfig.FRAMES_PER_SECOND;
			}
			break;
		}
		return result;
	}

	/**
	 * {@link IUpdateble#tick()}
	 */
	public void tick() {
		if (fade < FADE_TIME_SECONDS * GraphicsConfig.FRAMES_PER_SECOND) {
			fade++;
		} else {
			showButton = true;
			getView().getUpdater().removeComponent(this);
		}
	}

	/**
	 * {@link IPaintable#paint(AppGraphics)}
	 */
	public void paint(AppGraphics gr) {
		bufgr.setColor(AppColor.BLACK);
		bufgr.fillRect(0, 0, GraphicsConfig.getCanvasWidth(), GraphicsConfig.getCanvasHeight());
		if (fade < FADE_TIME_SECONDS * GraphicsConfig.FRAMES_PER_SECOND) {
			bufgr.setAlphaComposite((fade * ((float) 1 / (float) (FADE_TIME_SECONDS * GraphicsConfig.FRAMES_PER_SECOND))));
		}
		bufgr.drawImage(background.getImage(), 0, 0, null);
		if (showButton) {
			button.paint(bufgr);
		}
		gr.drawImage(buf, 0, 0, null);
	}

}
