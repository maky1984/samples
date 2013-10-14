package com.maky.app.controller.commander;

public class Command {

	public static int UNDEFINED = -1;

	public static final int COMMAND_TEST = 0;
	public static final int COMMAND_UP = 1;
	public static final int COMMAND_DOWN = 2;
	public static final int COMMAND_LEFT = 3;
	public static final int COMMAND_RIGHT = 4;
	public static final int COMMAND_SELECT = 5;
	public static final int COMMAND_ESC = 6;
	public static final int COMMAND_EXIT = 7;
	public static final int COMMAND_NUMBER = 8;
	public static final int COMMAND_BACK = 9;
	public static final int COMMAND_MOUSE_MOVED = 10;

	// General UI element specific commands
	public static final int COMMAND_INPUT_NUMBER_ENTERED = 100;
	public static final int COMMAND_MESSAGE_REMOVED = 101;
	public static final int COMMAND_ACTIVE_ELEMENT_SELECTED = 102;

	// Resource editor specific commands
	public static final int COMMAND_RESOURCE_SHOW_CREATE_BYTE_ARRAY_SCREEN = 2000;
	public static final int COMMAND_RESOURCE_SHOW_CREATE_IMAGE_SCREEN = 2001;
	public static final int COMMAND_RESOURCE_EDITOR_LEVEL_VALUE_SELECTED = 2002;
	public static final int COMMAND_RESOURCE_EDITOR_SAVE_LEVEL = 2003;
	public static final int COMMAND_RESOURCE_EDITOR_CLEAR_LEVEL = 2004;
	public static final int COMMAND_RESOURCE_ADD_LEVEL = 2005;

	public static final int RESULT_COMMAND_PROCESSED = 0;
	public static final int RESULT_COMMAND_SKIPPED = 1;

	private int id;
	private Object params;

	public Command(int id) {
		this.id = id;
	}

	public Command(int id, Object[] params) {
		this(id);
		this.params = params;
	}

	public Command(int id, int[] params) {
		this(id);
		this.params = params;
	}

	public int getId() {
		return id;
	}

	public int getParam(int index) {
		if (params == null || index >= ((int[])params).length) {
			return UNDEFINED;
		}
		return ((int[])params)[index];
	}
	
	public Object getObjectParam(int index) {
		return ((Object[])params)[index];
	}
}
