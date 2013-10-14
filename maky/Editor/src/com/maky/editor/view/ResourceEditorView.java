package com.maky.editor.view;

import com.maky.app.view.View;
import com.maky.editor.controller.ResourceEditorController;
import com.maky.editor.model.ResourceEditorModel;
import com.maky.editor.view.levelEdit.LevelEditScreen;
import com.maky.editor.view.menu.ResourceEditorMenu;

public class ResourceEditorView extends View {

	private ResourceEditorMenu menu;
	private LevelEditScreen createLevel;

	public ResourceEditorView(ResourceEditorController controller, ResourceEditorModel model) {
		super(controller, model);
	}

	public void show() {
		menu = new ResourceEditorMenu(this);
		setCurrentScreen(menu);
	}

	public void loadCreateByteArrayScreen() {
		createLevel = new LevelEditScreen(this);
		setCurrentScreen(createLevel);
	}
}
