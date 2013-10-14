package com.maky.arkanoid;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

import com.maky.util.log.Logger;

public class ArkanoidActivity extends Activity {

	private GameLayoutGL mGLView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGLView = new GameLayoutGL(this);
		setContentView(mGLView);
		Logger.getInstance(this).logInfo("onCreate called");
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}
	
	/* load our native library */
	static {
		System.loadLibrary("arkanoid");
	}

}