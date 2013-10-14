package com.maky.tv;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;


public interface IAppComp {

	public int ACTION_UP_RELEASED = 0;
	public int ACTION_DOWN_RELEASED = 1;
	public int ACTION_RIGHT_RELEASED = 2;
	public int ACTION_LEFT_RELEASED = 3;
	public int ACTION_PUT_BOMB_RELEASED = 4;
	public int ACTION_UP_PRESSED = 5;
	public int ACTION_DOWN_PRESSED = 6;
	public int ACTION_RIGHT_PRESSED = 7;
	public int ACTION_LEFT_PRESSED = 8;
	public int ACTION_PUT_BOMB_PRESSED = 9;

	public void start();

	public void init(GL2 gl2, GLU glu);
	
	public void update();

	public void render(GL2 gl2);

	public void action(int actionUpReleased);

}
