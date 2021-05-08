package adminportalpageobjects;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import utilities.ElementManager;

public class JLRegistrationPage extends ElementManager {

	@FindBy(xpath = "//span[text()='Jiva Lead Registration']")
	public WebElement jlregMenu;

	@FindBy(xpath = "//input[@name='firstName']")
	public WebElement firstName;

	@FindBy(xpath = "//input[@name='lastName']")
	public WebElement lastName;

	@FindBy(xpath = "//input[@name='phoneNumber']")
	public WebElement phoneNo;

	@FindBy(xpath = "//select[@name='geo.name']")
	public WebElement countryDD;

	@FindBy(xpath = "//option[text()='Indonesia']")
	public WebElement countryOption;

	@FindBy(xpath = "(//select[@name='geo.name'])[2]")
	public WebElement provinceDD;

	@FindBy(xpath = "//option[text()='Lampung']")
	public WebElement provinceOption;

	@FindBy(xpath = "(//select[@name='geo.name'])[3]")
	public WebElement districtDD;

	@FindBy(xpath = "//option[text()='Pesawaran']")
	public WebElement districtOption;

	@FindBy(xpath = "(//select[@name='geo.name'])[4]")
	public WebElement subdistrictDD;

	@FindBy(xpath = "//option[text()='Kedondong']")
	public WebElement subdistrictOption;

	@FindBy(xpath = "(//select[@name='geo.name'])[5]")
	public WebElement villageDD;

	@FindBy(xpath = "//option[text()='Kerta Sana']")
	public WebElement villageOption;

	@FindBy(xpath = "//select[@class='form-control ng-untouched ng-pristine ng-valid']")
	public WebElement bankDD;

	@FindBy(xpath = "//option[text()='HDFC BANK']")
	public WebElement bankName;

	@FindBy(xpath = "//input[@class='form-control ng-untouched ng-pristine ng-valid']")
	public WebElement accountNO;

	@FindBy(xpath = "//button[@class='btn btn-success text-center']")
	public WebElement createBtn;

	@FindBy(xpath = "//button[@class='btn btn-success']")
	public WebElement popupConfirm;

	@FindBy(xpath = "//button[@class='btn btn-info']")
	public WebElement popupOK;

	@FindBy(xpath = "//span[@class='caret']")
	public WebElement logoutLink;

	@FindBy(className = "dd-link")
	public WebElement logoutBtn;

	public JLRegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	Random random = new Random();
	int x = random.nextInt(30000000);
	String jlphonenumber = "81" + x;

	// Random name
	int y = random.nextInt(50);
	String JLLastName = "JLTest" + y;

	int z = random.nextInt(10000000);
	String JLAccNo = "5106" + z;

	public void jlCreate() throws Exception {

		Reporter.log("JL creation Started", true);
		sleep(5000);

		clickElement(jlregMenu, "JL Registration");
		SendKeys(firstName, "JivaJLTest", "JL First Name");
		SendKeys(lastName, JLLastName, "JL Last Name");
		SendKeys(phoneNo, jlphonenumber, "JL Phone No");
		clickElement(countryDD, "Country Drop Down");
		clickElement(countryOption, "Country Value");
		clickElement(provinceDD, "Country Drop Down");
		clickElement(provinceOption, "Province Option");
		clickElement(districtDD, "Country Drop Down");
		clickElement(districtOption, "District Option");
		clickElement(subdistrictDD, "Country Drop Down");
		clickElement(subdistrictOption, "Sub District Option");
		clickElement(villageDD, "Country Drop Down");
		clickElement(villageOption, "Village Option");
		clickElement(bankDD, "Bank DropDown");
		clickElement(bankName, "Bank Name");
		SendKeys(accountNO, JLAccNo, "Account No");
		clickElement(createBtn, "Create Button");
		clickElement(popupConfirm, "Confirm Popup");
		clickElement(popupOK, "Popup Ok");

		clickElement(logoutLink, "LogOut Link");
		clickElement(logoutBtn, "LogOut Button");

		Reporter.log("JL creation Completed", true);
	}
}
