package com.maky.app.view;

import com.maky.app.controller.commander.ICommandListener;
import com.maky.environment.IPaintable;

public interface IScreen extends IPaintable, ICommandListener{

	/**
	 * This method is called when screen showed up
	 */
	public void show();
	
	/**
	 * This method is called screen removes from view
	 */
	public void dismiss();
}
