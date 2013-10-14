package com.maky.app.controller.commander;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.maky.util.log.Logger;

public class MouseKeyboardCommander extends Commander implements MouseMotionListener, MouseListener, KeyListener {

	//TODO: Post mouse events only when someone is listening for them
	
	private Logger logger;

	public MouseKeyboardCommander(Canvas canvas) {
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addKeyListener(this);
		logger = Logger.getInstance(this);
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		int result = command(Command.COMMAND_MOUSE_MOVED, e.getX(), e.getY());
		logger.logNotice(" MouseEvent e=" + e + " result=" + result);
	}

	public void mouseClicked(MouseEvent e) {
		int result = command(Command.COMMAND_SELECT, e.getX(), e.getY());
		logger.logInfo(" MouseEvent e=" + e + " result=" + result);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		int result = Command.RESULT_COMMAND_SKIPPED;
		if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
			result = command(Command.COMMAND_LEFT);
		} else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
			result = command(Command.COMMAND_RIGHT);
		} else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP) {
			result = command(Command.COMMAND_UP);
		} else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_KP_DOWN) {
			result = command(Command.COMMAND_DOWN);
		} else if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			result = command(Command.COMMAND_SELECT);
		} else if (code == KeyEvent.VK_ESCAPE) {
			result = command(Command.COMMAND_ESC);
		} else if (code >= KeyEvent.VK_0 && code <= KeyEvent.VK_9) {
			result = command(Command.COMMAND_NUMBER, code);
		} else if (code == KeyEvent.VK_BACK_SPACE) {
			result = command(Command.COMMAND_BACK);
		}
		logger.logInfo(" KeyEvent e=" + e + " result=" + result);
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
