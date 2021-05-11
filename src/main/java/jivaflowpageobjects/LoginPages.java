package jivaflowpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import excelClasses.Menu;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import utilities.ElementManager;
import utilities.EnvironmentManager;
import utilities.TestLogger;

public class LoginPages extends ElementManager {

	public LoginPages(WebDriver driver) {
		this.driver = driver;
	}

	// Jiva Web portal

	@FindBy(css = "#loader > app-root > div.row > login-root > div > div > div.container > div > div.col-md-5.loginForm > form > div:nth-child(1) > div > input")
	public WebElement juserName;

	@FindBy(css = "#loader > app-root > div.row > login-root > div > div > div.container > div > div.col-md-5.loginForm > form > div:nth-child(2) > div > input")
	public WebElement jpassWord;

	@FindBy(css = "#loader > app-root > div.row > login-root > div > div > div.container > div > div.col-md-5.loginForm > form > div.text-center > button")
	public WebElement jloginBtn;

	public void jivaPortalLogin(Menu menu) throws Exception {

		Reporter.log("Jiva WebPortal Login ", true);

		SendKeys(juserName, menu.getUsername(), "USERNAME");
		SendKeys(jpassWord, menu.getPassword(), "PASSWORD");
		clickElement(jloginBtn, "LOGIN");

	}

		// Magento Login

	@FindBy(id = "username")
	public WebElement MUSERNAME;

	@FindBy(id = "login")
	public WebElement MPASSWORD;

	@FindBy(xpath = "//span[text()='Sign in']")
	public WebElement MLOGIN_BUTTON;

	@FindBy(xpath = "//button[@class='action-close']")
	public WebElement NEWVERSION_MSG;

	public void magentoLogin(Menu menu) throws Exception {

		Reporter.log("Magento Login ", true);

		// Menu menu =
		// MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.MAGENTOLOGIN);

		SendKeys(MUSERNAME, menu.getUsername(), "USERNAME");
		SendKeys(MPASSWORD, menu.getPassword(), "PASSWORD");
		clickElement(MLOGIN_BUTTON, "LOGIN");
		driver.manage().window().maximize();
		waitUntilTextIsPresent("Advanced Reporting");

	}

	// FarmerCRM Login

	@FindBy(id = "mat-input-0")
	public WebElement CRMUSERNAME;

	@FindBy(id = "mat-input-1")
	public WebElement CRMPASSWORD;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement CRMLOGIN_BUTTON;

	public void farmercrmLogin(Menu menu) throws Exception {

		Reporter.log("Farmer CRM Login ", true);

		// SendKeys(CRMUSERNAME, "mounika.lunavath@jiva.ag", "Username");
		// SendKeys(CRMPASSWORD, "wPzCUEZvgDU9PXBiXDAHhvjp", "Password");

		SendKeys(CRMUSERNAME, menu.getUsername(), "Username");
		SendKeys(CRMPASSWORD, "menu.getPassword()", "Password");

		clickElement(CRMLOGIN_BUTTON, "LOGIN");
	}

	// Farmer App Login

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_google")
	public WebElement google_btn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_consent")
	public WebElement concent_btn;

	@FindBy(id = "com.google.android.gms:id/account_display_name")
	public WebElement login_account;

	// @FindBy(xpath =
	// "//android.widget.EditText[@id='identifierId']/android.view.View/android.view.View")
	@FindBy(className = "android.widget.EditText")
	public WebElement email_id;

	@FindBy(xpath = "//android.widget.Button[@text='Next']")
	public WebElement nxt_btn;

	@FindBy(xpath = "//android.widget.Button[@text='Skip']")
	public WebElement skipBtn;

	// @FindBy(id = "android.widget.EditText")
	@FindBy(className = "android.widget.EditText")
	public WebElement password;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/fragment_sell")
	public WebElement sell_Tab;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/fragment_profile")
	public WebElement profileTab;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/view_help")
	public WebElement helpandSupport;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/tv_app_version")
	public WebElement appVersion;

	@FindBy(className = "android.widget.ImageButton")
	public WebElement backbtn;

