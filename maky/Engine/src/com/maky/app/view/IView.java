package com.maky.app.view;

import com.maky.app.view.graphics.elements.Message;
import com.maky.environment.AppCanvas;

public interface IView {

	public AppCanvas getCanvas();

	public void show();
	
	public void showMessage(Message msg);
	
	public void removeMessage();
}
