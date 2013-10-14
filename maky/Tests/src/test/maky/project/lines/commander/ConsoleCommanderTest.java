package test.maky.project.lines.commander;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.ConsoleCommander;

import junit.framework.TestCase;

public class ConsoleCommanderTest extends TestCase {

	public final void testConstructing() {
		CommandOutputListener listener = new CommandOutputListener();
		ConsoleCommander commander = new ConsoleCommander();
		commander.addComponent(listener);
		commander.post("test");
		assertEquals(Command.COMMAND_TEST, listener.getLastCommand());
	}

	public final void testRemoving() {
		CommandOutputListener listener = new CommandOutputListener();
		ConsoleCommander commander = new ConsoleCommander();
		commander.addComponent(listener);
		commander.post("test");
		assertEquals(Command.COMMAND_TEST, listener.getLastCommand());
		commander.post("unknown");
		assertEquals(Command.UNDEFINED, listener.getLastCommand());
		commander.removeComponent(listener);
		commander.post("test");
		assertEquals(Command.UNDEFINED, listener.getLastCommand());
	}

	public final void testCommands() {
		CommandOutputListener listener = new CommandOutputListener();
		ConsoleCommander commander = new ConsoleCommander();
		commander.addComponent(listener);
		commander.post("left");
		assertEquals(Command.COMMAND_LEFT, listener.getLastCommand());
		commander.post("right");
		assertEquals(Command.COMMAND_RIGHT, listener.getLastCommand());
		commander.post("up");
		assertEquals(Command.COMMAND_UP, listener.getLastCommand());
		commander.post("down");
		assertEquals(Command.COMMAND_DOWN, listener.getLastCommand());
		commander.post("select");
		assertEquals(Command.COMMAND_SELECT, listener.getLastCommand());
		commander.post("test");
		assertEquals(Command.COMMAND_TEST, listener.getLastCommand());
		commander.post("unknown");
		assertEquals(Command.UNDEFINED, listener.getLastCommand());
	}
}
