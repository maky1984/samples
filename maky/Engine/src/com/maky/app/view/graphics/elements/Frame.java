package com.maky.app.view.graphics.elements;

import com.maky.app.resource.IResources;
import com.maky.app.resource.RImage;
import com.maky.app.resource.ResourceManager;
import com.maky.app.view.View;
import com.maky.environment.AppColor;
import com.maky.environment.AppGraphics;
import com.maky.environment.AppImage;
import com.maky.environment.GraphicsConfig;
import com.maky.util.log.Logger;

/**
 * Represents graphic frame, that can be used in any object
 * 
 * @author michael
 * 
 */

public class Frame extends GraphicElement {

	public static final int THICKNESS_IMAGES_USED = -1;
	public static final int CENTER = -1;

	protected final int bodyWidth, bodyHeight;
	private final int leftBorderWidth;
	private final int leftBorderHeight;
	private final int topBorderHeight;
	private final int topBorderWidth;
	private final int leftBorderCount;
	private final int topBorderCount;
	private final RImage leftTopCorner;
	private final RImage rightTopCorner;
	private final RImage leftBottomCorner;
	private final RImage rightBottomCorner;
	private final RImage leftBorder;
	private final RImage rightBorder;
	private final RImage topBorder;
	private final RImage bottomBorder;
	private final int frameThickness;
	private AppColor color;

	private final AppImage buffer;
	private final AppGraphics bufferGraphics;

	/**
	 * Constructs graphic frame with solid filling
	 * 
	 * @param offsetX
	 *            - left top corner x
	 * @param offsetY
	 *            - left top corner y
	 * @param bodyWidth
	 *            - width of frame body
	 * @param bodyHeight
	 *            - height of frame body
	 * @param thickness
	 *            - thickness of frame or -1 if frame builded with images
	 * @param color
	 *            - color of frame filling
	 */
	public Frame(int x, int y, int bodyWidth, int bodyHeight, int thickness, View view) {
		super(x == CENTER ? GraphicsConfig.getCenterX(bodyWidth) : x, y == CENTER ? GraphicsConfig.getCenterY(bodyHeight) : y, view);
		this.bodyWidth = bodyWidth < 1 ? 1 : bodyWidth;
		this.bodyHeight = bodyHeight < 1 ? 1 : bodyHeight;
		if (thickness == THICKNESS_IMAGES_USED) {
			ResourceManager RM = ResourceManager.getInstance();
			leftBorder = (RImage) RM.getResource(IResources.IMAGE_FRAME_LEFT_BORDER);
			rightBorder = (RImage) RM.getResource(IResources.IMAGE_FRAME_RIGHT_BORDER);
			topBorder = (RImage) RM.getResource(IResources.IMAGE_FRAME_TOP_BORDER);
			bottomBorder = (RImage) RM.getResource(IResources.IMAGE_FRAME_BOTTOM_BORDER);
			leftTopCorner = (RImage) RM.getResource(IResources.IMAGE_FRAME_LEFT_TOP_CORNER);
			rightTopCorner = (RImage) RM.getResource(IResources.IMAGE_FRAME_RIGHT_TOP_CORNER);
			leftBottomCorner = (RImage) RM.getResource(IResources.IMAGE_FRAME_LEFT_BOTTOM_CORNER);
			rightBottomCorner = (RImage) RM.getResource(IResources.IMAGE_FRAME_RIGHT_BOTTOM_CORNER);

			leftBorderWidth = leftBorder.getImage().getWidth(null);
			leftBorderHeight = leftBorder.getImage().getHeight(null);
			topBorderHeight = topBorder.getImage().getHeight(null);
			topBorderWidth = topBorder.getImage().getWidth(null);
			int addition = (getBodyHeight() % leftBorder.getImage().getHeight(null)) > 0 ? 1 : 0;
			leftBorderCount = getBodyHeight() / leftBorder.getImage().getHeight(null) + addition;
			addition = (getBodyWidth() % topBorder.getImage().getWidth(null)) > 0 ? 1 : 0;
			topBorderCount = getBodyWidth() / topBorder.getImage().getWidth(null) + addition;
			Logger.getInstance(this).logInfo(
					" Contructing graphic frame leftBorderCount = " + leftBorderCount + " topBorderCount = "
							+ topBorderCount);
			frameThickness = THICKNESS_IMAGES_USED;
			this.color = null;
		} else {
			frameThickness = thickness;
			leftBorderWidth = frameThickness;
			leftBorderHeight = frameThickness;
			topBorderHeight = frameThickness;
			topBorderWidth = frameThickness;
			leftBorderCount = 0;
			topBorderCount = 0;
			leftTopCorner = null;
			rightTopCorner = null;
			leftBottomCorner = null;
			rightBottomCorner = null;
			leftBorder = null;
			rightBorder = null;
			topBorder = null;
			bottomBorder = null;
			this.color = AppColor.BLACK;
		}
		if (x == CENTER) {
			setX(getX() - leftBorderWidth);
		}
		if (y == CENTER) {
			setY(getY() - topBorderHeight);
		}
		if (frameThickness == 0) {
			buffer = null;
			bufferGraphics = null;
		} else {
			buffer = getView().createBufferImage(getWidth(), getHeight());
			bufferGraphics = buffer.getGraphics();
			repaintFrame();
		}
	}

