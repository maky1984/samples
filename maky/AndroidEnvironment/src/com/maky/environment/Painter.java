package com.maky.environment;

import java.util.ArrayList;
import java.util.List;

import com.maky.util.log.Logger;

public class Painter {

	private List<IPaintable> graphicsComponents;
	private IPaintable[] paintComponents;
	private volatile boolean componentsChanged;
	private Logger log;
	private boolean showFPS;
	private long time;
	private int fps;
	private int fpsCounter;

	public Painter() {
		graphicsComponents = new ArrayList<IPaintable>();
		componentsChanged = true;
		log = Logger.getInstance(this);
		showFPS = Logger.DO_DEBUG;
	}

	public synchronized void addComponent(IPaintable component) {
		graphicsComponents.add(component);
		componentsChanged = true;
	}

	public synchronized boolean removeComponent(IPaintable component) {
		if (graphicsComponents.remove(component)) {
			componentsChanged = true;
			return true;
		}
		return false;
	}

	public void paint(AppGraphics gr) {
		if (showFPS) {
			if (System.currentTimeMillis() - time > 1000) {
				time = System.currentTimeMillis();
				fps = fpsCounter + 1;
				fpsCounter = 0;
			} else {
				fpsCounter++;
			}
		}
		log.logNotice(" Paint start");
		if (componentsChanged) {
			synchronized (this) {
				componentsChanged = false;
				paintComponents = graphicsComponents.toArray(new IPaintable[graphicsComponents.size()]);
			}
		}
		for (int i = 0; i < paintComponents.length; i++) {
			paintComponents[i].paint(gr);
		}
		log.logNotice(" Paint stop");
		if (showFPS) {
			gr.setFont(GraphicsConfig.getDefaultFont());
			gr.drawString(Integer.toString(fps), GraphicsConfig.getCanvasCenterX(), GraphicsConfig.getScaledY(20));
		}
	}
}
