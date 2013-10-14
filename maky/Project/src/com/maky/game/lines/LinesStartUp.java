package com.maky.game.lines;

import com.maky.app.resource.ResourceManager;
import com.maky.environment.GraphicsConfig;
import com.maky.game.lines.controller.LinesController;
import com.maky.game.lines.model.LinesModel;
import com.maky.game.lines.view.LinesView;

/**
 * Boot application
 * 
 * @author michael
 * 
 */
public class LinesStartUp {

	private final LinesView view;
	private final LinesController controller;
	private final LinesModel model;

	public LinesStartUp() {
		LinesResources.configure();
		GraphicsConfig.configure(LinesConfig.WIDTH, LinesConfig.HEIGHT);
		model = new LinesModel();
		controller = new LinesController(model);
		view = new LinesView(controller, model);

		// Load resource Manager
		ResourceManager.getInstance();
	}

	private void start() {
		controller.initCommander(view);
		view.show();
	}

	public static void main(String[] args) {
		new LinesStartUp().start();
	}

}
