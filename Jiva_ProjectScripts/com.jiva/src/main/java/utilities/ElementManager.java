package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import adminportalpageobjects.FarmersOnCredit;
import adminportalpageobjects.InputLedgerPage;
import adminportalpageobjects.JLRegistrationPage;
import adminportalpageobjects.SetOriginMasterPage;
import adminportalpageobjects.SetMCCommission;
import adminportalpageobjects.SetQualityDiscounting;
import adminportalpageobjects.TaskMetricsPage;
import excelClasses.ExcelUtils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import jivaflowpageobjects.FarmerMCConnectionPage;
import jivaflowpageobjects.HarvestFlowPage;
import jivaflowpageobjects.InputFlowPage;
import jivaflowpageobjects.JLActivitiesPage;
import jivaflowpageobjects.LoanKYCFlowPage;
import jivaflowpageobjects.LoginPages;

public class ElementManager extends DriverManager {

	private WebDriverWait wait;
	private WebElement element;
	private Actions actionBuilder;
	protected String testMethodName;
	public WebDriver driver = null;
	public static AppiumDriverLocalService service;

	static ExcelUtils excelUtils = new ExcelUtils();
	static String excelFilePath = EnvironmentManager.getExcelPath(TESTDATAPATH_PROPERTY);

	/* Jiva Flow Page Objects */

	protected FarmerMCConnectionPage fmcconpage;
	protected HarvestFlowPage harvestflowpage;
	protected InputFlowPage inputflowpage;
	protected JLActivitiesPage jlactivitespage;
	protected LoanKYCFlowPage loankycflowpage;
	protected LoginPages loginPages;

	/* Jiva Admin Portal Page Objects */

	protected JLRegistrationPage jlcreatepage;
	protected InputLedgerPage farmerinputs;
	protected FarmersOnCredit farmeroncredit;
	protected SetMCCommission setmccommission;
	protected SetQualityDiscounting setqualitydiscount;
	protected TaskMetricsPage testmetrics;
	protected SetOriginMasterPage setoriginmaster;

	public void setUpPageObjects() {

		/* Page Factory Init */

		fmcconpage = PageFactory.initElements(driver, FarmerMCConnectionPage.class);
		harvestflowpage = PageFactory.initElements(driver, HarvestFlowPage.class);
		inputflowpage = PageFactory.initElements(driver, InputFlowPage.class);
		jlactivitespage = PageFactory.initElements(driver, JLActivitiesPage.class);
		loankycflowpage = PageFactory.initElements(driver, LoanKYCFlowPage.class);
		loginPages = PageFactory.initElements(driver, LoginPages.class);

		jlcreatepage = PageFactory.initElements(driver, JLRegistrationPage.class);
		farmerinputs = PageFactory.initElements(driver, InputLedgerPage.class);
		farmeroncredit = PageFactory.initElements(driver, FarmersOnCredit.class);
		setmccommission = PageFactory.initElements(driver, SetMCCommission.class);
		setqualitydiscount = PageFactory.initElements(driver, SetQualityDiscounting.class);
		testmetrics = PageFactory.initElements(driver, TaskMetricsPage.class);

	}

	public void getExcelData(String sheetName, String phNo) throws IOException {
		excelUtils.setExcelFile(excelFilePath, sheetName);
		for (int i = 0; i <= excelUtils.getRowCountInSheet(); i++) {
			phNo = excelUtils.getCellData(i, 0);

		}
	}

