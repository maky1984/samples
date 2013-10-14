package com.maky.app.controller.commander;

import java.util.ArrayList;
import java.util.List;

import com.maky.util.log.Logger;

abstract public class Commander {

	private List commandListeners;
	private ICommandListener[] commandListenerComponents;
	private volatile boolean componentsChanged;

	public Commander() {
		commandListeners = new ArrayList();
		componentsChanged = true;
	}

	public synchronized void addComponent(ICommandListener component) {
		commandListeners.add(component);
		componentsChanged = true;
	}

	public synchronized boolean removeComponent(ICommandListener component) {
		if (commandListeners.remove(component)) {
			componentsChanged = true;
			return true;
		}
		return false;
	}

	public int handle(Command command) {
		if (componentsChanged) {
			synchronized (this) {
				componentsChanged = false;
				commandListenerComponents = (ICommandListener[]) commandListeners
						.toArray(new ICommandListener[commandListeners.size()]);
			}
		}
		int result = Command.RESULT_COMMAND_SKIPPED;
		for (int i = commandListenerComponents.length - 1; i >= 0; i--) {
			result = commandListenerComponents[i].handle(command);
			if (result == Command.RESULT_COMMAND_PROCESSED) {
				Logger.getInstance(this).logInfo(
						"command " + command.getId() + " processed by " + commandListenerComponents[i]);
				break;
			}
		}
		return result;
	}

	protected int command(int command, int param1, int param2, int param3, int param4) {
		Command object = new Command(command, new int[] { param1, param2, param3, param4 });
		return handle(object);
	}

	protected int command(int command, int param1, int param2, int param3) {
		Command object = new Command(command, new int[] { param1, param2, param3 });
		return handle(object);
	}

	protected int command(int command, int param1, int param2) {
		Command object = new Command(command, new int[] { param1, param2 });
		return handle(object);
	}

	protected int command(int command, int param1) {
		Command object = new Command(command, new int[] { param1 });
		return handle(object);
	}

	protected int command(int command, Object param1) {
		Command object = new Command(command, new Object[] { param1 });
		return handle(object);
	}

	protected int command(int command, Object param1, Object param2) {
		Command object = new Command(command, new Object[] { param1, param2 });
		return handle(object);
	}

	protected int command(int command) {
		Command object = new Command(command);
		return handle(object);
	}
}
