package adminportalpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import utilities.ElementManager;

public class SetMCCommission extends ElementManager {

	@FindBy(xpath = "//select[@class='form-control ng-untouched ng-pristine ng-valid']")
	public WebElement commissionType;

	@FindBy(xpath = "//input[@type='text']")
	public WebElement commission;

	@FindBy(xpath = "//button[@class='btn btn-success']")
	public WebElement saveBtn;

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement okBtn;
	
	@FindBy(xpath = "//span[@class='caret']")
	public WebElement logoutLink;

	@FindBy(className = "dd-link")
	public WebElement logoutBtn;

	public SetMCCommission(WebDriver driver) {
		this.driver = driver;
	}

	public void setMCCommission() throws Exception {

		Reporter.log("MC Commsiison Settings Started", true);
		sleep(5000);

		selectByValue(commissionType, "2", "Inputs On Loans");

		SendKeys(commission, "2", "Commission Percentage");

		clickElement(saveBtn, "Save Button");
		clickElement(okBtn,"OK Button");
		
		clickElement(logoutLink, "LogOut Link");
		clickElement(logoutBtn, "LogOut Button");

		Reporter.log("MC Commsiison Settings Completed", true);
	}
}
