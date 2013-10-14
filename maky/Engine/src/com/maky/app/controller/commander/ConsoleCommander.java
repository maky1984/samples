package com.maky.app.controller.commander;

public class ConsoleCommander extends Commander {

	public ConsoleCommander() {
	}

	public void post(String string) {
		if (string.equals("test")) {
			super.command(Command.COMMAND_TEST);
		} else if (string.equals("left")) {
			super.command(Command.COMMAND_LEFT);
		} else if (string.equals("right")) {
			super.command(Command.COMMAND_RIGHT);
		} else if (string.equals("up")) {
			super.command(Command.COMMAND_UP);
		} else if (string.equals("down")) {
			super.command(Command.COMMAND_DOWN);
		} else if (string.equals("select")) {
			super.command(Command.COMMAND_SELECT);
		} else if (string.equals("exit")) {
			super.command(Command.COMMAND_ESC);
		} else {
			super.command(Command.UNDEFINED);
		}
	}

}
