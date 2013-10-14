package com.maky;

import javax.media.opengl.GL2;

public interface IAppComp extends IAppActionListener {

	public void start();
	
	public void update();

	public void render(GL2 gl2);

}
