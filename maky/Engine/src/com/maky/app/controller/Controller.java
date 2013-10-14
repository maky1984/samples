package com.maky.app.controller;

import java.util.ArrayList;
import java.util.Iterator;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.Commander;
import com.maky.app.controller.commander.ICommandListener;
import com.maky.app.controller.commander.MouseKeyboardCommander;
import com.maky.app.model.IModel;
import com.maky.app.view.IView;

public class Controller implements ICommandListener {

	private MouseKeyboardCommander keyboradMouseCommander;
	// commanders represents commander from console, network etc.
	private ArrayList commanders;
	private ArrayList listeners;

	protected IModel model;
	protected IView view;

	public Controller(IModel model) {
		this.model = model;
		commanders = new ArrayList();
		listeners = new ArrayList();
	}

	public void initCommander(IView view) {
		this.view = view;
		this.keyboradMouseCommander = new MouseKeyboardCommander(view.getCanvas());
		addListener(this);
	}

	public int handle(Command command) {
		switch (command.getId()) {
		case Command.COMMAND_EXIT:
		case Command.COMMAND_ESC:
			model.exit();
			return Command.RESULT_COMMAND_PROCESSED;
		}
		return Command.RESULT_COMMAND_SKIPPED;
	}

	public void addCommander(Commander commander) {
		commanders.add(commander);
		Iterator it = listeners.iterator();
		while (it.hasNext()) {
			ICommandListener listener = (ICommandListener) it.next();
			commander.addComponent(listener);
		}
	}

	public void removeCommander(Commander commander) {
		commanders.remove(commander);
		Iterator it = listeners.iterator();
		while (it.hasNext()) {
			ICommandListener listener = (ICommandListener) it.next();
			commander.removeComponent(listener);
		}
	}

	public void addListener(ICommandListener listener) {
		keyboradMouseCommander.addComponent(listener);
		Iterator it = commanders.iterator();
		while (it.hasNext()) {
			Commander commander = (Commander) it.next();
			commander.addComponent(listener);
		}
		listeners.add(listener);
	}

	public void removeListener(ICommandListener listener) {
		keyboradMouseCommander.removeComponent(listener);
		Iterator it = commanders.iterator();
		while (it.hasNext()) {
			Commander commander = (Commander) it.next();
			commander.removeComponent(listener);
		}
		listeners.remove(listener);
	}
}
