package com.test.notifier.runner;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotifierServiceRunner {

	private static final Logger logger = LoggerFactory.getLogger(NotifierServiceRunner.class);

	public static void main(String[] args) {
		System.out.println("USAGE: [portNumber] [servicePath]");
		System.out.println("DEFALT: 8070 network-ad-service");
		String port = args.length > 0 ? args[0] : "8070";
		String servicePath = args.length > 1 ? args[1] : "notifier-service"; 
		try {
			String webappDirLocation = "src/main/webapp/";
			Tomcat tomcat = new Tomcat();
			tomcat.setPort(Integer.valueOf(port));
			tomcat.addWebapp("/" + servicePath, new File(webappDirLocation).getAbsolutePath());
			logger.info("configuring app with basedir: " + new File(webappDirLocation).getAbsolutePath());
			tomcat.start();
			tomcat.getServer().await();
		} catch (Exception e) {
			logger.error("Error:", e);
		}
	}
}
