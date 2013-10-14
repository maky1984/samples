package com.maky.funnyballs;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author  michael
 */
public class FunnyBall extends Activity {

	private String PREFERENCES_NAME = "fbpreferences";
	/**
	 * @uml.property  name="game"
	 * @uml.associationEnd  
	 */
	private GameLayout game;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		LayoutInflater inflater = LayoutInflater.from(this);
		game = (GameLayout) inflater.inflate(R.layout.main, null);
		game.setPreferences(getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE));
		setContentView(game);
	}
}