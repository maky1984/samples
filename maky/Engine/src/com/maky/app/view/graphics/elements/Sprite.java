package com.maky.app.view.graphics.elements;

import com.maky.app.resource.RImage;
import com.maky.app.view.View;
import com.maky.environment.AppGraphics;
import com.maky.environment.AppImage;
import com.maky.environment.GraphicsConfig;
import com.maky.util.timing.IUpdateble;

public class Sprite extends GraphicElement implements IUpdateble {

	private final AppImage[] images;
	private final int width;
	private final int height;
	private int state;
	private boolean isActive;
	private final boolean infinite;
	private final long duration;
	private int tickPeriod;
	private int tickCounter;
	
	public Sprite(Sprite sprite) {
		super(sprite.getView());
		images = sprite.images;
		width = sprite.width;
		height = sprite.height;
		infinite = sprite.infinite;
		duration = sprite.duration;
	}
	
	public Sprite(AppImage[] images, long duration, boolean infinite, View view) {
		super(view);
		this.images = images;
		if (images.length > 0) {
			AppImage image = images[0];
			width = image.getWidth();
			height = image.getHeight();
		} else {
			width = 0;
			height = 0;
		}
		this.infinite = infinite;
		this.duration = duration;
	}
	
	public Sprite(RImage allImages, int number, long duration, boolean infinite, View view) {
		super(view);
		images = new AppImage[number];
		int dx = allImages.getImage().getWidth() / number;
		for (int i=0;i<images.length;i++) {
			AppImage image = new AppImage(dx, allImages.getImage().getHeight(), AppImage.TYPE_INT_ARGB);
			image.getGraphics().drawImage(allImages.getImage(), -i * dx, 0, null);
			images[i] = image;
		}
		width = dx;
		height = allImages.getImage().getHeight();
		this.infinite = infinite;
		this.duration = duration;
	}
	
	public void paint(AppGraphics gr) {
		if (state > 0) {
			gr.setAlphaComposite(0.7f);
		}
		gr.drawImage(images[state], getX(), getY(), null);
		if (state > 0) {
			gr.setAlphaComposite(1.0f);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void start() {
		if (!isActive) {
			isActive = true;
			tickPeriod = (int)(duration * GraphicsConfig.TICKLER_PERIOD) / (1000 * images.length);
			getView().getUpdater().addComponent(this);
		}
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void stop() {
		state = 0;
		isActive = false;
		getView().getUpdater().removeComponent(this);
	}
	
	public void tick() {
		if (isActive) {
			if (tickCounter == tickPeriod) {
				tickCounter = 0;
				if (state + 1 == images.length && !infinite) {
					stop();
				} else {
					state = (state + 1) % (images.length);
				}
			} else {
				tickCounter++;
			}
		}
	}

}
