package com.maky.starship;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.maky.IAppComp;

public class StarShipComponent implements IAppComp {

	private Ship ship;
	
	@Override
	public void start() {
		ship = new Ship();
	}

	@Override
	public void update() {
	}

	@Override
	public void action(int action) {
		
	}
	
	@Override
	public void render(GL2 gl2) {
		gl2.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl2.glLoadIdentity();
		ship.render(gl2);
		gl2.glFlush();
	}

}
