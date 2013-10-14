package test.maky.project.lines.commander;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.Commander;
import com.maky.app.controller.commander.MouseKeyboardCommander;

import junit.framework.TestCase;

public class UICommanderTest extends TestCase {

	private static long DELAY = 150;

	private static void sleep() {
		try {
			Thread.sleep(DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void testUICommands() {
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		Frame frame = new Frame("Test mouse frame", gc);
		frame.setBounds(0, 0, 100, 100);
		Canvas canvas = new Canvas();
		canvas.setBounds(0, 0, 100, 100);
		frame.add(canvas);
		frame.setVisible(true);
		CommandOutputListener listener = new CommandOutputListener();
		Commander commander = new MouseKeyboardCommander(canvas);
		commander.addComponent(listener);
		sleep();
		try {
			Robot robot = new Robot();
			robot.mouseMove(90, 90);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			sleep();
			assertEquals(Command.COMMAND_SELECT, listener.getLastCommand());
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyRelease(KeyEvent.VK_LEFT);
			sleep();
			assertEquals(Command.COMMAND_LEFT, listener.getLastCommand());
			robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_RIGHT);
			sleep();
			assertEquals(Command.COMMAND_RIGHT, listener.getLastCommand());
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
			sleep();
			assertEquals(Command.COMMAND_UP, listener.getLastCommand());
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			sleep();
			assertEquals(Command.COMMAND_DOWN, listener.getLastCommand());
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			sleep();
			assertEquals(Command.COMMAND_SELECT, listener.getLastCommand());
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}

}