	public Frame(int bodyWidth, int bodyHeight, int thickness, View view) {
		this(CENTER, CENTER, bodyWidth, bodyHeight, thickness, view);
	}

	public Frame(int x, int y, GraphicElement body, int thickness) {
		this(x, y, body.getWidth(), body.getHeight(), thickness, body.getView());
	}

	public Frame(int x, int y, GraphicElement body) {
		this(x, y, body, THICKNESS_IMAGES_USED);
	}

	public void setColor(AppColor color) {
		this.color = color;
	}

	public int getBodyLeftTopX() {
		return getX() + leftBorderWidth;
	}

	public int getBodyLeftTopY() {
		return getY() + topBorderHeight;
	}

	public int getHeight() {
		return bodyHeight + 2 * topBorderHeight;
	}

	public int getWidth() {
		return bodyWidth + 2 * leftBorderWidth;
	}

	public int getFrameWidth() {
		return leftBorderWidth;
	}

	public int getFrameHeight() {
		return topBorderHeight;
	}

	public int getBodyWidth() {
		return bodyWidth;
	}

	public int getBodyHeight() {
		return bodyHeight;
	}

	private void repaintFrame() {
		bufferGraphics.clearWithColor(AppColor.TRANSP, getWidth(), getHeight());
		// TODO: Temporary code
		bufferGraphics.setColor(AppColor.PINK);
		bufferGraphics.fillRect(getX(), getY(), getWidth(), getHeight());
		// END
		if (frameThickness == THICKNESS_IMAGES_USED) {
			bufferGraphics.drawImage(leftTopCorner.getImage(), 0, 0, null);
			bufferGraphics.drawImage(rightTopCorner.getImage(), leftBorderWidth + bodyWidth, 0, null);
			bufferGraphics.drawImage(leftBottomCorner.getImage(), 0, topBorderHeight + bodyHeight, null);
			bufferGraphics.drawImage(rightBottomCorner.getImage(), leftBorderWidth + bodyWidth, topBorderHeight
					+ bodyHeight, null);
			for (int i = 0; i < leftBorderCount; i++) {
				bufferGraphics.drawImage(leftBorder.getImage(), 0, topBorderHeight + i * leftBorderHeight, null);
				bufferGraphics.drawImage(rightBorder.getImage(), leftBorderWidth + bodyWidth, topBorderHeight + i
						* leftBorderHeight, null);
			}
			for (int i = 0; i < topBorderCount; i++) {
				bufferGraphics.drawImage(topBorder.getImage(), leftBorderWidth + i * topBorderWidth, 0, null);
				bufferGraphics.drawImage(bottomBorder.getImage(), leftBorderWidth + i * topBorderWidth, topBorderHeight
						+ bodyHeight, null);
			}
		} else if (frameThickness > 0) {
			bufferGraphics.setColor(color);
			if (frameThickness == 1) {
				bufferGraphics.drawPolyline(new int[] { 0, bodyWidth + 1, bodyWidth + 1, 0, 0 }, new int[] { 0, 0,
						bodyHeight + 1, bodyHeight + 1, 0 }, 5);
			} else {
				bufferGraphics.fillRect(0, 0, bodyWidth + 2 * leftBorderWidth, frameThickness);
				bufferGraphics
						.fillRect(0, bodyHeight + frameThickness, bodyWidth + 2 * leftBorderWidth, frameThickness);
				bufferGraphics.fillRect(0, frameThickness, frameThickness, bodyHeight);
				bufferGraphics.fillRect(bodyWidth + frameThickness, frameThickness, frameThickness, bodyHeight);
			}
		}

	}

	public void paint(AppGraphics gr) {
		if (frameThickness != 0) {
			gr.drawImage(buffer, getX(), getY(), null);
		}
	}
}
