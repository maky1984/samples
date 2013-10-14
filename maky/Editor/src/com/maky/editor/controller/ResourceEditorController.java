package com.maky.editor.controller;

import com.maky.app.controller.Controller;
import com.maky.app.controller.commander.Command;
import com.maky.editor.model.ResourceEditorModel;
import com.maky.editor.view.ResourceEditorView;

public class ResourceEditorController extends Controller {

	public ResourceEditorController(ResourceEditorModel model) {
		super(model);
	}

	private ResourceEditorModel getModel() {
		return (ResourceEditorModel) model;
	}

	private ResourceEditorView getView() {
		return (ResourceEditorView) view;
	}

	public int handle(Command command) {
		int result = Command.RESULT_COMMAND_PROCESSED;
		switch (command.getId()) {
		case Command.COMMAND_RESOURCE_SHOW_CREATE_BYTE_ARRAY_SCREEN:
			getView().loadCreateByteArrayScreen();
			break;
		case Command.COMMAND_RESOURCE_SHOW_CREATE_IMAGE_SCREEN:
			// TODO: implement it
			break;
		case Command.COMMAND_RESOURCE_ADD_LEVEL:
			getModel().createLevelResource((short[][])command.getObjectParam(0));
		default:
			result = super.handle(command);
			break;
		}
		return result;
	}

}
