package com.maky.starship;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.maky.App;

public class Ship {
	
	private int x, y, w, h;
	
	public Ship() {
		w = 100;
		h = 100;
	}
	
	public void render(GL2 gl2) {
		gl2.glBegin(GL.GL_TRIANGLES);
		
		gl2.glColor3f( 1f, 1f, 1f );
		gl2.glVertex3f(App.toGLx(x), App.toGLy(y), App.toGLz(0));
		gl2.glVertex3f(App.toGLx(x + w), App.toGLy(y), App.toGLz(0));
		gl2.glVertex3f(App.toGLx(x + w), App.toGLy(y + h), App.toGLz(4000));
		
		gl2.glEnd();
	}

}
