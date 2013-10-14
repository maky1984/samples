package com.android.maky.tab.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TabTest extends Activity {

	private TextView text;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		text = (TextView) findViewById(R.id.text);
		CameraView surface = (CameraView) findViewById(R.id.SurfaceView01);
	}
}