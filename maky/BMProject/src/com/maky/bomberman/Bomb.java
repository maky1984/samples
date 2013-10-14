package com.maky.bomberman;

import java.util.List;

public class Bomb extends AnimObject {

	private static final int BLOW_TIME = 2000;
	private static final int BLOW_RANGE_1 = 2; 
	
	private static final int STATE_INIT = 0;
	private static final int STATE_DROPPED = 1;
	private static final int STATE_BLOWING = 2;
	private static final int STATE_BLOWED_UP = 3;
	
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	
	private final ControlledObject creator;
	private long creationTime;
	private int blowRange; // cell number that are blowed up by one bomb
	private int state;
	
	public Bomb(String name, int x, int y, ControlledObject bombCreator) {
		super(name, x, y, WIDTH, HEIGHT);
		creator = bombCreator;
		state = STATE_INIT;
		blowRange = BLOW_RANGE_1;
	}

	public boolean isBlowedUp() {
		return state == STATE_BLOWED_UP;
	}
	
	private void blowUp(MapCell cell) {
		cell.blowedUpBy(this);
	}
	
	private void destroyObjects() {
		MapCell root = getMapCell();
		blowUp(root);
		Map map = getMap();
		MapCell left = root;
		MapCell right = root;
		MapCell up = root;
		MapCell down = root;
		for (int i = 1; i < BLOW_RANGE_1; i++) {
			if (left != null) {
				left = map.leftFrom(left);
				if (left != null) blowUp(left);
			}
			if (right != null) {
				right = map.rightFrom(right);
				if (right != null) blowUp(right);
			}
			if (up != null) {
				up = map.upFrom(up);
				if (up != null) blowUp(up);
			}
			if (down != null) {
				down = map.downFrom(down);
				if (down != null) blowUp(down);
			}
		}
	}
	
	public void process() {
		switch(state) {
		case STATE_INIT:
			creationTime = System.currentTimeMillis();
			state = STATE_DROPPED;
			break;
		case STATE_DROPPED:
			if (System.currentTimeMillis() - creationTime >= BLOW_TIME) {
				state = STATE_BLOWING;
			}
			break;
		case STATE_BLOWING:
			// TODO:
			destroyObjects();
			if (System.currentTimeMillis() - creationTime >= 2*BLOW_TIME) {
				state = STATE_BLOWED_UP;
			}
			break;
		case STATE_BLOWED_UP:
			break;
		}
	}
	
	public void update() {
		process();
		super.update();
	}
	
}
