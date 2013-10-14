package com.maky;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.AnimatorBase;
import com.jogamp.opengl.util.FPSAnimator;

public class App {

	private GLCanvas canvas;
	private AnimatorBase animator;
	private IAppComp appComp;
	
	// Screen coordinates
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;
	
	// Virtual world constants
	public static final int VIRTUAL_WIDTH = 6400;
	public static final int VIRTUAL_HEIGHT = 4800;
	public static final int VIRTUAL_DEPTH = 5000;
	
	// OpenGL world constants
	public static final float GL_SCENE_WIDTH = 1.0f;
	public static final float GL_SCENE_HEIGHT = 1.0f;
	public static final float GL_SCENE_DEPTH = 1.0f;
	
	// Additional constants
	public static final int FPS = 60;

	private long time;
	private long frames;

	public static long toMilliSeconds(long frames) {
		return 1000 * frames / FPS;
	}

	public static long toFrames(long milliseconds) {
		return milliseconds * FPS / 1000;
	}
	
	public static float toGLx(int x) {
		return GL_SCENE_WIDTH * x / VIRTUAL_WIDTH;
	}
	
	public static float toGLy(int y) {
		return GL_SCENE_HEIGHT * y / VIRTUAL_HEIGHT;
	}

	public static float toGLz(int z) {
		return GL_SCENE_DEPTH * z / VIRTUAL_DEPTH;
	}
	
	public static int toVirtualX(float x) {
		return Math.round(VIRTUAL_WIDTH * x / GL_SCENE_WIDTH);
	}
	
	public static int toVirtualY(float y) {
		return Math.round(VIRTUAL_HEIGHT * y / GL_SCENE_HEIGHT);
	}

	public App(IAppComp appComponent) {
		time = 0;
		frames = 0;
		appComp = appComponent;
	}

	public void start() {
		appComp.start();
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
				glu.gluOrtho2D(0.0f, GL_SCENE_WIDTH, 0.0f, GL_SCENE_HEIGHT);
				gl2.glMatrixMode(GL2.GL_MODELVIEW);
				gl2.glLoadIdentity();
				//gl2.glViewport(0, 0, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
			}

			public void dispose(GLAutoDrawable arg0) {
				System.out.println("dispose");
			}

			public void display(GLAutoDrawable drawable) {
				//System.out.print("display totalFps:");
				appComp.update();
				appComp.render(drawable.getGL().getGL2());
				frames++;
				long spentSeconds = 1 + (System.currentTimeMillis() - time) / 1000;
				long totalFps = frames / spentSeconds;
				//System.out.println(totalFps);
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

		frame.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					appComp.action(IAppActionListener.ACTION_UP_RELEASED);
					break;
				case KeyEvent.VK_DOWN:
					appComp.action(IAppActionListener.ACTION_DOWN_RELEASED);
					break;
				case KeyEvent.VK_RIGHT:
					appComp.action(IAppActionListener.ACTION_RIGHT_RELEASED);
					break;
				case KeyEvent.VK_LEFT:
					appComp.action(IAppActionListener.ACTION_LEFT_RELEASED);
					break;
				case KeyEvent.VK_SPACE:
					appComp.action(IAppActionListener.ACTION_PUT_BOMB_RELEASED);
					break;
				}
			}

			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					appComp.action(IAppActionListener.ACTION_UP_PRESSED);
					break;
				case KeyEvent.VK_DOWN:
					appComp.action(IAppActionListener.ACTION_DOWN_PRESSED);
					break;
				case KeyEvent.VK_RIGHT:
					appComp.action(IAppActionListener.ACTION_RIGHT_PRESSED);
					break;
				case KeyEvent.VK_LEFT:
					appComp.action(IAppActionListener.ACTION_LEFT_PRESSED);
					break;
				case KeyEvent.VK_SPACE:
					appComp.action(IAppActionListener.ACTION_PUT_BOMB_PRESSED);
					break;
				}
			}
		});
		frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		frame.setVisible(true);
		animator.start();
	}
}
