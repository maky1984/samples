package com.maky.bomberman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.maky.IAppComp;

public class Stage implements IAppComp {

	private final static int HERO_START_X = 0;
	private final static int HERO_START_Y = 0;

	private String filename = "map.dat";
	private Map map;
	private Hero hero;
	private List<Bomb> bombs;
	private List<ControlledObject> enemies;

	public Stage() {
	}

	public void start() {
		try {
			map = new Map(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		hero = new Hero("Main Hero", HERO_START_X, HERO_START_Y, this);
		hero.setMoveSpeed(Hero.SPEED_1);
		hero.linkMap(map);
		bombs = new ArrayList<Bomb>();
		enemies = new ArrayList<ControlledObject>();
	}

	public void action(int action) {
		switch (action) {
		case ACTION_DOWN_PRESSED:
			hero.startMoveDown();
			break;
		case ACTION_DOWN_RELEASED:
			hero.stopMoveDown();
			break;
		case ACTION_UP_PRESSED:
			hero.startMoveUp();
			break;
		case ACTION_UP_RELEASED:
			hero.stopMoveUp();
			break;
		case ACTION_LEFT_PRESSED:
			hero.startMoveLeft();
			break;
		case ACTION_LEFT_RELEASED:
			hero.stopMoveLeft();
			break;
		case ACTION_RIGHT_PRESSED:
			hero.startMoveRight();
			break;
		case ACTION_RIGHT_RELEASED:
			hero.stopMoveRight();
			break;
		case ACTION_PUT_BOMB_PRESSED:
			hero.startBombing();
			break;
		case ACTION_PUT_BOMB_RELEASED:
			hero.stopBombing();
			break;
		default:
			System.out.println("Action not supported");
			break;
		}
	}

	public void update() {
		map.update();
		hero.update();
		for (Bomb bomb : bombs) {
			bomb.update();
		}
		for (ControlledObject enemy : enemies) {
			enemy.update();
		}
	}

	public void render(GL2 gl2) {
		gl2.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl2.glLoadIdentity();
		map.render(gl2);
		hero.render(gl2);
		for (Bomb bomb : bombs) {
			bomb.render(gl2);
		}
		for (ControlledObject enemy : enemies) {
			enemy.render(gl2);
		}
		gl2.glFlush();
	}

	public void addBomb(Bomb bomb) {
		bomb.linkMap(map);
		bombs.add(bomb);
	}

	public void removeBomb(Bomb bomb) {
		bombs.remove(bomb);
	}

}
