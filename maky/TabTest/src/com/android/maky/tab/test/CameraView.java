package com.android.maky.tab.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = "CameraView";
	private SurfaceHolder holder;

	public CameraView(Context context) {
		super(context);
		holder = getHolder();
		holder.addCallback(this);
	}

	public CameraView(Context context, AttributeSet set) {
		super(context, set);
		holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		Log.i(TAG, "surfceChanged");
	}

	public void surfaceCreated(SurfaceHolder arg0) {
		Log.i(TAG, "surfaceCreated");
		Thread cameraTask = new TaskCamera(arg0);
		cameraTask.start();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.i(TAG, "surfaceDestroyed");
	}
}
