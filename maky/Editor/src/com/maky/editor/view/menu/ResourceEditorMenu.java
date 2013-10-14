package com.maky.editor.view.menu;

import com.maky.app.controller.commander.Command;
import com.maky.app.view.IScreen;
import com.maky.app.view.menu.Menu;
import com.maky.app.view.menu.MenuItem;
import com.maky.editor.view.ResourceEditorView;
import com.maky.environment.GraphicsConfig;

public class ResourceEditorMenu extends Menu implements IScreen {

	public static final int SIZE = 3;
	private static final MenuItem ITEM_CREATE_BYTE_ARRAY = new MenuItem("BYTE ARRAY", "Create byte array",
			Command.COMMAND_RESOURCE_SHOW_CREATE_BYTE_ARRAY_SCREEN);
	private static final MenuItem ITEM_CREATE_IMAGE = new MenuItem("IMAGE", "Create image reource",
			Command.COMMAND_RESOURCE_SHOW_CREATE_IMAGE_SCREEN);
	private static final MenuItem ITEM_EXIT = new MenuItem("EXIT", "Exit", Command.COMMAND_EXIT);

	public ResourceEditorMenu(ResourceEditorView view) {
		super(MenuItem.getWidth(), MenuItem.getHeight() * 3, 0, view);
	}

	protected void buildMenu() {
		addItem(ITEM_CREATE_BYTE_ARRAY);
		addItem(ITEM_CREATE_IMAGE);
		addItem(ITEM_EXIT);
	}

	protected MenuItem getDefaultActiveItem() {
		return ITEM_CREATE_BYTE_ARRAY;
	}

	protected int itemSelected(MenuItem item) {
		return getView().getUICommander().post(item.getCommandId());
	}
	
	public void show() {
	}
	
	public void dismiss() {
	}

	public int getHeight() {
		return GraphicsConfig.getCanvasHeight();
	}

	public int getWidth() {
		return GraphicsConfig.getCanvasWidth();
	}
}