	public static void startEmulator() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\resources\\startEmulator.bat");
		Thread.sleep(10000);
	}

	public static void startTabEmulator() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\resources\\startTabletEmulator.bat");
		Thread.sleep(10000);
	}

	public static void stopEmulator() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\resources\\stopEmulator.bat");

	}

	public static void stopTabEmulator() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\resources\\stopTabletEmulator.bat");
		Thread.sleep(10000);
	}

	public AppiumDriverLocalService startServer() {

		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {

			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;

	}

	public AppiumDriverLocalService stopServer() {

		service = AppiumDriverLocalService.buildDefaultService();
		service.stop();
		return service;

	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public void killAllSession() throws IOException {

		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	}

	@BeforeMethod
	public void methodName(Method method) {
		setUpPageObjects();
		testMethodName = method.getName();
	}

	@BeforeSuite
	public void setEnvironmet() throws InterruptedException, IOException {

		killAllSession();
		sleep(5000);
		startEmulator();
		sleep(10000);
		service = startServer();

	}

	@AfterSuite(alwaysRun = true)
	public void clearEnvironment() throws IOException, InterruptedException {
		stopEmulator();
		service.stop();

	}

	public void clickElement(WebElement elementtype, String elementName) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(elementtype));

			if (elementtype.isDisplayed() && elementtype.isEnabled()) {
				final long startTime = System.nanoTime();
				elementtype.click();

				final long duration = System.nanoTime() - startTime;
				long seconds = TimeUnit.NANOSECONDS.toSeconds(duration);
				Reporter.log("Clicked on " + elementName + " loaded in " + (seconds) + " seconds", true);

			}

		} catch (Exception e) {
			Reporter.log("Timed out waiting for element := " + elementName, true);
			TestLogger.elementIdentifierFail("" + elementName);
			Assert.fail("TIMEOUT EXCEPTION element does not exist " + elementName);
		}
	}

	public void clickWebElement(WebElement elementtype, String elementName) {

		try {

			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: none; border: 2px solid red;');",
					elementtype);

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(elementtype));

			if (elementtype.isDisplayed() && elementtype.isEnabled()) {
				final long startTime = System.nanoTime();
				elementtype.click();

				final long duration = System.nanoTime() - startTime;
				long seconds = TimeUnit.NANOSECONDS.toSeconds(duration);
				Reporter.log("Clicked on " + elementName + " loaded in " + (seconds) + " seconds", true);

			}
		} catch (Exception e) {
			Reporter.log("Timed out waiting for element := " + elementName, true);
			TestLogger.elementIdentifierFail("" + elementName);
			Assert.fail("TIMEOUT EXCEPTION element does not exist " + elementName);
		}
	}

	public void setDateValue(WebElement elementtype, String date, String elementName) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(elementtype));
			// Enables the date box
			// ((JavascriptExecutor)driver).executeScript
			// ("document.getElementById('fromDate').removeAttribute('readonly',0);");
			((JavascriptExecutor) driver)
					.executeScript("document.'" + elementtype + "'.removeAttribute('readonly',0);");
			// ((JavascriptExecutor)driver).executeScript("document.getElementById('fromDate').setAttribute('value','"+date+"')");
			elementtype.clear();
			elementtype.sendKeys(date);

		} catch (Exception e) {
			Reporter.log("Timed out waiting for element := " + elementName, true);
			TestLogger.elementIdentifierFail("" + elementName);
			Assert.fail("TIMEOUT EXCEPTION element does not exist " + elementName);
		}
	}

	// ******************* Select By Visible Text ******************* //

	public void selectByVisibleText(WebElement element, String valueToSelect, String elementName) {

		try {
			Select select = new Select(element);
			select.selectByVisibleText(valueToSelect);
			Reporter.log(valueToSelect + " Value selected from dropdown " + elementName, true);
		} catch (NoSuchElementException e) {
			Reporter.log("Value not found in dropdown", true);

		}
		// Verifying if selected value is displaying or not
		Assert.assertEquals(getSelectedOption(element), valueToSelect, "Selected Value not displaying");
	}

	// ******************* Select By Index ******************* //

	public void selectByIndexValue(WebElement element, int indexVal, String elementName) {

		try {
			Select select = new Select(element);
			select.selectByIndex(indexVal);
			Reporter.log(getSelectedOption(element) + " Value selected from dropdown " + elementName, true);
		} catch (NoSuchElementException e) {
			Reporter.log("Value not found in dropdown", true);

		}
	}

	// ******************* Select By Value ******************* //

	public void selectByValue(WebElement element, String valueToSelect, String elementName) {

		try {
			sleep(3000);
			Select select = new Select(element);
			select.selectByValue(valueToSelect);
			Reporter.log(valueToSelect + " Value selected from dropdown " + elementName, true);
		} catch (NoSuchElementException e) {
			System.out.println("Value not find in dropdown");

		}
		// Verifying if selected value is displaying or not
		Assert.assertEquals(getSelectedOption(element), valueToSelect, "Selected Value not displaying");
	}

	public void SendKeys(WebElement elementType, String value, String elementName) {
		try {
			wait = new WebDriverWait(driver, 60);
			if (wait.until(ExpectedConditions.elementToBeClickable(elementType)) != null) {
				element = elementType;
				if (element.isDisplayed() && element.isEnabled()) {
					element.clear();
					element.sendKeys(value);
					// TestLogger.elementIdentifier("" + elementName);
					Reporter.log("***Entered " + value + " in TextField ***", true);
				}
			}
		} catch (Exception e) {
			Reporter.log("Timed out waiting for element := " + elementName, true);
			TestLogger.elementIdentifierFail("" + elementName);
			Assert.fail("TIMEOUT EXCEPTION element does not exist " + elementName);
		}
	}

	public String getText(WebElement elementtype, String elementName) {
		String returnText = null;
		try {

			wait = new WebDriverWait(driver, 20);
			if (wait.until(ExpectedConditions.elementToBeClickable(elementtype)) != null && elementtype.isDisplayed()) {
				returnText = elementtype.getText();
				Reporter.log(returnText, true);

			}
		} catch (NoSuchElementException e) {
			Reporter.log(elementName + " : element not found", true);

		}
		return returnText;
	}

	public void elementClick(WebDriver driver, By byType, long explicittime) {
		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				element = driver.findElement(byType);
				if (element.isDisplayed() && element.isEnabled()) {
					TestLogger.elementClickIdentifier(element.getText());
					element.click();
					Reporter.log(" Clicked " + element + " in " + byType, true);
				}
			}
		} catch (Exception e) {
			Reporter.log("TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - "
					+ element.getText(), true);
			TestLogger.elementIdentifierFail(element.getText());
			Assert.fail("TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - "
					+ element.getText());
		}

	}

	public void clickHiddenElement(WebDriver driver, By element, String elementName, long explicittime)
			throws InterruptedException {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", element);
			Reporter.log("Clicked on " + elementName, true);
			checkPageIsReady();
		} catch (Exception e) {
			Reporter.log("Timed out waiting for element := " + elementName, true);
			Assert.assertFalse(true);
		}
	}

	public void elementSendKeys(WebDriver driver, By byType, String value, long explicittime) {
		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				element = driver.findElement(byType);
				if (element.isDisplayed() && element.isEnabled()) {
					element.clear();
					element.sendKeys(value);
					TestLogger.elementIdentifier("" + byType);
					Reporter.log("***Entered " + value + " in " + byType + "***", true);
				}
			}
		} catch (Exception e) {
			Reporter.log(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType,
					true);
			TestLogger.elementIdentifierFail("" + byType);
			Assert.fail(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType);
		}
	}

	public String elementGetText(WebDriver driver, By byType, long explicittime) {

		String returnText = null;
		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				element = driver.findElement(byType);
				if (element.isDisplayed() && element.isEnabled()) {
					TestLogger.elementIdentifier(element.getText());
					returnText = element.getText();
				}
			}
		} catch (Exception e) {
			Reporter.log(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType,
					true);
			TestLogger.elementIdentifierFail(element.getText());
			Assert.fail(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType);
		}
		return returnText;
	}

	public String elementGetValue(WebDriver driver, By byType, long explicittime) {

		String returnText = null;
		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.visibilityOfElementLocated(byType)) != null) {
				element = driver.findElement(byType);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + element.getLocation().y + ")");
				returnText = element.getAttribute("value");
			}
		} catch (Exception e) {
			Reporter.log(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType,
					true);
			TestLogger.elementIdentifierFail("" + byType);
			Assert.fail(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType);
		}
		return returnText;
	}

	public void elementSelect(WebDriver driver, By byType, String optionValue, long explicittime) {

		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.visibilityOfElementLocated(byType)) != null) {
				element = driver.findElement(byType);
				new Select(element).selectByVisibleText(optionValue);
			} else {

			}
		} catch (Exception e) {
			Reporter.log("TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - "
					+ optionValue, true);

			Assert.fail(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType);
		}
	}

	public boolean elementAvailable(WebElement elementType, String elementName) {
		Reporter.log("Searching for element :=" + elementName, true);
		boolean elementIdentifier = false;
		try {

			wait = new WebDriverWait(driver, 30);
			if (wait.until(ExpectedConditions.elementToBeClickable(elementType)) != null) {
				WebElement element = elementType;

				if (element.isDisplayed()) {
					elementIdentifier = true;
					if (elementName != null && !elementName.isEmpty())
						Reporter.log("Web element = '" + elementName + "' is identified successfully", true);

				}

			} else {
				elementIdentifier = false;

				if (elementName != null && !elementName.isEmpty())
					Reporter.log("Web element = '" + elementName + "' is not identified", true);
			}
		} catch (Exception e) {
			Reporter.log("Timed out waiting for element := " + elementName, true);
			if (elementName != null && !elementName.isEmpty())
				Reporter.log("Web element = '" + elementName + "' is not identified", true);
		}
		return elementIdentifier;

	}

	public String getSelectedOption(WebElement element) {
		Select select = new Select(element);
		WebElement selectedElement = select.getFirstSelectedOption();
		String selectedOption = selectedElement.getText();
		return selectedOption;
	}

	public void scrollPage() {
		sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}

	public void scrollPage(String visibleText) {

		Reporter.log("Scrolling page", true);
		WebElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""
						+ visibleText + "\").instance(0))"));
		if (element.isDisplayed()) {
			Reporter.log(visibleText + " is Displayed", true);
		} else {
			Reporter.log(visibleText + " is not Displayed", true);
		}
	}

	public boolean elementAvailability(WebDriver driver, By byType, String value, long explicittime) {

		boolean elementIdentifier = false;
		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				element = driver.findElement(byType);
				if (element.isDisplayed()) {
					elementIdentifier = true;
					TestLogger.elementIdentifier(value);
				} else {
					elementIdentifier = false;
					TestLogger.elementIdentifierFail("" + value);
				}
			}
		} catch (Exception e) {
			Reporter.log(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + value,
					true);
			TestLogger.elementIdentifierFail("" + value);

		}
		return elementIdentifier;
	}

	public void scrollAndClick(String visibleText) {
		Reporter.log("Scrolling page", true);
		WebElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""
						+ visibleText + "\").instance(0))"));
		element.click();
		Reporter.log("Clicked on " + element.getText(), true);

	}

	public void scrollAndTextView(String visibleText) {

		WebElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + visibleText + "\"));"));

		String text = element.getText();
		System.out.println(text);
		Reporter.log("Scrolling page", true);

	}

	public void scrollClickByContains(String visibleText) {
		Reporter.log("Scrolling page", true);
		WebElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new UiSelector().textContains(\""
						+ visibleText + "\").instance(0))"));
		Reporter.log("Clicked on " + element.getText(), true);
		element.click();

	}

	public void scrollAndTextMatchView(String visibleText) {
		try {
			WebElement element = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""
							+ visibleText + "\").instance(0))"));

			Reporter.log("Scrolling page", true);
		} catch (Exception e) {

			Reporter.log("TIMEOUT EXCEPTION element does not exist " + visibleText, true);
			TestLogger.elementIdentifierFail("" + visibleText);

		}
	}

	public void scrollPageByText(String visibleText) {

		Reporter.log("Scrolling page", true);
		WebElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""
						+ visibleText + "\").instance(0))"));
		if (element.isDisplayed()) {
			Reporter.log(visibleText + "is Displayed", true);
		} else {
			Reporter.log(visibleText + "is not Displayed", true);
		}
	}

	public void scrollPage(WebElement eleName) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eleName);
	}

	public void scrollToView(By eleName) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eleName);
	}

	public boolean scrollandCheck(String name) {

		boolean addedMC = false;
		WebElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ name + "\").instance(0))"));

		if (element.isDisplayed()) {
			addedMC = true;
			Reporter.log(name + " MC is available", true);

		} else {
			addedMC = false;
		}
		return addedMC;
	}

	public void scrollAndView(String visibleText) {

		WebElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ visibleText + "\").instance(0))"));
		Reporter.log("Scrolling page", true);

	}

	public void elementMouseHover(WebDriver driver, By byType, Boolean scroll, long explicittime) {

		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.elementToBeClickable(byType)) != null) {
				if (null != driver.findElements(byType)) {
					element = driver.findElement(byType);
					if (element.isDisplayed() && element.isEnabled()) {
						if (scroll) {
							((JavascriptExecutor) driver)
									.executeScript("window.scrollTo(0," + element.getLocation().y + ")");
						}
						actionBuilder = new Actions(driver);
						actionBuilder.moveToElement(element).build();
						actionBuilder.perform();
						TestLogger.elementSelect("Successfully Mouse hover on element");
					}
				}
			}
		} catch (Exception e) {
			Reporter.log(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType,
					true);
			TestLogger.elementIdentifierFail("" + byType);
			Assert.fail(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType);
		}
	}

	public boolean addedMC(String MCname) throws InterruptedException {

		boolean addedMC = false;

		List<WebElement> addedMcList = driver.findElements(By.id("txtMcName"));
		for (int i = 0; i < addedMcList.size(); i++) {
			String list = addedMcList.get(i).getText();
			if (list.contains(MCname)) {
				addedMC = true;
				Reporter.log(MCname + " MC is available", true);
				break;

			} else {
				addedMC = false;

			}

		}
		return addedMC;
	}

	public void clickFirstElement() throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> options = driver.findElements(By.id("com.gaiaventure.jl:id/txtMcName"));
		int listSize = options.size();
		if (listSize <= 0) {
			Reporter.log("List is Empty . Unable to proceed", true);
			driver.quit();

		} else {
			options.get(0).click();

		}

	}

	public boolean nameSearch(String fName) throws InterruptedException {

		boolean a = false;

		List<WebElement> tabledata = driver
				.findElements(By.xpath("//table[@class='mat-table cdk-table table ng-star-inserted']/tbody/tr"));
		int total = tabledata.size();
		try {
			Reporter.log("Searching for " + fName, true);
			for (int i = 1; i < total; i++) {
				WebElement name = driver.findElement(By.xpath(
						"//table[@class='mat-table cdk-table table ng-star-inserted']/tbody/tr[" + i + "]/td[2]"));
				String farmerName = name.getText();
				WebElement status = driver.findElement(By.xpath(
						"//table[@class='mat-table cdk-table table ng-star-inserted']/tbody/tr[" + i + "]/td[8]"));
				if (name.getText().contains(fName) && status.getText().contains("Pending")) {
					a = true;
					Reporter.log("***Selected " + name.getText() + " with Status " + status.getText() + "***", true);
					name.click();
					break;
				} else {
					a = false;
				}
			}
		} catch (Exception e) {
			Reporter.log("TIMEOUT EXCEPTION element does not exist", true);
			TestLogger.elementIdentifierFail("");
		}
		return a;
	}

	public void checkPageIsReady() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Initially below if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			Thread.sleep(500);
			System.out.println("Page loaded.");
			return;
		}
		// This loop will iterate for 120 times to check If page Is ready after
		// every 1second.
		for (int i = 0; i < 120; i++) {
			try {
				Thread.sleep(5000);
				System.out.println("Waiting for page to load");
			} catch (InterruptedException e) {
			}
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public void sleep(long millisecs) {
		try {
			Thread.sleep(millisecs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void fnextTAB() {
	 * 
	 * try { for (int i = 1; i < 6; i++) { ((AndroidDriver) driver).pressKey(new
	 * KeyEvent(AndroidKey.TAB));
	 * 
	 * if (i == 5) { ((AndroidDriver) driver).pressKey(new
	 * KeyEvent(AndroidKey.ENTER)); break; }
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * Reporter.log("TIMEOUT EXCEPTION Tab sequence is not done", true);
	 * TestLogger.elementIdentifierFail("");
	 * 
	 * } }
	 */
	public void fiagreeTAB() {

		try {
			Reporter.log("TAB key sequence is started to click 'I Agree'", true);
			for (int i = 1; i < 7; i++) {
				System.out.println("I Agree Tab");
				((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));

				if (i == 6) {
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
					Reporter.log("TAB key sequence is ended and 'I Agree'- button is pressed", true);
					break;
				}
			}
			Thread.sleep(10000);
			Reporter.log("TAB key sequence is started to click 'I Accept'", true);
			for (int j = 1; j < 5; j++) {
				System.out.println("I Accept Tab");
				((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
				Reporter.log("TAB key sequence is ended and 'I Accept'- button is pressed", true);
				if (j == 4) {
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
					Reporter.log("TAB key sequence is ended and 'I Accept'- button is pressed", true);
					break;
				}
			}

		} catch (Exception e) {
			Reporter.log("TIMEOUT EXCEPTION Tab sequence is not done", true);
			TestLogger.elementIdentifierFail("");

		}
	}

	public void farmerLogIn(By LOGIN_ACCOUNT, By EMAIL_ID, String MailId, By NXT_BTN, By SKIP_BTN, By PASSWORD,
			String Pwd) {

		try {

			if (elementAvailability(driver, LOGIN_ACCOUNT, "Account", 60) == true) {

				System.out.println("Logged In Account available");
				Reporter.log("Logged In Account available", true);
				elementClick(driver, LOGIN_ACCOUNT, 5);

			}
			// else {
			// System.out.println("Logged In Account Unavailable");
			// Reporter.log("Logged In Account Unavailable" , true);
			// }

			else if (elementAvailability(driver, EMAIL_ID, "EMail", 20) == true) {
				elementSendKeys(driver, EMAIL_ID, MailId, 5);

				if (elementAvailability(driver, NXT_BTN, "Next", 2) == true) {
					elementClick(driver, NXT_BTN, 2);
				} else {
					System.out.println("Next Button Unavailable");
					Reporter.log("Next Button Unavailable", true);
					Reporter.log("TAB sequence is started", true);
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
					Reporter.log("TAB sequence is ended and Next key is pressed ", true);
				}

				if (elementAvailability(driver, SKIP_BTN, "Skip", 10) == true) {

					System.out.println("New login, Skip option available");
					Reporter.log("New login, Skip option available", true);
					elementClick(driver, SKIP_BTN, 2);
				} else {
					System.out.println("Not a New User, Skip button unavailable");
					Reporter.log("Not a New User, Skip button unavailable", true);
				}

				if (elementAvailability(driver, PASSWORD, "Password", 10) == true) {
					elementSendKeys(driver, PASSWORD, Pwd, 3);
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
					Reporter.log("Enter Key pressed", true);
					Thread.sleep(15000);
					fiagreeTAB();

				}

			} else {

				System.out.println("Unable to login");

			}
		} catch (Exception e) {
			Reporter.log("TIMEOUT EXCEPTION Log In is not done", true);
			TestLogger.elementIdentifierFail("");

		}
	}

	public void dropdownSelect(String dropDownValue) throws InterruptedException {

		List<WebElement> forExtensionDropdown = driver
				.findElements(By.xpath("//div[@id='mat-select-0-panel']//mat-option"));
		Reporter.log("Total Number of Dropdown values are ==" + forExtensionDropdown.size(), true);
		for (int i = 0; i < forExtensionDropdown.size(); i++) {

			if (forExtensionDropdown.get(i).getText().equalsIgnoreCase(dropDownValue)) {
				forExtensionDropdown.get(i).click();
				Reporter.log("***Selected == " + dropDownValue + " from Dropdown***", true);
				break;
			}

		}

	}

	public boolean waitUntilTextIsPresent(String validationText) {
		int searchFlag = 0;
		int attemptCount = 1;
		int maxAttempts = 11;
		int sleepInterval = 1000;
		Reporter.log("Searching for := " + validationText, true);
		if (driver.getTitle().toLowerCase().contains(validationText.toLowerCase())) {
			Reporter.log("Page Title for " + validationText + " is true");
			searchFlag = 1;
		} else {

			foundIt: while (attemptCount <= maxAttempts) {
				if (driver.getPageSource().toLowerCase().contains(validationText.toLowerCase())) {
					Reporter.log("Page Source for " + validationText + " is true", true);
					searchFlag = 1;
					break foundIt;
				} else {
					try {
						Thread.sleep(sleepInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				attemptCount++;
			}
		}
		if (searchFlag == 0) {
			Reporter.log("Page Source for " + validationText + " is not found", true);
		}
		return (attemptCount <= maxAttempts) ? true : false;
	}

	public void selectfromFolder(WebDriver driver) throws InterruptedException {
		List<WebElement> photoslist = driver
				.findElements(By.id("com.google.android.apps.photos:id/fragment_container"));
		for (int i = 0; i < photoslist.size(); i++) {
			String photoname = photoslist.get(i).getText();
			if (photoname == null) {
				System.out.println("Folder/Photo is not available to select");
				Reporter.log("Folder/Photo is not available to select" + photoname);
				break;
			} else {
				photoslist.get(i).click();
				Reporter.log("Selected Photo/Folder is " + photoname);
				break;
			}
		}
	}

	public void clickFirstImage(WebDriver driver, By FIRST_IMAGE) throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> options = driver.findElements(By.className("android.view.ViewGroup"));
		int listSize = options.size();
		if (listSize <= 0) {
			Reporter.log("List is Empty . Unable to proceed", true);
			driver.quit();

		} else {
			options.get(0).click();

		}

	}

	public void getFarmer_AppVersion(WebElement profiletab, WebElement helptab, WebElement appVersion) {

		clickElement(profiletab, "PROFILE_TAB");
		clickElement(helptab, "HELPANDSUPPORT");

		Reporter.log("**********************************************************", true);
		Reporter.log("FARMER APPLICATION VERSION IS " + appVersion.getText(), true);
		Reporter.log("**********************************************************", true);
		driver.navigate().back();
		// clickElement(backbtn, "BACK_BTN");

	}

	public void getMCAppVersion() {
		Reporter.log("**********************************************************", true);
		Reporter.log("APPLICATION VERSION IS " + EnvironmentManager.getEnvironment("MCURL"), true);
		Reporter.log("**********************************************************", true);
	}

	public void takeTestStepScreenshot(String screenShotName) {
		File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String out = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
		try {
			File file = new File(PropertiesUtil.getValue(PropertiesUtil.SCREENSHOT_PATH_PROPERTY) + "/" + out + "/"
					+ screenShotName + out + ".png");
			FileUtils.copyFile(screenShotFile, file);
			Reporter.log(file.getAbsolutePath(), true);

		} catch (Throwable e) {
			System.out.println("Failed to capture screenshot");
		}
	}

	public void takeScreenshot(String screenShotName) {
		File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String out = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
		try {
			File file = new File(PropertiesUtil.getValue(PropertiesUtil.SCREENSHOT_PATH_PROPERTY) + "/" + out + "/"
					+ screenShotName + out + ".png");
			FileUtils.copyFile(screenShotFile, file);
			Reporter.log(file.getAbsolutePath());

		} catch (Throwable e) {
			System.out.println("Failed to capture screenshot");
		}
		Assert.assertTrue(false, "this will make the test method fail");
	}

	public void logUserStoryStart(String userStoryDescription, String logMessage) {
		Reporter.log("                                                             ", true);
		Reporter.log("                         " + userStoryDescription + "                                     ",
				true);
		Reporter.log("****************   " + logMessage + "   ****************", true);
	}

	public String logUserStoryEndSuccess(String userStoryDescription, String successMsgDesc) {
		Reporter.log(userStoryDescription + " Successfully executed", true);
		if (!(successMsgDesc == "")) {
			successMsgDesc = successMsgDesc + userStoryDescription + "|";
		} else {
			successMsgDesc = userStoryDescription + "|";
		}
		System.out.println(successMsgDesc);
		return successMsgDesc;
	}

	public String logUserStoryEndFail(String userStoryDescription, String failMsgDesc) {
		Reporter.log("Failed := " + userStoryDescription, true);
		takeTestStepScreenshot(userStoryDescription.replace(" ", "").substring(0, 12) + "_Error");
		if (!(failMsgDesc == "")) {
			failMsgDesc = failMsgDesc + userStoryDescription + "|";
		} else {
			failMsgDesc = userStoryDescription + "|";
		}
		System.out.println(failMsgDesc);

		return failMsgDesc;

	}

	public void displayExecutionResults(String successMsgDesc, String failMsgDesc) {
		Reporter.log("                                                             ", true);
		Reporter.log("^^^^^^^^^^^^^^^^^^^^^^    Execution Results    ^^^^^^^^^^^^^^^^^^^^^^^^^^", true);
		int failCount = failMsgDesc.length() - failMsgDesc.replace("|", "").length();
		int passCount = successMsgDesc.length() - successMsgDesc.replace("|", "").length();
		String totalCount = String.valueOf(passCount + failCount);
		Reporter.log("Total Test Count :=" + totalCount, true);
		Reporter.log("Passed Test Count :=" + String.valueOf(passCount), true);
		Reporter.log("Passed Test Description :=" + successMsgDesc, true);
		Reporter.log("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", true);
		if (failCount > 0) {
			Reporter.log("Failed Test Count :=" + String.valueOf(failCount), true);
			Reporter.log("Failed Test Description :=" + failMsgDesc, true);
			Reporter.log("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", true);
			Assert.assertFalse(true);
		} else if (passCount == 0 && failCount == 0) {
			Reporter.log(" ------- Overall Test Failed ------", true);
			Reporter.log(" ---- No test cases executed ----", true);
			Assert.assertFalse(true);
		}
	}

	public void mc_SwitchActivity() {

		try {

			Reporter.log("^^^^^^^^^^^^^^^^^^^^^^    Switching to MC App    ^^^^^^^^^^^^^^^^^^^^^^^^^^", true);
			sleep(3000);
			Activity activity = new Activity("com.gaiaventure.mc", "com.gaiaventure.mc.ui.activity.SplashActivity");
			((StartsActivity) driver).startActivity(activity);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void farmer_SwitchActivity() {

		try {

			Reporter.log("^^^^^^^^^^^^^^^^^^^^^^    Switching to Farmer App    ^^^^^^^^^^^^^^^^^^^^^^^^^^", true);
			sleep(3000);
			Activity activity = new Activity("com.gaiaventure.advisor.qa",
					"com.gaiaventure.advisor.activity.MainActivity");
			((StartsActivity) driver).startActivity(activity);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
