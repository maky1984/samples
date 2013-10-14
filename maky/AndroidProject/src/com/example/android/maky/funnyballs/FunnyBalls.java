package com.example.android.maky.funnyballs;

import android.app.Activity;
import android.os.Bundle;

public class FunnyBalls extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graphics_test);
		// get handles to the LunarView from XML, and its LunarThread
		GraphicsTest graph = (GraphicsTest) findViewById(R.id.SurfaceView01);
	}
}