
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
/**
 * This ConfigReader file will read the config file 
 *
 */

public class ConfigReader {
	
	public static FileInputStream fileInput = null;
	public static Properties prop = new Properties();
	public static File file;
	/**
	 * will read the properties file with this function
	 * @param filePath
	 * @return
	 */
	
	public static String getValue(String key) {
		// Read from properties file
		 file = new File("./src/main/resources/ConfigFiles/config.properties");	
		try {
			fileInput = new FileInputStream(file);
			prop.load(fileInput);
		} catch (Exception e) {
			LogUtil.errorLog(ConfigReader.class, "Caught the exception", e);
		}
		return prop.getProperty(key);

	}
	
}
