package com.maky.app.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.maky.app.view.View;
import com.maky.app.view.graphics.elements.Message;

public class Model implements IModel {

	protected List views;

	public Model() {
		views = new ArrayList();
	}

	public void addView(View view) {
		views.add(view);
	}

	public void removeView(View view) {
		views.remove(view);
	}

	public void notifyViewShowMessage(String str) {
		Iterator it = views.iterator();
		while (it.hasNext()) {
			View view = (View) it.next();
			view.showMessage(new Message(str, view));
		}
	}

	public void exit() {
		System.exit(0);
	}
}
