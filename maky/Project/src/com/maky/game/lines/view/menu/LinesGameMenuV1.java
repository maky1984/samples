package com.maky.game.lines.view.menu;

import com.maky.app.controller.commander.UICommander;
import com.maky.app.resource.RImage;
import com.maky.app.resource.ResourceManager;
import com.maky.app.view.IScreen;
import com.maky.app.view.menu.Menu;
import com.maky.app.view.menu.MenuItem;
import com.maky.environment.AppGraphics;
import com.maky.environment.GraphicsConfig;
import com.maky.environment.IPaintable;
import com.maky.game.lines.LinesResources;
import com.maky.game.lines.controller.LinesCommand;
import com.maky.game.lines.view.LinesView;

public class LinesGameMenuV1 extends Menu implements IScreen {

	private static final MenuItem ITEM_NEW = new MenuItem("NEW GAME", "Begin new game", LinesCommand.COMMAND_GAME_NEW);
	private static final MenuItem ITEM_HIGHSCORE = new MenuItem("HIGHSCORE", "Highscore table",
			LinesCommand.COMMAND_GAME_HIGHSCORE);
	private static final MenuItem ITEM_EXIT = new MenuItem("EXIT", "Leave the game", LinesCommand.COMMAND_GAME_EXIT);

	private RImage background = ResourceManager.getInstance().getImageResource(LinesResources.IMAGE_MENU_BACKGROUND);

	private UICommander commander;

	public LinesGameMenuV1(UICommander commander, LinesView view) {
		super(MenuItem.getWidth(), MenuItem.getHeight() * 3, 0, view);
		this.commander = commander;
	}

	protected void buildMenu() {
		addItem(ITEM_NEW);
		addItem(ITEM_HIGHSCORE);
		addItem(ITEM_EXIT);
	}

	protected MenuItem getDefaultActiveItem() {
		return ITEM_NEW;
	}

	/**
	 * This method is invoked when user selects menu item
	 */
	protected int itemSelected(MenuItem item) {
		return commander.post(item.getCommandId());
	}

	public int getHeight() {
		return GraphicsConfig.getCanvasHeight();
	}

	public int getWidth() {
		return GraphicsConfig.getCanvasWidth();
	}

	/**
	 * {@link IScreen#dismiss()}
	 */
	public void dismiss() {

	}

	/**
	 * {@link IScreen#show()}
	 */
	public void show() {

	}

	/**
	 * {@link IPaintable#paint(AppGraphics)}
	 */
	public void paint(AppGraphics gr) {
		gr.drawImage(background.getImage(), 0, 0, null);
		super.paint(gr);
	}
}
