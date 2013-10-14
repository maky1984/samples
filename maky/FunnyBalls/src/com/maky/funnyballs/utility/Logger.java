package com.maky.funnyballs.utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton that manages all logs
 * 
 * @author michael
 * 
 */

public class Logger {

	public static boolean DO_DEBUG = true;
	public static boolean DO_ERROR = true;
	public static boolean DO_INFO = true;
	public static boolean DO_NOTICE = false;
	private static final Map<Class<?>, Object> loggers = new HashMap<Class<?>, Object>();

	private final String className;

	private Logger(Class<?> cl) {
		className = cl.getName() + ":";
	}

	public static Logger getInstance(Object object) {
		Class<?> cl = object.getClass();
		Logger instance = (Logger) loggers.get(cl);
		if (instance == null) {
			instance = new Logger(cl);
			loggers.put(cl, instance);
		}
		return instance;
	}

	public void logError(String msg) {
		System.out.println(className + msg);
	}

	public void logDebug(String msg) {
		if (DO_DEBUG) {
			System.out.println(className + msg);
		}
	}

	public void logException(Throwable e) {
		System.out.println(className + e);
	}

	public void logException(String str, Throwable e) {
		System.out.println(className + str + "/r/n" + e);
		e.printStackTrace(System.out);
	}

	public void logInfo(String msg) {
		if (DO_INFO) {
			System.out.println(className + msg);
		}
	}

	public void logNotice(String msg) {
		if (DO_NOTICE) {
			System.out.println(className + msg);
		}
	}

}
