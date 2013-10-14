package com.maky.bomberman;

import com.maky.App;

public class BomberManStart extends App {

	public BomberManStart(Stage stage) {
		super(stage);
	}

	public static void main(String[] args) {
		BomberManStart app = new BomberManStart(new Stage());
		app.start();
	}

}
