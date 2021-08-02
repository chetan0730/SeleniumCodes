package utils;

import org.apache.log4j.Logger;

public class Log {

// Initialize Log4j logs

	private static Logger Log = Logger.getLogger(Log.class.getName());//

	
	public static void info(String message) {
		System.out.println(message);
		Log.info(message);
		
	}

	public static void warn(String message) {
		System.out.println(message);
		Log.warn(message);
		

	}

	public static void error(String message) {
		System.out.println(message);
		Log.error(message);
		

	}

	public static void fatal(String message) {
		System.out.println(message);
		Log.fatal(message);
		
	}

	public static void debug(String message) {

		Log.debug(message);
		System.out.println(message);

	}

}