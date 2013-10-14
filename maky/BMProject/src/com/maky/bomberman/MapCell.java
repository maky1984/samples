package com.maky.bomberman;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

import com.maky.App;

public class MapCell {

	public static final int TYPE_GROUND = 0;
	public static final int TYPE_ROCK = 1;

	private int type;
	private int x, y, w, h;
	private float colorChangingValue;
	private Bomb bomb;
	private List<AnimObject> situatedObjects;

	public MapCell(int x, int y, int w, int h) {
		this(x, y, w, h, TYPE_GROUND);
	}

	public MapCell(int x, int y, int w, int h, int type) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.type = type;
		situatedObjects = new ArrayList<AnimObject>();
	}

	public void render(GL2 gl2) {
		gl2.glBegin(GL2.GL_QUADS);
		// draw a rectangle
		gl2.glColor3f(type, colorChangingValue, 0);
		gl2.glVertex2f(App.toGLx(x), App.toGLy(y));
		gl2.glColor3f(0, type, 0);
		gl2.glVertex2f(App.toGLx(x + w), App.toGLy(y));
		gl2.glColor3f(0, 0, type);
		gl2.glVertex2f(App.toGLx(x + w), App.toGLy(y + h));
		gl2.glColor3f(1, 1, 1);
		gl2.glVertex2f(App.toGLx(x), App.toGLy(y + h));
		gl2.glEnd();
	}

	public void update() {
		switch (type) {
		case TYPE_ROCK:
			colorChangingValue += 0.001f;
			break;
		default:
			break;
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int currentType) {
		type = currentType;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void addControlledObject(AnimObject object) {
		situatedObjects.add(object);
	}
	
	public void removeControlledObject(AnimObject object) {
		if (!situatedObjects.remove(object)) {
			System.out.println("Something wrong, trying to remove ... " + object);
		}
	}
	
	public List<AnimObject> getObjectsOnCell() {
		return situatedObjects;
	}
	
	public boolean putBomb(Bomb bomb) {
		if (this.bomb == null) {
			this.bomb = bomb;
			return true;
		}
		return false;
	}
	
	public void blowedUpBy(Bomb bomb) {
		if (situatedObjects.size() > 0) {
			AnimObject[] array = situatedObjects.toArray(new AnimObject[situatedObjects.size()]);
			for (AnimObject object : array) {
				object.blowedUpBy(bomb);
			}
		}
		this.bomb = null;
	}
}
