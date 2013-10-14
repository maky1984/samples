package com.maky.app.controller.commander;

public class UICommander extends Commander {

	public int post(int commandId) {
		return command(commandId);
	}

	public int post(int commandId, int param1) {
		return command(commandId, param1);
	}

	public int post(int commandId, int param1, int param2, int param3, int param4) {
		return command(commandId, param1, param2, param3, param4);
	}
	
	public int post(int commandId, Object objParam) {
		return command(commandId, objParam);
	}
}
