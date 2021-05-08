package jivaflowpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import excelClasses.Menu;
import io.appium.java_client.MobileBy;
import utilities.ElementManager;

public class FarmerMCConnectionPage extends ElementManager {

	/* 1. Farmer Creates Connection Request to MC */
	
	public FarmerMCConnectionPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_find_village")
	public WebElement flocation;

	@FindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	public WebElement allowbtn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_next")
	public WebElement nextbtn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/fragment_buy")
	public WebElement buymenubtn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_find_collector")
	public WebElement findmc;

//	@FindBy(id="com.gaiaventure.advisor.qa:id/tv_country_code")
//	public WebElement countryCodeDropdown;

//	@FindBy(xpath = "//android.widget.TextView[@text='+91 (India)']")
//	public WebElement indiaCode;
	@FindBy(id = "com.gaiaventure.advisor.qa:id/enter_phone_number")
	public WebElement selectmcph;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/et_phone_number_input")
	public WebElement entermcph;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_next")
	public WebElement mcphnextbtn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_ok")
	public WebElement okbtn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/et_phone_number_input")
	public WebElement farmerphno;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_next")
	public WebElement farmerphnxtbtn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_next")
	public WebElement mcconnectbtn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/fragment_profile")
	public WebElement account;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/link_logout")
	public WebElement logout;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/tv_display_name")
	public WebElement mcName;

	public void newFarmerCon() throws Exception {

		Reporter.log("New Farmer Registration is started", true);

		// elementSendKeys(driver,F_NAME,"JivaAuto Test13", 65);
		// sleep(3000);
		clickElement(flocation, "findlocation");

		clickElement(allowbtn, "allow access");

		// elementSendKeys(driver,FARMER_LOCATION,"Lumpangan, Pajukukang, Bantaeng,
		// Sulawesi selatan, Indonesia",60);
		// sleep(3000);
		clickElement(nextbtn, "next");

		clickElement(buymenubtn, "Buy screen");

		// elementClick(driver,PAY_LATER,10);
		// sleep(5000);
		clickElement(findmc, "KnowSahabat");

		// clickElement(countryCodeDropdown, "CountryCode");
		// clickElement(indiaCode, "IndiaCode");
		// SendKeys(entermcph, "9551825425", "McPhoneNumber");

		clickElement(selectmcph, "MC Phone");

		SendKeys(entermcph, "8114123245", "McPhoneNumber");

		clickElement(mcphnextbtn, "MC Phone Next BTN");
		waitUntilTextIsPresent("Connecting to");

		Reporter.log("Request sent to " + mcName.getText(), true);

		clickElement(okbtn, "OK BTN");

		SendKeys(farmerphno, "9114123105", "Farmer Phone Number");

		clickElement(farmerphnxtbtn, "Farmer Phone Next");

		clickElement(mcconnectbtn, "MC Connect BTN");
		// clickElement(account,"Account");
		// sleep(3000);
		// clickElement(logout,"Log Out");

		Reporter.log("New Farmer Registration is Completed", true);

	}
	
	/* 2. MC Accepts Farmer's Connection Request */

	@FindBy(id = "com.gaiaventure.mc:id/navigation_farmer")
	public WebElement farmerscreen;

	@FindBy(className = "android.widget.ImageView")
	public WebElement tasksDropDown;

	private static final By REQUEST_OPTION = MobileBy.AndroidUIAutomator("text(\"Requests\")");

	//private static final By FARMER_NAME = MobileBy.AndroidUIAutomator(
		//	"new UiScrollable(new UiSelector().resourceId(\"com.gaiaventure.mc:id/tvName\")).scrollIntoView(new UiSelector().textContains(\"JivaAuto Test13\").instance(0))");

	@FindBy(id = "com.gaiaventure.mc:id/tvYes")
	public WebElement yesBtn;

	@FindBy(id = "com.gaiaventure.mc:id/navigation_account")
	public WebElement accountL;

	@FindBy(id = "com.gaiaventure.mc:id/txtLogout")
	public WebElement logOut;

	public void mcAcceptConnRequest(Menu menu) throws Exception {

		Reporter.log("MC with Farmer Connection task is started", true);

		clickElement(farmerscreen, "Farmer screen");

		clickElement(tasksDropDown, "Task DropDown");

		elementClick(driver, REQUEST_OPTION, 10);

	//	elementGetText(driver, FARMER_NAME, 10);
		
		scrollClickByContains(menu.getParameter3());

		clickElement(yesBtn, "Yes Button");

		// clickElement(account,"Account");

		// clickElement(logOut,"LogOut");

		Reporter.log("MC with Farmer Connection task is completed", true);

	}

}
