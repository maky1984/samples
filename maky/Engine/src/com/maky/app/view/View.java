package com.maky.app.view;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import com.maky.app.controller.Controller;
import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.UICommander;
import com.maky.app.model.Model;
import com.maky.app.view.graphics.elements.Message;
import com.maky.environment.AppCanvas;
import com.maky.environment.AppImage;
import com.maky.environment.GraphicsConfig;
import com.maky.environment.Painter;
import com.maky.util.timing.Tickler;

/**
 * Represents the View in MVC pattern
 * 
 * @author michael
 * 
 */
public abstract class View implements IView {

	private final AppCanvas canvas;
	private final Tickler tickler;
	private final Controller controller;
	protected final UICommander commander;
	private IScreen currentScreen;
	private Message message;

	public View(final Controller controller, Model model) {
		this.controller = controller;
		commander = new UICommander();
		controller.addCommander(commander);
		Painter painter = new Painter();
		canvas = new AppCanvas(painter);

		Frame frame = new Frame();
		frame.setBounds(0, 0, GraphicsConfig.getCanvasWidth(), GraphicsConfig.getCanvasHeight());
		frame.add(canvas);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setVisible(true);
		canvas.requestFocus();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				commander.post(Command.COMMAND_ESC);
			}
		});
		model.addView(this);

		// Load tickler
		tickler = new Tickler();
		tickler.addComponent(canvas);
		tickler.start(GraphicsConfig.TICKLER_PERIOD);
	}

	public AppCanvas getCanvas() {
		return canvas;
	}

	public Painter getPainter() {
		return canvas.getPainter();
	}

	public Controller getController() {
		return controller;
	}

	public UICommander getUICommander() {
		return commander;
	}

	public Tickler getUpdater() {
		return tickler;
	}

	public void setCurrentScreen(IScreen screen) {
		if (currentScreen != null) {
			currentScreen.dismiss();
			getPainter().removeComponent(currentScreen);
			getController().removeListener(currentScreen);
		}
		currentScreen = screen;
		getPainter().addComponent(currentScreen);
		getController().addListener(currentScreen);
		currentScreen.show();
	}

	public AppImage createBufferImage(int width, int height) {
		return new AppImage(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
	}

	public abstract void show();

	public void beep() {
		// TODO: Implement beep signal
	}

	private void showMessageComponent(Message msg) {
		getPainter().addComponent(msg);
		getController().addListener(msg);
	}

	public void showMessage(Message msg) {
		if (message != null) {
			removeMessage();
		}
		message = msg;
		showMessageComponent(message);
	}

	private void removeMessageComponent(Message msg) {
		getPainter().removeComponent(msg);
		getController().removeListener(msg);
		msg.dismiss();
	}

	public void removeMessage() {
		removeMessageComponent(message);
		message = null;
	}
}
