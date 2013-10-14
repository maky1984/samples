package com.maky.app.controller.commander;

/**
 * This interface describes the handler of the commands from the UI (or any other commander)
 * 
 * @author mkotlyar
 */

public interface ICommandListener {

	/**
	 * This method is called when Commander wants to CommandListener with command
	 * @param command 
	 * @return
	 */
	public int handle(Command command);
}
