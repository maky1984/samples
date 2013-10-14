package com.android.maky.tab.test;

import java.io.IOException;

import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

public class TaskCamera extends Thread {

	private static final String TAG = "TaskCamera:";
	private String text;
	private SurfaceHolder holder;

	public TaskCamera(SurfaceHolder holder) {
		this.holder = holder;
	}

	@Override
	public void run() {
		super.run();
		StringBuilder result = new StringBuilder();
		Camera current = Camera.open();
		if (current == null) {
			result.append("There is no camera on the device or maybe in use.");
		} else {
			Camera.Parameters cameraParams = current.getParameters();
			//cameraParams.setPreviewFormat(Camera.Parameters.)
			result.append("Params returned...");
			Log.i(TAG, "Parameters returned..." + cameraParams);
			try {
				current.setPreviewDisplay(holder);
			} catch (IOException e) {
				Log.wtf(TAG, e);
			}
			current.startPreview();
		}
		text = result.toString();
	}

	public String getText() {
		return text;
	}
}
