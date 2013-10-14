package test.maky.project.lines.commander;

import com.maky.app.controller.commander.Command;
import com.maky.app.controller.commander.ICommandListener;

public class CommandOutputListener implements ICommandListener {

	private int lastCommand;

	public CommandOutputListener() {
	}

	public int handle(Command command) {
		lastCommand = command.getId();
		return Command.RESULT_COMMAND_PROCESSED;
	}

	public int getLastCommand() {
		return lastCommand;
	}

}
