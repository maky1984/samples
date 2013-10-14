package com.maky.environment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.maky.util.timing.IUpdateble;

public class AppCanvas extends SurfaceView implements IUpdateble {

	SurfaceHolder holder;

	private Painter painter;
	private AppImage bufferImage;
	private AppGraphics bufferGraphics;
	private AppGraphics graphics;

	public AppCanvas(Context context, AttributeSet set) {
		super(context, set);
		graphics = new AppGraphics();
		holder = getHolder();
	}

	public void update() {
		Canvas canvas = holder.lockCanvas();
		graphics.setGraphics(canvas);
		paint(graphics);
		holder.unlockCanvasAndPost(canvas);
	}

	public void paint(AppGraphics g) {
		if (bufferImage == null) {
			bufferImage = new AppImage(Bitmap.createBitmap(GraphicsConfig.getCanvasWidth(),
					GraphicsConfig.getCanvasHeight(), Config.ALPHA_8));
			bufferGraphics = bufferImage.getGraphics();
		}
		// do normal redraw
		painter.paint(bufferGraphics);
		// transfer off-screen image to window
		g.drawImage(bufferImage, 0, 0, this);
	}

	public void tick() {
		update();
	}

	public Painter getPainter() {
		return painter;
	}
}
