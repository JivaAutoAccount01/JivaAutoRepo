package utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * This class represents DriveManger things and browser and mobile information
 */
public class DriverManager {

	protected WebDriver driver = null;
	protected AndroidDriver<AndroidElement> adriver = null;
	private String url = null;
	private static final int DELAYTIME = Integer.parseInt(EnvironmentManager.getDelayTime());
	private String DeviceName = null;

	public static final String TESTDATAPATH_PROPERTY = "testdatapath";

	public WebDriver getDriver(DriverType browser) throws IOException, InterruptedException, MalformedURLException {

		switch (browser) {

		case JLTABEMULATOR:

			url = EnvironmentManager.getEnvironment("JLURL");
			DesiredCapabilities jlcaps = new DesiredCapabilities();
			File filePath = new File(System.getProperty("user.dir"));
			File appDir = new File(filePath, "/app");
			File jlapp = new File(appDir, url);

			jlcaps.setCapability("WDA_LOCAL_PORT", "Android");
			jlcaps.setCapability("VERSION", "11.0");
			jlcaps.setCapability("deviceName", "JLDEVICENAME");
			jlcaps.setCapability("platformName", "Android");
			jlcaps.setCapability("app", jlapp.getAbsolutePath());
			jlcaps.setCapability("AUTOMATION_NAME", "uiautomator2");
			jlcaps.setCapability("appPackage", "com.gaiaventure.jl");

			// mcEmuCaps.setCapability("appActivity",
			// "com.gaiaventure.mc.ui.activity.SplashActivity");
			// mcEmuCaps.setCapability("noReset", "true");

			try {
				driver = new AndroidDriver<AndroidElement>((new URL("http://127.0.0.1:4723/wd/hub")), jlcaps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			Reporter.log("Android application launched successfully", true);
			break;

		case MAGENTO:
			url = EnvironmentManager.getEnvironment("MagentoURL");
			Reporter.log("Magento flow is started", true);
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
			TestLogger.appInfo("Majento launched successfully" + "\n" + "URL = " + url);
			break;

		case JIVAWEBPORTAL:
			url = EnvironmentManager.getEnvironment("JivaWebPortal");
			Reporter.log("Jiva Webportal flow is started", true);
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
			TestLogger.appInfo("jiva Webportal launched successfully" + "\n" + "URL = " + url);
			break;

		case FARMERCRM:
			url = EnvironmentManager.getEnvironment("FarmerCRMURL");
			Reporter.log("FarmerCRM Loan Approval flow is started", true);
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
			TestLogger.appInfo("Farmer CRM launched successfully" + "\n" + "URL = " + url);
			break;

		case MCEMULATOR:
			url = EnvironmentManager.getEnvironment("MCURL");
			DeviceName = EnvironmentManager.getDeviceName("DeviceName");

			DesiredCapabilities mcEmuCaps = new DesiredCapabilities();

			File mcFilePath = new File(System.getProperty("user.dir"));
			File mcappDir = new File(mcFilePath, "/app");
			File mcapp = new File(mcappDir, url);

			mcEmuCaps.setCapability("VERSION", "11.0");
			mcEmuCaps.setCapability("deviceName", DeviceName);
			mcEmuCaps.setCapability("platformName", "Android");
			mcEmuCaps.setCapability("app", mcapp.getAbsolutePath());
			mcEmuCaps.setCapability("AUTOMATION_NAME", "uiautomator2");
			mcEmuCaps.setCapability("appPackage", "com.gaiaventure.mc");
			mcEmuCaps.setCapability("appActivity", "com.gaiaventure.mc.ui.activity.SplashActivity");
			mcEmuCaps.setCapability("noReset", "true");

			try {
				driver = new AndroidDriver<AndroidElement>((new URL("http://127.0.0.1:4723/wd/hub")), mcEmuCaps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			TestLogger.appInfo("MC Emulator Android application launched successfully");
			break;

		case FARMEREMULATOR:
			url = EnvironmentManager.getEnvironment("FURL");
			DeviceName = EnvironmentManager.getDeviceName("DeviceName");

			DesiredCapabilities farmerEmuCaps = new DesiredCapabilities();

			File farmerFilePath = new File(System.getProperty("user.dir"));
			File farmerappDir = new File(farmerFilePath, "/app");
			File farmerapp = new File(farmerappDir, url);

			farmerEmuCaps.setCapability("VERSION", "11.0");
			farmerEmuCaps.setCapability("deviceName", "DEVICENAME");
			farmerEmuCaps.setCapability("platformName", "Android");
			farmerEmuCaps.setCapability("app", farmerapp.getAbsolutePath());
			farmerEmuCaps.setCapability("AUTOMATION_NAME", "uiautomator2");
			farmerEmuCaps.setCapability("noReset", "true");

			try {
				driver = new AndroidDriver<AndroidElement>((new URL("http://127.0.0.1:4723/wd/hub")), farmerEmuCaps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			TestLogger.appInfo("Farmer Emulator Android application launched successfully");
			break;

		case FARMERBROWSERSTACK:
			DesiredCapabilities bsCaps = new DesiredCapabilities();
			bsCaps.setCapability("browserstack.user", "camillusranjith1");
			bsCaps.setCapability("browserstack.key", "xHmtzrg9yXu9HJV2qZNJ");
			bsCaps.setCapability("app", "bs://d1799dfd1d9357ca308549d192580d7bb8423bc4");
			bsCaps.setCapability("device", "Google Pixel 3");
			bsCaps.setCapability("os_version", "9.0");
			bsCaps.setCapability("project", "jiva");
			bsCaps.setCapability("build", " QA Android");
			bsCaps.setCapability("name", "Farmer app");

			try {
				driver = new AndroidDriver<AndroidElement>((new URL("http://hub.browserstack.com/wd/hub")), bsCaps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			TestLogger.appInfo("Browserstack launched successfully");
			break;

		case MCBROWSERSTACK:
			DesiredCapabilities mcCaps = new DesiredCapabilities();
			mcCaps.setCapability("browserstack.user", "camillusranjith1");
			mcCaps.setCapability("browserstack.key", "xHmtzrg9yXu9HJV2qZNJ");
			mcCaps.setCapability("app", "bs://41645c485730189f1b29430cfb935fed05c3f51c");
			mcCaps.setCapability("device", "Google Pixel 3");
			mcCaps.setCapability("os_version", "9.0");
			mcCaps.setCapability("project", "jiva");
			mcCaps.setCapability("build", " QA Android");
			mcCaps.setCapability("name", "MC app");

			try {
				driver = new AndroidDriver<AndroidElement>((new URL("http://hub.browserstack.com/wd/hub")), mcCaps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			TestLogger.appInfo("Browserstack launched successfully");
			break;

		default:
			break;
		}

		return driver;
	}

	public void swithApp(String packageName, String activityName) {

		Activity activity = new Activity(packageName, activityName);
		adriver.startActivity(activity);
	}

	/**
	 * This will highlighted the fields in browser and delete the browser cache
	 */
	public static WebDriver getDriverInstance(WebDriver driver) {
		return driver;
	}
}
