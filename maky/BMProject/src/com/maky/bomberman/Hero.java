package com.maky.bomberman;

public class Hero extends ControlledObject {

	public static final int WIDTH = 600;
	public static final int HEIGHT= 400; 
	
	public Hero(String name, int x, int y, Stage stage) {
		super(name, x, y, WIDTH, HEIGHT, stage);
	}
}
