package adminportalpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import excelClasses.Menu;
import utilities.ElementManager;

public class SetOriginMasterPage extends ElementManager {
	
	@FindBy(xpath = "//span[text()='Jiva Lead Registration']")
	public WebElement jlregMenu;
	
	@FindBy(xpath = "//span[contains(text(),'Origin Master Settings')]")
	public WebElement OriginMasterSettings;

	@FindBy(xpath = "//span[contains(text(),'Origin Settings')]")
	public WebElement OriginSettings;

	@FindBy(name = "bankname")
	public WebElement addBankName;

	@FindBy(name = "maxval")
	public WebElement accNoLength;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement addButton;

	@FindBy(xpath = "//button[contains(.,\'Confirm\')]")
	public WebElement confirmButton;

	@FindBy(xpath = "//button[contains(.,\'OK\')]")
	public WebElement okButton;

	@FindBy(xpath = "//label[contains(.,\'General Settings\')]")
	public WebElement generalSettings;

	@FindBy(css = ".col-md-2 > .form-control")
	public WebElement emptyBagWeight;

	@FindBy(css = ".text-center > .btn")
	public WebElement saveButton;

	@FindBy(css = ".modal-footer > .btn")
	public WebElement gs_OKButton;

	@FindBy(xpath = "//span[contains(text(),'Manage Input & Quality Parameters')]")
	public WebElement manageInputs;

	@FindBy(css = "#content-wrapper > app-input-quality-parameter-settings > form > div > div.col-md-2.nopdl > select")
	public WebElement commodityDD;

	public void originSettings() {
		
		elementAvailable(jlregMenu, "HomeScreen");
		driver.get("https://qa-portal.jivafarm.com/#/originSettings");
		// clickElement(OriginMasterSettings, "OriginMasterSettings");

		clickWebElement(OriginSettings, "OriginSettings");
		SendKeys(addBankName, "CITI", "BankName");
		SendKeys(accNoLength, "10", "AccountLength");
		clickWebElement(addButton, "AddButton");
		clickWebElement(confirmButton, "ConfirmButton");
		waitUntilTextIsPresent("Data saved successfully");
		clickWebElement(okButton, "OKButton");

		// General Settings clickWebElement(generalSettings, "GenreralSettings");
		SendKeys(emptyBagWeight, "1", "Weight");
		clickWebElement(saveButton, "SaveButton");
		waitUntilTextIsPresent("Settings saved successfully");
		clickWebElement(gs_OKButton, "OKButton");
		driver.navigate().back();

		// ManageInput
		sleep(5000);
		clickWebElement(manageInputs, "ManageInputs");
		clickWebElement(commodityDD, "DD");
		selectByValue(commodityDD, "CORN", "Corn");

	}
}


