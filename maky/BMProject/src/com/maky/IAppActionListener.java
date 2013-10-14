package com.maky;

public interface IAppActionListener {

	public int ACTION_UP_PRESSED = 0;
	public int ACTION_UP_RELEASED = 1;
	public int ACTION_DOWN_PRESSED = 2;
	public int ACTION_DOWN_RELEASED = 3;
	public int ACTION_RIGHT_PRESSED = 4;
	public int ACTION_RIGHT_RELEASED = 5;
	public int ACTION_LEFT_PRESSED = 6;
	public int ACTION_LEFT_RELEASED = 7;
	public int ACTION_PUT_BOMB_PRESSED = 8;
	public int ACTION_PUT_BOMB_RELEASED = 9;
	
	public void action(int action);
	
}
