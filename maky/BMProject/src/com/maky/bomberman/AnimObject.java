package com.maky.bomberman;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.maky.App;

public class AnimObject {

	public static final int SPEED_1 = 2000; // coordinates per second
	
	private String name;
	
	private int x,y,w,h,plannedX, plannedY;
	private int moveDelta;
	private long lastMoveTime;
	private int speed; // virtual coordinates per second
	private int moveDX, moveDY;
	private int mapCellH, mapCellW; // size of cell on map
	private Map map;
	
	public AnimObject(String name, int x, int y, int width, int height) {
		this.name = name;
		this.x = x;
		this.y = y;
		w = width;
		h = height;
	}

	public void linkMap(Map map) {
		mapCellH = map.getCellH();
		mapCellW = map.getCellW();
		this.map = map;
		getMapCell().addControlledObject(this);
	}
	
	public void removeFromMap() {
		getMapCell().removeControlledObject(this);
	}
	
	protected Map getMap() {
		return map;
	}

	public void setMoveSpeed(int speed) {
		this.speed = speed;
		System.out.println("AnimObject[" + name + "] setSpeed speed = " + speed + " moveDelta=" + moveDelta);
	}
	
	public void updateMove(int dx, int dy) {
		moveDX += dx;
		moveDY += dy;
		lastMoveTime = System.currentTimeMillis();
	}
	
	public void update() {
		MapCell oldPosition = getMapCell();
		calculateMoveDelta();
		int planForX = moveDelta*moveDX + x;
		int planForY = moveDelta*moveDY + y;
		plannedX = planForX;
		plannedY = planForY;
		if (checkCollision()) {
			plannedX = x;
			plannedY = planForY;
			if (checkCollision()) {
				plannedX = planForX;
				plannedY = y;
				if (checkCollision()) {
					plannedX = x;
					plannedY = y;
				} else {
					x = plannedX;
					y = plannedY;
				}
			} else {
				plannedX = x;
				y = plannedY;
			}
		} else {
			x = plannedX;
			y = plannedY;
		}
		MapCell newPosition = getMapCell();
		if (!oldPosition.equals(newPosition)) {
			oldPosition.removeControlledObject(this);
			newPosition.addControlledObject(this);
		}
	}
	
	private void calculateMoveDelta() {
		long currTime = System.currentTimeMillis();
		long timeSpentFromLastMove = currTime - lastMoveTime;
		if ((moveDelta = (int)(speed * timeSpentFromLastMove / 1000)) > 0) {
			lastMoveTime = currTime;
		}
	}
	
	private boolean checkCollision() {
		int x = plannedX;
		int y = plannedY;
		boolean leftDown = pointHasCollision(x, y);
		boolean rightDown = pointHasCollision(x + w - 1, y); 
		boolean rightUp = pointHasCollision(x + w - 1, y + h - 1); 
		boolean leftUp = pointHasCollision(x, y + h - 1);
		boolean result;
		if (leftDown || rightDown || rightUp || leftUp) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	private int getCellX(int x) {
		int i = x / mapCellW;
		if ((x % mapCellW) == 0 && i > 0) {
			i--;
		}
		return i;
	}
	
	private int getCellY(int y) {
		int j = y / mapCellH;
		if ((y % mapCellH) == 0 && j > 0) {
			j--;
		}
		return j;
	}
	
	private boolean pointHasCollision(int x, int y) {
		if (x < 0 || x > App.VIRTUAL_WIDTH || y < 0 || y > App.VIRTUAL_HEIGHT) {
			return true;
		}
		return map.hasCollision(getCellX(x),getCellY(y));
	}
	
	public void blowedUpBy(Bomb bomb) {
		removeFromMap();
		System.out.println("Player " + name + " blowed up by " + bomb);
	}

	protected MapCell getMapCell() {
		return map.get(getCellX(x + w/2), getCellY(y + h/2));
	}
	
	public void render(GL2 gl2) {
		gl2.glBegin(GL.GL_TRIANGLES);
		gl2.glColor3f( 1, 1, 1 );
		gl2.glVertex2f(App.toGLx(x), App.toGLy(y));
		gl2.glVertex2f(App.toGLx(x + w), App.toGLy(y));
		gl2.glVertex2f(App.toGLx(x + w), App.toGLy(y + h));
		gl2.glEnd();
	}
	
	public String getName() {
		return name;
	}
}
