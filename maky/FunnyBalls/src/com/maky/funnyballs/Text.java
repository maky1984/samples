package com.maky.funnyballs;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * @author  michael
 */
public class Text {
	
	/**
	 * @uml.property  name="game"
	 * @uml.associationEnd  
	 */
	private final GameLayout game;
	private final Bitmap font;
	private final char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'V', 'X', 'Y', 'Z' };
	private int[] positions = { 8, 30, 48, 71, 94, 117, 141, 164, 182, 206, 229, 253, 277, 301, 325, 345, 363, 387,
			412, 426, 450, 468, 498, 522, 546, 569, 593, 616, 639, 661, 685, 707, 728, 746 };

	private int defisCharStartX = 1210;
	private int defisCharEndX = 1230;
	
	public Text(GameLayout game) {
		this.game = game;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;
		font = BitmapFactory.decodeResource(game.getResources(), R.drawable.font, options);
		// for (int i=0;i<positions.length;i++) {
		// positions[i] = game.getUtil().getSX(positions[i]);
		// }
	}

	public Bitmap createMsgBitmap(String msg) {
		char[] msgChars = msg.toCharArray();
		Bitmap[] images = new Bitmap[msgChars.length];
		int number = 0;
		int width = 0;
		int height = font.getHeight();
		int imageWidth;
		for (char c : msgChars) {
			if (c == ' ') {
				imageWidth = 15;
				images[number++] = Bitmap.createBitmap(imageWidth, height, Config.ARGB_8888);
			} else if (c == '-') {
				imageWidth = defisCharEndX - defisCharStartX;
				images[number++] = Bitmap.createBitmap(font, defisCharStartX, 0, imageWidth, height);
			} else {
				int i = positions[0];
				for (int index = 0; index < chars.length; index++) {
					if (chars[index] == c) {
						i = index;
						break;
					}
				}
				imageWidth = positions[i + 1] - positions[i] - 5;
				images[number++] = Bitmap.createBitmap(font, positions[i] + 2, 0, imageWidth, height);
			}
			width += imageWidth;
		}
		Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas comboImage = new Canvas(result);
		int offset = 0;
		for (int i = 0; i < images.length; i++) {
			comboImage.drawBitmap(images[i], offset, 0f, null);
			offset += images[i].getWidth();
		}
		return result;
	}

	public IGameComponent getTextComponent(String msg, int x, int y, final Paint paint) {
		return getTextComponent(msg, x, y, paint, null);
	}

	public IGameComponent getTextComponent(String msg, int x, int y, final Paint paint, Rect destantion) {
		final Bitmap image = createMsgBitmap(msg);
		final Rect dst;
		if (destantion == null) {
			dst = new Rect(x, y, x + image.getWidth(), y + image.getHeight());
		} else {
			dst = destantion;
		}
		IGameComponent component = new IGameComponent() {
			private float d = 0;
			private boolean direction = true;
			private int delta;
			private Paint textPaint = (paint == null) ? new Paint() : paint;
			@Override
			public void update() {
				delta = 100 * 1000 / (700 * game.getFPS());
				if (d < 100 && direction) {
					d += delta;
				} else if (d > 0) {
					d -= 10;
					direction = false;
				} else {
					direction = true;
				}
				textPaint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[] { 1, 0, 0, 0, d, 0, 1,
						0, 0, d, 0, 0, 1, 0, d, 0, 0, 0, 1, 0 })));
			}

			@Override
			public boolean touch(MotionEvent event) {
				return false;
			}

			@Override
			public void start() {
			}

			@Override
			public boolean keyPressed(int keyCode) {
				return false;
			}

			@Override
			public void init() {
			}

			@Override
			public void draw(Canvas canvas) {
				canvas.drawBitmap(image, null, dst, textPaint);
			}

			@Override
			public void destroy() {
			}

			@Override
			public boolean isInitialized() {
				return true;
			}
		};
		component.init();
		return component;
	}

}
