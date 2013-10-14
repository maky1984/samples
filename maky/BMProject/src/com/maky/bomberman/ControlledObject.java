package com.maky.bomberman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.media.opengl.GL2;

public class ControlledObject extends AnimObject {

	private static final int STEP = 1;
	private static final long BOMB_PERIOD_SPEED1 = 1000;
	private static final int BOMB_MAX_NUMBER = 2;
	
	private Stage stage;
	private long bombPeriod, lastBombDroppedTime;
	private int bombCounter;
	private boolean isBombing;
	private List<Bomb> bombs;
	
	public ControlledObject(String name, int x, int y, int w, int h, Stage stage) {
		super(name, x, y, w, h);
		this.stage = stage;
		bombPeriod = BOMB_PERIOD_SPEED1;
		bombs = new ArrayList<Bomb>();
	}
	
	public void startMoveUp() {
		updateMove(0, STEP);
	}
	
	public void stopMoveUp() {
		updateMove(0, -STEP);
	}
	
	public void startMoveDown() {
		updateMove(0, -STEP);
	}
	
	public void stopMoveDown() {
		updateMove(0, STEP);
	}
	
	public void startMoveLeft() {
		updateMove(-STEP, 0);
	}

	public void stopMoveLeft() {
		updateMove(STEP, 0);
	}
	
	public void startMoveRight() {
		updateMove(STEP, 0);
	}
	
	public void stopMoveRight() {
		updateMove(-STEP, 0);
	}

	public void startBombing() {
		isBombing = true;
	}
	
	public void stopBombing() {
		isBombing = false;
	}

	private void dropBomb() {
		if (isBombing) {
			long currTime = System.currentTimeMillis();
			if (currTime - lastBombDroppedTime > bombPeriod && bombCounter < BOMB_MAX_NUMBER) {
				MapCell mapCell = getMapCell();
				Bomb bomb = new Bomb("Bomb by " + getName(), mapCell.getX(), mapCell.getY(), this);
				if (mapCell.putBomb(bomb)) {
					stage.addBomb(bomb);
					bombs.add(bomb);
					bombCounter++;
					lastBombDroppedTime = currTime;
				}
			}
		}
	}
	
	public void blowUpBomb() {
		for(Iterator<Bomb> it = bombs.iterator(); it.hasNext();) {
			Bomb bomb = it.next();
			if (bomb.isBlowedUp()) {
				stage.removeBomb(bomb);
				it.remove();
				bombCounter--;
			}
		}
	}
	
	public void update() {
		dropBomb();
		blowUpBomb();
		super.update();
	}
	
	@Override
	public void render(GL2 gl2) {
		super.render(gl2);
	}
}
