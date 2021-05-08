package adminportalpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import utilities.ElementManager;

public class SetQualityDiscounting extends ElementManager {

	@FindBy(xpath = "//select[@name='selectedProduct']")
	public WebElement dropdownSelect;

	@FindBy(xpath = "//select[@class='form-control commodity-dd ng-untouched ng-pristine ng-invalid']")
	public WebElement tradeProduct;

	@FindBy(xpath = "//select[@ng-reflect-name='percentageOrAbsolute-0']")
	public WebElement waterDiscount;

	@FindBy(xpath = "//select[@ng-reflect-name='percentageOrAbsolute-1']")
	public WebElement cornDiscount;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement SaveBtn;

	@FindBy(xpath = "//span[@class='caret']")
	public WebElement logoutLink;

	@FindBy(className = "dd-link")
	public WebElement logoutBtn;

	public SetQualityDiscounting(WebDriver driver) {
		this.driver = driver;
	}

	public void qualityDisc() throws Exception {

		Reporter.log("Quality Discounting Settings Started", true);
		sleep(5000);

		selectByValue(dropdownSelect, "1", "CORN");

		selectByValue(tradeProduct, "1", "jiva-core");

		selectByValue(waterDiscount, "2", "Absolute Discounting");

		selectByValue(cornDiscount, "1", "% Discounting");

		clickElement(SaveBtn, "Go Button");

		clickElement(logoutLink, "LogOut Link");
		clickElement(logoutBtn, "LogOut Button");

		Reporter.log("Quality Discounting Settings Completed", true);
	}
}
