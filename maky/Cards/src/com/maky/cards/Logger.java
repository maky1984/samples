package com.maky.cards;

import java.io.PrintStream;

public class Logger {
	
	private static Logger instance;
	private static PrintStream out;
	
	private Logger() {
		out = System.out;
	}
	
	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	public void setPrintStream(PrintStream out) {
		Logger.out = out;
	}
	
	public void log(String msg) {
		out.println(msg);
	}
	
	public void log(String msg, Exception e) {
		log(msg);
		e.printStackTrace(out);
	}
}
