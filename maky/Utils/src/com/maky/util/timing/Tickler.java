package com.maky.util.timing;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Tickler {

	private List<IUpdateble> updatebleComponents;
	private volatile IUpdateble[] updatebleComponentsArray;

	private Timer timer;
	private TicklerTask task;

	private class TicklerTask extends TimerTask {
		public void run() {
			// TODO: rebuild this scheme
			// Make one thread or use thread pool for avoiding creating new thread for each tick
			tick();
		}
	}

	public Tickler() {
		timer = new Timer();
		task = new TicklerTask();
		updatebleComponents = new ArrayList<IUpdateble>();
	}

	public void addComponent(IUpdateble listener) {
		updatebleComponents.add(listener);
		updatebleComponentsArray = updatebleComponents.toArray(new IUpdateble[updatebleComponents.size()]);
	}

	public boolean removeComponent(IUpdateble listener) {
		boolean result = updatebleComponents.remove(listener);
		updatebleComponentsArray = updatebleComponents.toArray(new IUpdateble[updatebleComponents.size()]);
		return result;
	}

	public void start(int delay, int period) {
		timer.schedule(task, delay, period);
	}

	public void start(int period) {
		timer.schedule(task, 0, period);
	}

	public void stop() {
		timer.cancel();
	}

	public void tick() {
		for (int i = 0; i < updatebleComponentsArray.length; i++) {
			updatebleComponentsArray[i].tick();
		}
	}
}
