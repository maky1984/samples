package com.maky.editor;

import com.maky.editor.controller.ResourceEditorController;
import com.maky.editor.model.ResourceEditorModel;
import com.maky.editor.view.ResourceEditorView;
import com.maky.environment.GraphicsConfig;

public class ResourceEditor {

	private ResourceEditorView view;
	
	private ResourceEditorController controller;
	
	private ResourceEditorModel model;

	public ResourceEditor() {
		GraphicsConfig.configure(GraphicsConfig.getCanvasWidth(), GraphicsConfig.getCanvasHeight());
		model = new ResourceEditorModel();
		controller = new ResourceEditorController(model);
		view = new ResourceEditorView(controller, model);
	}
	
	private void start() {
		controller.initCommander(view);
		view.show();
	}

	public static void main(String[] args) {
		new ResourceEditor().start();
	}

}
