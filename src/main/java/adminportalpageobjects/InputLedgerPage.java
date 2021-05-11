package adminportalpageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import io.appium.java_client.MobileElement;
import utilities.ElementManager;

public class InputLedgerPage extends ElementManager {

	@FindBy(xpath = "//span[text()='Input Ledger']")
	public WebElement inputledgerMenu;

	@FindBy(xpath = "//select[@name='geo.name']")
	public WebElement countryDD;

	@FindBy(xpath = "(//select[@name='geo.name'])[2]")
	public WebElement provinceDD;

	@FindBy(xpath = "//button[text()='Go']")
	public WebElement goBtn;

	@FindBy(id = "paymentstart")
	public WebElement fromDate;

	@FindBy(id = "endDate")
	public WebElement toDate;

	@FindBy(xpath = "//button[text()='Search']")
	public WebElement searchBtn;

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement OKBtn;

	@FindBy(xpath = "//button[@class='btn btn-primary']")
	public WebElement viewBtn;

	@FindBy(xpath = "//span[@class='caret']")
	public WebElement logoutLink;

	@FindBy(className = "dd-link")
	public WebElement logoutBtn;

	public InputLedgerPage(WebDriver driver) {
		this.driver = driver;
	}

	public void farmerInputs() throws Exception {

		Reporter.log("Input Ledger View Started", true);
		waitUntilTextIsPresent("Price Publish");
		sleep(5000);
		clickElement(inputledgerMenu, "Input Ledger Menu");
		sleep(5000);
		selectByIndexValue(countryDD, 1, "Indonesia");
		selectByIndexValue(provinceDD, 5, "Jawa Barat");
		clickElement(goBtn, "Go Button");
		sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		sleep(5000);
		setDateValue(fromDate, "01-04-2021", "From Date");
		setDateValue(toDate, "30-04-2021", "To Date");
		clickElement(searchBtn, "Search Button");
		sleep(3000);
		if (elementAvailable(OKBtn, "OK Button") == true) {
			Reporter.log("No Records Popup displayed");
			clickElement(OKBtn, "OK Button");
		} else {
			Reporter.log("Records are Available");
		}
		clickElement(viewBtn, "View Button");

		clickElement(logoutLink, "LogOut Link");
		clickElement(logoutBtn, "LogOut Button");

		Reporter.log(" View Task Metrics Completed", true);
	}
}