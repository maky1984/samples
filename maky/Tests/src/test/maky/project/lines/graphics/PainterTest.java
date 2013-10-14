package test.maky.project.lines.graphics;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;

import junit.framework.TestCase;

import com.maky.environment.AppColor;
import com.maky.environment.AppGraphics;
import com.maky.environment.IPaintable;
import com.maky.environment.Painter;

public class PainterTest extends TestCase {

	public void testPainter() {
		final AppColor RGBtestColor = new AppColor(100, 100, 100);
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		Frame frame = new Frame("Test mouse frame", gc);
		frame.setBounds(0, 0, 100, 100);
		final Painter painter = new Painter();
		IPaintable testComponenet = new IPaintable() {
			public void paint(AppGraphics gr) {
				gr.setColor(RGBtestColor);
				gr.fillRect(0, 0, 100, 100);
				gr.setColor(new AppColor(0, 0, 0));
			}
		};
		painter.addComponent(testComponenet);
		Canvas canvas = new Canvas() {
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				painter.paint(new AppGraphics(g));
			}
		};
		canvas.setBounds(0, 0, 100, 100);
		frame.add(canvas);
		frame.repaint();
		frame.setVisible(true);
		AppColor color = null;
		try {
			Robot robot = new Robot();
			robot.delay(1000);
			color = new AppColor(robot.getPixelColor(60, 60));
		} catch (AWTException e) {
			e.printStackTrace();
		}
		assertEquals(RGBtestColor, color);
	}
}
