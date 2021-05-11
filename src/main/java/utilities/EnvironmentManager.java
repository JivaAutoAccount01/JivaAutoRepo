package utilities;

import java.io.IOException;
import java.util.Properties;

public class EnvironmentManager extends DriverManager {
	
	
	public static Properties properties;
	
	public static String getEnvironment(String url) {
		return properties.getProperty(url);
	}
	public static String getDelayTime() {
		return properties.getProperty("timeout");
	}
	
	public static String getFarmerName() {
		return properties.getProperty("FarmerName");
	}
	
	public static String getExcelPath(String path) {
		return properties.getProperty(path);
	}
	
//	public static String getTestDataFilePath() {
	//	return properties.getProperty("testdatapath");
	//}
	
	public static String getValue(String key) {
		return (System.getProperty(key, properties.getProperty(key)));
	}
	
	public static String getDeviceName(String DeviceName) {
		return properties.getProperty(DeviceName);
	}
	
	
	static {
		properties = new Properties();
		try {
			properties
					.load(EnvironmentManager.class.getClassLoader().getResourceAsStream("config/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
}