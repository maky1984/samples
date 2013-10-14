package com.maky.bomberman.editor;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.AnimatorBase;
import com.jogamp.opengl.util.FPSAnimator;
import com.maky.bomberman.Map;
import com.maky.bomberman.MapCell;

public class MapEditor implements KeyListener, MouseListener {
	private GLCanvas canvas;
	private AnimatorBase animator;
	public static final int WIDTH = 6400;
	public static final int HEIGHT = 4800;
	public static final int FPS = 60;
	
	private final int cellW, cellH, currentType;

	private long time;
	private long frames;

	private String filename = "map.dat";
	private Map map;
	private static final int MAP_SIZE = 100;

	public static long toMilliSeconds(long frames) {
		return 1000 * frames / FPS;
	}

	public static long toFrames(long milliseconds) {
		return milliseconds * FPS / 1000;
	}

	public MapEditor() {
		cellW = WIDTH / MAP_SIZE;
		cellH = HEIGHT / MAP_SIZE;
		time = 0;
		frames = 0;
		currentType = MapCell.TYPE_ROCK;
	}

	public void start() {
		loadMap();
		GLCapabilities caps = new GLCapabilities(GLProfile.getDefault());
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);
		canvas = new GLCanvas(caps);
		// animator = new Animator(canvas);
		animator = new FPSAnimator(canvas, 60);
		canvas.addGLEventListener(new GLEventListener() {

			public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
				System.out.println("reshape");
			}

			public void init(GLAutoDrawable arg0) {
				System.out.println("init");
				time = System.currentTimeMillis();
				GL2 gl2 = arg0.getGL().getGL2();
				gl2.glMatrixMode(GL2.GL_PROJECTION);
				gl2.glLoadIdentity();
				// coordinate system origin at lower left with width and height same as the window
				GLU glu = new GLU();
				glu.gluOrtho2D(0.0f, 1.0f, 1.0f, 0.0f);
				gl2.glMatrixMode(GL2.GL_MODELVIEW);
				gl2.glLoadIdentity();
				gl2.glViewport(0, 0, WIDTH, HEIGHT);
			}

			public void dispose(GLAutoDrawable arg0) {
				System.out.println("dispose");
			}

			public void display(GLAutoDrawable drawable) {
				GL2 gl2 = drawable.getGL().getGL2();
		        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );
		        gl2.glLoadIdentity();
				map.render(gl2);
				gl2.glFlush();
				frames++;
				long spentSeconds = 1 + (System.currentTimeMillis() - time) / 1000;
				long totalFps = frames / spentSeconds;
			}
		});
		final Frame frame = new Frame("Application frame");
		frame.add(canvas);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				frame.remove(canvas);
				frame.dispose();
				System.exit(0);
			}
		});
		canvas.addMouseListener(this);
		canvas.addKeyListener(this);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		animator.start();
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed " + e);
		int x = e.getX();
		int y = e.getY();
		int i = x / cellW;
		int j = y / cellH;
		MapCell cell = map.get(i, j);
		cell.setType(currentType);
		map.set(i, j, cell);
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("Key pressed " + e);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_S:
			saveMap();
			break;
		}

	}

	public void keyReleased(KeyEvent e) {
	}

	public void loadMap() {
		try {
			map = new Map(filename);
		} catch (IOException e) {
			System.out.println("Map not found in " + filename + ". New one created.");
			map = new Map(MAP_SIZE);
		}
	}

	public void saveMap() {
		map.saveMap(filename);
		System.out.println("Map saved.");
	}

	public static void main(String[] args) {
		MapEditor app = new MapEditor();
		app.start();
	}
}
