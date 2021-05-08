package utilities;

import org.apache.log4j.Logger;
import org.testng.Reporter;

public class TestLogger {
	/** Initializing the logger class instance */
	public static Logger logger = Logger.getLogger(ElementManager.class.getName());

	/**
	 * @param methodName
	 *            Based on this method name user knows the which method is
	 *            executing
	 */
	public static void methodName(String methodName) {
		Reporter.log("***Test case name = " + methodName,true);
	}

	public static void beforORAfterMetod(String methodName) {
		Reporter.log(methodName,true);
	}

	public static void elementIdentifier(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			Reporter.log("***Web element = '" + elementName + "' is identified successfully***",true);
	}
	
	public static void elementClickIdentifier(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			Reporter.log("***Web element = '" + elementName + "' is identified and clicked***",true);
	}
	
	public static void elementIdentifierFail(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			Reporter.log("_____Web element = '" + elementName + "' is not identified____",true);
			Reporter.log("**********************************************************",true);
	}

	public static void elementCheckBoxIdentifier(String message) {
			Reporter.log(message,true);
	}

	public static void elementCheckBoxIdentifierFail(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			Reporter.log("____Web element = '" + elementName + "' is not identified____",true);
			Reporter.log("**********************************************************",true);
	}

	public static void elementSelect(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			Reporter.log(elementName,true);
	}

	public static void appInfo(String message) {
		Reporter.log("*************************** " + "\n" + message,true);
	}
	public static void testMessage(String message)
	{
		Reporter.log(message);
		Reporter.log("**********************************************************",true);
	}
}
