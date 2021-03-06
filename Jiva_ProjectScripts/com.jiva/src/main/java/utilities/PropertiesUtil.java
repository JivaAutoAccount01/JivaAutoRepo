package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class is used to read the property values found in 'config.properties'.
 */
public class PropertiesUtil {

	public static final String BROWSER_PROPERTY = "browser";
	public static final String URL_PROPERTY = "url";
	public static final String ROLE_PROPERTY = "role";
	public static final String URLExternal_PROPERTY = "externalUrl";
	public static final String SCREENSHOT_PATH_PROPERTY = "screenshot.path";
	public static final String TESTDATAPATH_PROPERTY = "testdatapath";
	public static final String TESTDATAPATHExternal_PROPERTY = "testdatapathExternal";
	public static final String RUNTIMEPLATFORM_PROPERTY = "runtimePlatform";
	public static final String HUBURL_PROPERTY = "huburl";

	private static Properties prop = new Properties();

	static {
		resetPropertiesUtil("./src/main/resources/config/config.properties");		
	}	

	public static void resetPropertiesUtil(String fileName) {
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return prop.getProperty(key);
	}
	
	/**
	 * Call this method before running integration tests.
	 * This method changes property 'testdatapath' to point to an Excel file intended for integration testing.
	 */
	public static void useIntegrationTestData() {
		prop.setProperty(TESTDATAPATH_PROPERTY, "./TestData/TestDataFile.xls");		
	}

	/**
	 * Convenience method to indicated whether or not 'runtimePlatfrom' property
	 * is set to 'grid'.
	 * 
	 * @return 'true' if property value set to 'grid', else 'false'.
	 */
	public static boolean isGridPlatform() {
		boolean isGrid = false;
		String value = getValue(PropertiesUtil.RUNTIMEPLATFORM_PROPERTY);
		if (value.equalsIgnoreCase("grid")) {
			isGrid = true;
		}
		return isGrid;
	}

	/**
	 * Convenience method to construct and return the grid url.	  
	 * @return String containing 'http://'+localIP+':4545/wd/hub'.  Or return null if not able to discover local IP.
	 */
	public static String getGridUrl() {
		return prop.getProperty(HUBURL_PROPERTY);
	}

	/**
	 * Determine if the browser property is set to 'chrome'.
	 * @return
	 */
	

}