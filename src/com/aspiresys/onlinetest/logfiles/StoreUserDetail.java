package com.aspiresys.onlinetest.logfiles;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class StoreUserDetail {
	static final Logger logger = Logger.getLogger(StoreUserDetail.class);

	// store the details what we need
	public static void storeUserInfo(String message) throws IOException {
		PropertyConfigurator
				.configure(System.getProperty("user.dir")+"/src/com/aspiresys/onlinetest/logfiles/TrackUserDetail.properties");
		logger.error(message);

		/*
		 * FileAppender fileappender = new FileAppender(new
		 * HTMLLayout(),"/home/ganesh/Desktop/Dinesh/J2EE/OnlineTest/sam.html");
		 * logger.addAppender(fileappender); logger.info("Welcome");
		 * logger.info("to"); logger.info("Rose India");
		 * logger.info("-----------");
		 */
	}
}
