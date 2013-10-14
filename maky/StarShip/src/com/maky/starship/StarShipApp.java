package com.maky.starship;

import com.maky.App;
import com.maky.IAppComp;

public class StarShipApp extends App {

	public StarShipApp(IAppComp comp) {
		super(comp);
	}

	public static void main(String[] args) {
		IAppComp comp = new StarShipComponent();
		StarShipApp app = new StarShipApp(comp);
		app.start();
	}
}
