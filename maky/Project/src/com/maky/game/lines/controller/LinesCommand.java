package com.maky.game.lines.controller;

import com.maky.app.controller.commander.Command;

public class LinesCommand extends Command {
	
	// Lines game specific commands
	public static final int COMMAND_GAME_NEW = 1000;
	public static final int COMMAND_GAME_EXIT = 1001;
	public static final int COMMAND_GAME_HIGHSCORE = 1002;
	public static final int COMMAND_GAME_MOVE_BALL = 1003;
	public static final int COMMAND_GAME_LEVEL_CHOOSEN = 1004;
	public static final int COMMAND_GAME_NEXT_LEVEL = 1005;

	public LinesCommand(int id) {
		super(id);
	}

}
