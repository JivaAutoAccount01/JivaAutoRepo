package jivaflowpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import excelClasses.Menu;
import io.appium.java_client.MobileBy;
import utilities.ElementManager;

public class LoanKYCFlowPage extends ElementManager {

	/*
	 * 1. Farmer Apply the Loan 2. MC do the KYC Submit 3. Loan Approval in Farmer
	 * CRM Portal 4. Loan Amount submit in Magento Portal 5. Farmer View the Loan
	 * status and amount
	 */

	public LoanKYCFlowPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "com.gaiaventure.advisor.qa:id/fragment_buy")
	public WebElement buyTab;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_cta")
	public WebElement payLater;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_meet_collector")
	public WebElement meetmcbtn;

	private static final By APPLY_NOW = MobileBy.AndroidUIAutomator(
			"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Apply now\").instance(0))");

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_accept")
	public WebElement acceptbtn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/et_address")
	public WebElement addressvalue;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/et_postal_code")
	public WebElement postalcode;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_confirm")
	public WebElement confirmapply;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_ok")
	public WebElement okbutton;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/fragment_profile")
	public WebElement account;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/link_logout")
	public WebElement logout;

	public void fapplyLoan() throws Exception {

		Reporter.log("Farmer Apply Loan started", true);
		sleep(5000);
		clickElement(buyTab, "Buy Button");
		clickElement(payLater, "Pay Later");
		clickElement(meetmcbtn, "Meet MC");
		//elementClick(driver, APPLY_NOW, 20);
		scrollAndClick("Apply now");
		clickElement(acceptbtn, "Accept Button");
		SendKeys(addressvalue, "lumpangan", "ADDRESS VALUE");
		SendKeys(postalcode, "89067", "Postal Code");
		clickElement(confirmapply, "Confirm Apply");

		sleep(5000);
		if (elementAvailable(okbutton, "OK Button") == true) {
			clickElement(okbutton, "Send");
			Reporter.log("Loan Requset Sent, OK button clicked.", true);
		} else {

			Reporter.log("Loan Requset Sent, OK button not displayed.", true);
		}

		// elementClick(driver,ACCOUNT,20);
		// elementClick(driver,LOGOUT,20);

		Reporter.log("Farmer Loan Request is Completed", true);

	}
	
	
	/* 2. MC Submit the KYC*/
	
	@FindBy(id = "com.gaiaventure.mc:id/navigation_tasks")
	public WebElement taskScreen;

	@FindBy(className = "android.widget.ImageView")
	public WebElement taskDropDown;
	
	private static final By LOAN_OPTION = MobileBy.AndroidUIAutomator("text(\"Loan application\")");
	//private static final By APPLYFORLOAN = By.xpath("//android.widget.TextView[@text='Apply for loan']");
	
	//private static final By APPLYFORLOAN = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new UiSelector().textContains(\"Apply for a loan for JivaAuto Test13\").instance(0))");
	
	//private static final By UPLOAD_KTPCARD = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new UiSelector().textContains(\"Upload the KTP photo of the farmer JivaAuto Test06\").instance(0))");
	
	private static final By LOAN_OK=MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"OK\"));");

	@FindBy(id = "com.gaiaventure.mc:id/tvOK")
	public WebElement okButton;
	
	@FindBy(id = "com.gaiaventure.mc:id/btn_consent")
	public WebElement loanConsent;
	
	@FindBy(id = "com.gaiaventure.mc:id/tvFieldSize")
	public WebElement fieldSize;
	
	@FindBy(id = "com.gaiaventure.mc:id/includeSpinnerLayout")
	public WebElement yearsDD;
	
	@FindBy(xpath = "//android.widget.CheckedTextView[4]")
	public WebElement farmSize;
	
	//private static final By FARM_DETAILS = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Field size\").instance(0))");
	
	@FindBy(id = "com.gaiaventure.mc:id/includeSpinnerLayout")
	public WebElement yearsLiving;
	
	@FindBy(xpath = "//android.widget.CheckedTextView[4]")
	public WebElement noOfYears;
	
	@FindBy(id = "com.gaiaventure.mc:id/includeSpinnerLayout")
	public WebElement takeLoan;
	
	@FindBy(xpath = "//android.widget.CheckedTextView[2]")
	public WebElement takeLoanDD;
	
	@FindBy(id = "com.gaiaventure.mc:id/tvLoanAmountValue")
	public WebElement loanAmount;
	
	@FindBy(id = "com.gaiaventure.mc:id/txSaveandcontinue")
	public WebElement saveAndContinue;
	
	@FindBy(xpath = "//android.widget.Button[@text='Upload KTP card']")
	public WebElement uploadKTP;
	
	@FindBy(id = "com.gaiaventure.mc:id/rlTakeToGallery")
	public WebElement photoGallery;
	
	@FindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	public WebElement galleryAccess;

	@FindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	public WebElement photoAccess;
	
	@FindBy(id = "com.google.android.apps.photos:id/image")
	public WebElement imageFolder;
	
	private static final By FIRST_IMAGE = By.className("android.view.ViewGroup");
	
	//@FindBy(className = "android.view.ViewGroup")
	//public WebElement firstImage;
	
	@FindBy(id = "com.gaiaventure.mc:id/menu_crop")
	public WebElement cropImage;
	
	@FindBy(id = "com.gaiaventure.mc:id/btn_next_comp")
	public WebElement imageNxtBtn;
	
	@FindBy(id = "com.gaiaventure.mc:id/btnUpladeKTPImg")
	public WebElement uploadKartu;
	
	@FindBy(id = "com.gaiaventure.mc:id/aprooval_status")
	public WebElement successMsg1;
	
	@FindBy(id = "com.gaiaventure.mc:id/aprooval_status_message")
	public WebElement successMsg2;
	
	@FindBy(id = "com.gaiaventure.mc:id/btnUpladeKTPImg")
	public WebElement kartuUploadKtp;
	
	@FindBy(id = "com.gaiaventure.mc:id/navigation_account")
	public WebElement accountLink;
	
	@FindBy(id = "com.gaiaventure.mc:id/txtLogout")
	public WebElement logOut;


	public void mcSubmitKYC(Menu menu) throws Exception {

		Reporter.log("MC Loan-KYC Submit started", true);

		clickElement(taskScreen,"Task screen");
		clickElement(taskDropDown, "Task DropDown");
		
		elementClick(driver, LOAN_OPTION, 15);
	
		//scrollAndTextView("Apply for loan");
		
		scrollClickByContains("Apply for a loan for " + menu.getParameter3());
		//elementClick(driver, APPLYFORLOAN, 10);
		
		elementClick(driver, LOAN_OK, 20);
	
		clickElement(loanConsent, "Click Consent");
		scrollAndTextMatchView("Save and continue");
		//scrollAndTextView("Hectares");

		clickElement(fieldSize, "Field Size");
		clickElement(yearsDD, "Years As Farmer");
		Reporter.log("How Many Years as Farmer dropdown is selected");
		
		clickElement(farmSize, "Farm Size");
	
		clickElement(yearsLiving, "Years Of Living");
		Reporter.log("Years living in this village dropdown is selected");
	
		clickElement(noOfYears, "No Of Years");
		clickElement(takeLoan, "Loan Taken");
		Reporter.log("Did u took loan dropdown is selected");
		clickElement(takeLoanDD, "Loan Taken Amount");
		SendKeys(loanAmount, "20000", "Loan Amount");
		clickElement(saveAndContinue, "Click Save And Continue");
		//sleep(5000);
		//elementClick(driver,UPLOAD_KTPCARD,10);
	
		clickElement(uploadKTP, "Upload KTP");
		clickElement(photoGallery, "Photo Gallery Select");
		clickElement(galleryAccess, "Allow Gallery Access");
		clickElement(photoAccess, "Allow Photo Access");
		clickElement(imageFolder, "Click Image Folder");
		clickFirstImage(driver, FIRST_IMAGE);
		//elementClick(driver, PHOTONAME, 30);
		sleep(5000);
		
		clickElement(cropImage, "Click Crop Image");
		clickElement(imageNxtBtn, "Click Next Button");
		clickElement(uploadKartu, "Upload Next Button");
		clickElement(photoGallery, "Photo Gallery Select");
		clickElement(imageFolder, "Click Image Folder");
		clickFirstImage(driver, FIRST_IMAGE);
		//elementClick(driver, FIRST_IMAGE, 10);
		sleep(5000);
		clickElement(cropImage, "Click Crop Image");
	
		clickElement(imageNxtBtn, "Click Next Button");
	
		getText(successMsg1,"Success Message");
		
		getText(successMsg2,"Success Message");
		
		clickElement(kartuUploadKtp, "Kartu Upload KTP");
	
		//clickElement(accountLink, "Account");
		//clickElement(logOut, "LOGOUT");

		Reporter.log("MC Loan-KYC Submit completed", true);
	}

	
	/* 3. Loan Submit in Farmer CRM Portal */
	
	@FindBy(xpath = "//div[@class='mat-form-field-flex ng-tns-c97-4']")
	public WebElement filterDropdown;

	@FindBy(xpath = "//mat-select[@formcontrolname='status']")
	public WebElement statusDropdown;

	@FindBy(xpath = "//span[contains(text(),'Approved')]")
	public WebElement statusApproved;

	@FindBy(xpath = "//input[@formcontrolname='amount']")
	public WebElement amountField;

	@FindBy(xpath = "//button[@class='mat-focus-indicator mat-button mat-button-base mat-primary']")
	public WebElement saveBtn;

	@FindBy(xpath = "//div[@class='mat-menu-trigger nav-item account']")
	public WebElement logoutLink;

	@FindBy(xpath = "//span[text()='Log out']")
	public WebElement logoutBtn;

	//private static final By FILTERDROPDOWN = By.xpath("//div[@class='mat-form-field-flex ng-tns-c97-4']");


	public void loanApproval(Menu menu) throws Exception {

		Reporter.log("Farmer CRM Loan Approval  Started", true);
		Thread.sleep(5000);
		clickElement(filterDropdown, "Filter Dropdown");
		dropdownSelect("Pending requests");
		sleep(3000);

		if (nameSearch(menu.getParameter3()) == true) {
			Reporter.log("Name is available", true);
			sleep(5000);
			clickElement(statusDropdown, "Filter Dropdown");
			clickElement(statusApproved, "Approved");
			// dropdownSelect("Approved");
			clickElement(amountField, "AmountField");
			clickElement(saveBtn, "Save Button");
			sleep(3000);
			driver.switchTo().alert().accept();

		} else {
			Reporter.log("Farmer Name is not available", true);
		}

		sleep(5000);

		clickElement(logoutLink, "LogOut Link");
		clickElement(logoutBtn, "LogOut Button");

	}

	/* 4. Magento Loan Amount Submit */
	
	@FindBy(xpath = "//span[text()='Farmers']")
	public WebElement farmersMenu;

	@FindBy(xpath = "//span[text()='All Farmers']")
	public WebElement allFarmers;

	@FindBy(xpath = "//a[text()='Edit']")
	public WebElement editLink;
	
	@FindBy(xpath = "//*[@id=\"fulltext\"]")
	public WebElement searchInput;

	@FindBy(xpath = "//span[text()='Loan Amount & Refunds']")
	public WebElement loanAmountLink;

	@FindBy(id = "_storecreditadd_or_subtract")
	public WebElement magnetoLAmount;

	@FindBy(id = "save")
	public WebElement submitLoanAmount;


	public void magentoLoanSubmit(Menu menu) throws Exception {

		Reporter.log("Magento Loan Submit  Started", true);

		clickElement(farmersMenu, "Sales Menu");
		clickElement(allFarmers, "All Farmers");
		checkPageIsReady();
		//SendKeys(searchInput, menu.getParameter4(), "FarmerSearch");
		SendKeys(searchInput, menu.getParameter3(), "Farmer Name");
		
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).build().perform();
		sleep(5000);
		clickElement(editLink, "Edit Link");
		clickElement(loanAmountLink, "Loan Amount Link");
		
		// js.executeScript("window.scrollBy(0,1000)");
		SendKeys(magnetoLAmount, "250000", "Magento Loan Amount");
		clickElement(submitLoanAmount, "Submit Loan Amount");
				
		clickElement(logoutLink, "Account");
		clickElement(logoutBtn, "Log Out");
		
		Reporter.log("Magento Loan Submit Completed", true);


	}
	
	
public void viewLoanResults(){
	
	Reporter.log("Farmer View Loan Results  Started", true);
	
	// This will reuse the viewResult() method from InputFlowPage
	inputflowpage.viewResult();
	
	Reporter.log("Farmer View Loan Results Completed", true);

}

}