	public void farmerLogin(Menu menu) throws Exception {

		Reporter.log("Logging in", true);

		if (elementAvailable(sell_Tab, "HomePage") == true) {
			Reporter.log("User is in home page", true);
			getFarmer_AppVersion(profileTab, helpandSupport, appVersion);
		} else {

			sleep(10000);
			clickElement(google_btn, "GOOGLE_BTN");
			sleep(10000);
			clickElement(concent_btn, "CONCENT_BTN");
			sleep(10000);
			fLogIn(menu);
			getFarmer_AppVersion(profileTab, helpandSupport, appVersion);
		}
	}

	public void fLogIn(Menu menu) {

		try {

			if (elementAvailable(login_account, "Account") == true) {
				Reporter.log("Logged In Account available", true);
				clickElement(login_account, "LOGIN_ACCOUNT");

			} else if (elementAvailable(email_id, "EMail") == true) {

				Reporter.log("Logged In Account Unavailable");
				SendKeys(email_id, menu.getUsername(), "Email");

				if (elementAvailable(nxt_btn, "Next Button") == true) {
					clickElement(nxt_btn, "NXT_BTN");
				} else {
					Reporter.log("Next Button Unavailable", true);
					Reporter.log("TAB sequence is started", true);
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
					Reporter.log("TAB sequence is ended and Next key is pressed ", true);
				}
				if (elementAvailable(skipBtn, "Skip Button") == true) {
					Reporter.log("New login, Skip option available", true);
					clickElement(skipBtn, "Skip Button");
				} else {

					Reporter.log("Not a New User, Skip button unavailable", true);
				}

				if (elementAvailable(password, "Password") == true) {
					SendKeys(password, menu.getPassword(), "Password");

					if (elementAvailable(nxt_btn, "NXT_BTN") == true) {
						clickElement(nxt_btn, "NXT_BTN");
						Reporter.log("Next Button pressed", true);
					} else {
						((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
						Reporter.log("Enter Key pressed", true);
					}
					Reporter.log("Enter Key pressed", true);
					Thread.sleep(10000);
					fiagreeTAB();
				}

			} else {
				System.out.println("User may already Logged In");
			}
		} catch (Exception e) {
			Reporter.log("TIMEOUT EXCEPTION Log In is not done", true);
			TestLogger.elementIdentifierFail("");

		}
	}

	// MC app Login

	@FindBy(id = "com.gaiaventure.mc:id/tvNextButton")
	public WebElement NEXT_BUTTON;

	@FindBy(id = "com.gaiaventure.mc:id/btn_consent")
	public WebElement CONSENT_BUTTON;

	@FindBy(id = "com.gaiaventure.mc:id/editMobileNumber")
	public WebElement PHONENUMBER_FIELD;

	@FindBy(id = "com.gaiaventure.mc:id/btn_next")
	public WebElement PH_NEXTBUTTON;

	@FindBy(id = "com.android.chrome:id/terms_accept")
	public WebElement CHROME_TERMS;

	@FindBy(id = "com.android.chrome:id/next_button")
	public WebElement CHROME_NXT;

	@FindBy(id = "com.android.chrome:id/negative_button")
	public WebElement CHROME_NOTHANKS;

	@FindBy(xpath = "//android.widget.TextView[contains(@text, 'Chrome')]")
	public WebElement BROWSER_CHROME;

	@FindBy(id = "android:id/button_always")
	public WebElement CHROME_ALWAYS;

	@FindBy(id = "com.android.chrome:id/close_button")
	public WebElement CAPTCHA_CLOSE;

//	@FindBy(id = "recaptcha-verify-button")
//	public WebElement CAPTCHA_SCREEN;

	@FindBy(id = "com.gaiaventure.mc:id/editTextNumber")
	public WebElement OTP_FIELD;

	@FindBy(id = "com.gaiaventure.mc:id/btn_next")
	public WebElement OTP_NXT_BTN;

	@FindBy(id = "com.gaiaventure.mc:id/dialogTitle")
	public WebElement CANNOTFINDACCOUNT;

	@FindBy(id = "com.gaiaventure.mc:id/dialogMsg")
	public WebElement ACCOUNTMESSAGE;

	@FindBy(id = "com.gaiaventure.mc:id/dialogBtnOk")
	public WebElement OKBUTTON;

	@FindBy(id = "com.gaiaventure.mc:id/navigation_tasks")
	public WebElement TASKSCREEN;

	@FindBy(id = "com.gaiaventure.mc:id/navigation_account")
	public WebElement accountTab;

	public void mcLogin(Menu menu) throws Exception {

		Reporter.log("Logging in", true);

		if (elementAvailable(TASKSCREEN, "Task Page") == true) {
			Reporter.log("User is in Task page", true);
			getMCAppVersion();
		} else {

			// Assert.assertTrue(waitUntilTextIsPresent("Welcome"));

			clickElement(NEXT_BUTTON, "NEXT_BUTTON");
			clickElement(CONSENT_BUTTON, "CONSENT_BUTTON");
			SendKeys(PHONENUMBER_FIELD, menu.getUsername(), "PhoneNo");
			clickElement(PH_NEXTBUTTON, "PH_NEXTBUTTON");

			if (elementAvailable(CHROME_TERMS, "Accept") == true) {
				clickElement(CHROME_TERMS, "CHROME_TERMS");

				if (elementAvailable(CHROME_NXT, "CHROME_NXT") == true) {
					clickElement(CHROME_NXT, "CHROME_NXT");
				} else {
					Reporter.log("No Chrome Next is displayed");
				}
				if (elementAvailable(CHROME_NOTHANKS, "CHROME_NOTHANKS") == true) {
					clickElement(CHROME_NOTHANKS, "CHROME_NOTHANKS");
				} else {
					Reporter.log("Chrome No Thanks is not displayed");
				}
			} else {
				Reporter.log("No Chrome Terms is displayed");
			}
			if (elementAvailable(BROWSER_CHROME, "Chrome") == true) {
				clickElement(BROWSER_CHROME, "BROWSER_CHROME");
				clickElement(CHROME_ALWAYS, "CHROME_ALWAYS");

			}
			if (elementAvailable(CAPTCHA_CLOSE, "Captcha Close") == true) {
				clickElement(CAPTCHA_CLOSE, "Captcha Close");
				// driver.navigate().back();
			}

			SendKeys(OTP_FIELD, menu.getPassword(), "OTP");
			clickElement(OTP_NXT_BTN, "OTP_NXT_BTN");
			getMCAppVersion();

			if (elementAvailable(CANNOTFINDACCOUNT, "NO ACCOUNT") == true) {
				getText(ACCOUNTMESSAGE, "ACCOUNTMESSAGE");
				clickElement(OKBUTTON, "OKBUTTON");
				driver.quit();
			}

		}

	}
	
	// JL Tablet Login

		@FindBy(id = "btnNext")
		public WebElement Next_btn;

		@FindBy(id = "editTextphonenumber")
		public WebElement phNo_edit;

		@FindBy(id = "llyButtonWithLoaderParent")
		public WebElement PHNext_btn;

		@FindBy(id = "editTextsms")
		public WebElement sms_edit;

		public void jlLogin(Menu menu) throws Exception {

			Reporter.log("JL Login ", true);
			clickElement(Next_btn, "NextButton");
			SendKeys(phNo_edit, menu.getUsername(), "PhoneNumber");
			clickElement(PHNext_btn, "NEXT_BUTTON");
			
			if (elementAvailable(CHROME_TERMS, "Accept") == true) {
				clickElement(CHROME_TERMS, "CHROME_TERMS");

				if (elementAvailable(CHROME_NXT, "CHROME_NXT") == true) {
					clickElement(CHROME_NXT, "CHROME_NXT");
				} else {
					Reporter.log("No Chrome Next is displayed");
				}
				if (elementAvailable(CHROME_NOTHANKS, "CHROME_NOTHANKS") == true) {
					clickElement(CHROME_NOTHANKS, "CHROME_NOTHANKS");
				} else {
					Reporter.log("Chrome No Thanks is not displayed");
				}
			} else {
				Reporter.log("No Chrome Terms is displayed");
			}
			if (elementAvailable(BROWSER_CHROME, "Chrome") == true) {
				clickElement(BROWSER_CHROME, "BROWSER_CHROME");
				clickElement(CHROME_ALWAYS, "CHROME_ALWAYS");

			}
			if (elementAvailable(CAPTCHA_CLOSE, "Captcha Close") == true) {
				clickElement(CAPTCHA_CLOSE, "Captcha Close");
				// driver.navigate().back();
			}


			SendKeys(sms_edit, menu.getPassword(), "OTP_FIELD");
			clickElement(PHNext_btn, "NEXT_BUTTON");
			Reporter.log("JL Logged In ", true);
		}

}
