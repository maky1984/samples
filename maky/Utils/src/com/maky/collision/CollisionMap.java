package com.maky.collision;

public class CollisionMap {

	private int height;
	private int width;

	private Object map[][];

	public CollisionMap(int width, int height) {
		this.height = height;
		this.width = width;
		map = new Object[width][height];// new BitSet(width * height);
	}

	private Object checkState(int x, int y) {
		return map[x][y];// map.get(y * width + x);
	}

	public void addPoint(int x, int y, Object object) {
		map[x][y] = object;// map.set(y * width + x);
	}

	public void removePoint(int x, int y) {
		map[x][y] = null;// map.clear(y * width + x);
	}

	public void addRectangle(int x, int y, int width, int height, Object object) {
		for (int dx = x; dx < x + width; dx++) {
			for (int dy = y; dy < y + height; dy++) {
				addPoint(dx, dy, object);
			}
		}
	}

	public void removeRectangle(int x, int y, int width, int height) {
		for (int dx = x; dx < x + width; dx++) {
			for (int dy = y; dy < y + height; dy++) {
				removePoint(dx, dy);
			}
		}
	}

	public Object checkCollision(int x, int y) {
		// TODO: check bounds
		return checkState(x, y);
	}
}
