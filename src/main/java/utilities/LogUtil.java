package utilities;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

//import com.gargoylesoftware.htmlunit.html.HtmlAttributeChangeEvent;

/**
 * @author en
 *
 */
public class LogUtil {
	public static Logger errorLogger;
	public static Logger normalLogger;
	public static Logger htmlLogger;
	public static FileAppender normalFileApp;
	public static FileAppender errorFileApp;
	public static FileAppender htmlFileApp;
	
	public static ConsoleAppender conApp;
	public static RollingFileAppender normalRap;
	public static RollingFileAppender errorRap;
	private static boolean isInit = false;
	private LogUtil(){}

	public static PatternLayout patternLayout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n");
	public static PatternLayout consolePatternLayout = new PatternLayout("\tLOG-: [%m -  %d{yyyy-MM-dd HH:mm:ss a}] %n");
	public static HTMLLayout htmlLayout = new HTMLLayout();
	/**
	 * @param clazz
	 */
	public static void init(Class clazz) {
		if (!isInit) {

			try {
				htmlLogger = Logger.getLogger(clazz+",HtmlLogger");
				htmlLogger .setLevel(Level.INFO);
				htmlLayout.setTitle("Automation Logs");
				htmlFileApp = new FileAppender(htmlLayout, ConfigReader.getValue("logHtmlFilePath"));
				htmlFileApp.setImmediateFlush(true);
				htmlFileApp .setAppend(true);
				htmlFileApp.activateOptions();
				htmlLogger.addAppender(htmlFileApp);
			
								
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			
			
			normalLogger = Logger.getLogger(clazz + ",NormalLogger");
			normalLogger.setLevel(Level.INFO);

			normalFileApp = new FileAppender();
			normalFileApp.setLayout(patternLayout);
			normalFileApp.setFile(ConfigReader.getValue("logInfoFilePath"));

			normalFileApp.setImmediateFlush(true);
			normalFileApp .setAppend(true);
			normalLogger.addAppender(normalFileApp);
			normalFileApp.activateOptions();

			// Rolling File Appender for maximum 5 mb log file size
			try {
				normalRap = new RollingFileAppender(patternLayout, normalFileApp.getFile());
				normalRap.setMaxBackupIndex(5);
				normalRap.setMaximumFileSize(5);
				normalRap.activateOptions();
			} catch (IOException e) {
				LogUtil.errorLog(LogUtil.class, "Exception caught", e);
			}

			errorLogger = Logger.getLogger(clazz + ",ErrorLogger");
			errorLogger.setLevel(Level.ERROR);
			errorFileApp = new FileAppender();
			errorFileApp.setLayout(patternLayout);
			errorFileApp.setFile(ConfigReader.getValue("logErrorFilePath"));

			errorFileApp.setImmediateFlush(true);
			errorFileApp .setAppend(true);
			errorLogger.addAppender(errorFileApp);
			errorFileApp.activateOptions();

			try {
				errorRap = new RollingFileAppender(patternLayout, errorFileApp.getFile());
				normalRap.setMaxBackupIndex(5);
				normalRap.setMaximumFileSize(5);
				normalRap.activateOptions();
			} catch (IOException e) {
				LogUtil.errorLog(LogUtil.class, "Exception caught", e);
			}

			conApp = new ConsoleAppender();
			conApp.setLayout(consolePatternLayout);
			conApp.setTarget("System.out");
			conApp.activateOptions();
			
			normalLogger.addAppender(conApp);

			isInit = true;
		}
	}

	/**
	 * @param className
	 */
	

	/**
	 * @param clazz
	 * @param message
	 */
	public static void infoLog(Class clazz, String message) {
		init(clazz);
		normalLogger.info(message);
		htmlLogger.info(message);
	}

	/**
	 * @param className
	 * @param message
	 */
	public static void infoLog(String className, String message) {
		
		normalLogger.info(message);
		htmlLogger.info(message);
	}

	/**
	 * @param clazz
	 * @param message
	 * @param t
	 */
	public static void errorLog(Class clazz, String message, Throwable t) {
		init(clazz);
		htmlLogger.error(message,t);
		errorLogger.error(message, t);
		errorLogger.error("----------------------------------------------------------------------");

	}

	/**
	 * @param clazz
	 * @param message
	 */
	public static void errorLog(Class clazz, String message) {
		init(clazz);
		htmlLogger.error(message);
		errorLogger.error(message);
		errorLogger.error("-----------------------------------------------------------------------");

	}

	/**
	 * @param name
	 * @param message
	 */
	public static void errorLog(String name, String message) {
		
		htmlLogger.error(message);
		errorLogger.error(message);
		errorLogger.error("-----------------------------------------------------------------------");

	}


}

