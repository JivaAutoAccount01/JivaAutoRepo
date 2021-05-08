package Base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

//import WebDriver.Drivermanager;
import WebDriver.WebDriverFactory;
import utilities.*;
import testdata.excel.*;
import mcpageobjects.McHarvestPage;
import utilities.ElementManager;

public class BaseSteps {

	protected Actions action;
	protected DataProviderReader_SelGrid dataProviderReader_SelGrid;
	protected DataProviderReader_SelGrid dp = new DataProviderReader_SelGrid();

	// commom
	protected McHarvestPage mcHarvestPage;

	
	protected ExcelUtils_SelGrid excelUtils_SelGrid;
	protected ElementManager em;
	protected WebDriver driver;

	protected String testMethodName;

	@BeforeMethod

	public void beforeMethod(Method method) throws IOException, ParseException, InterruptedException {

		setUpPageObjects();

		testMethodName = method.getName();
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {

		driver.quit();
	}

	public void setUpPageObjects() throws IOException, ParseException, InterruptedException {
		driver = WebDriverFactory.getAndroidDriver();

		mcHarvestPage = PageFactory.initElements(driver, McHarvestPage.class);

	

		excelUtils_SelGrid = new ExcelUtils_SelGrid();
		em = new ElementManager();

	}

}
