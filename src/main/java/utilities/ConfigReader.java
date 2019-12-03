
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
/**
 * This ConfigReader file will read the config file 
 *
 */

public class ConfigReader {
	
	static FileInputStream fileInput = null;
	static Properties prop = new Properties();
	 static File file;
	/**
	 * will read the properties file with this function
	 * @param filePath
	 * @return
	 */
	
	public static Properties loadPropertyFile(String filePath) {
		// Read from properties file
		 file = new File(filePath);
		
		
	
		try {
			fileInput = new FileInputStream(file);
			prop.load(fileInput);
		} catch (Exception e) {
			LogUtil.errorLog(ConfigReader.class, "Caught the exception", e);
			
		

		}
		return prop;

	}
	/**
	 * will get sting value from properties file
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {

	 prop = loadPropertyFile("src\\main\\resources\\ConfigFiles\\config.properties");
		// Open the URL in firefox browser
		 return prop.getProperty(key);
	}
	/**
	 * will get int value from properties file
	 * @param key
	 * @return
	 */
	public static int getIntValue(String key) {
		 prop = loadPropertyFile("src\\main\\resources\\ConfigFiles\\config.properties");

		// Open the URL in firefox browser
		String strKey = prop.getProperty(key);

		return Integer.parseInt(strKey);
	}

}
