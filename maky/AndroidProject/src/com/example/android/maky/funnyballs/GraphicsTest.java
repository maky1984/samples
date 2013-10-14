package com.example.android.maky.funnyballs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GraphicsTest extends SurfaceView implements SurfaceHolder.Callback {

	SurfaceHolder mainHolder = getHolder();

	public GraphicsTest(Context context) {
		super(context);
		mainHolder.addCallback(this);
	}
	
	public GraphicsTest(Context context, AttributeSet set) {
		super(context, set);
		mainHolder.addCallback(this);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	public void surfaceCreated(final SurfaceHolder holder) {
		Thread task = new Thread() {
			public void run() {
				boolean work = true;
				while(work) {
	 				System.out.println(" Surface created");
					Canvas canvas = mainHolder.lockCanvas();
					if (canvas == null) {
						work = false;
					} else {
						Bitmap bitmap = Bitmap.createBitmap(200, 200, Config.ARGB_4444);
						Canvas bitmapCanvas = new Canvas(bitmap);
						Paint paint = new Paint();
						bitmapCanvas.drawColor(Color.BLUE);
						paint.setColor(Color.RED);
						bitmapCanvas.drawText("STRING TEST", 10, 10, paint);
						canvas.drawBitmap(bitmap, new Matrix(), null);
						mainHolder.unlockCanvasAndPost(canvas);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		task.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
	}
}
