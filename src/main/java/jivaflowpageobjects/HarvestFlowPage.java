package jivaflowpageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import excelClasses.Menu;
import io.appium.java_client.MobileElement;
import utilities.ElementManager;

/*This class file covers below steps
1.Mc Initiate HarvestPurchase
2.GetCode from farmer app
3.Apply code in Mc app , Dispatch Truck and Calculate HarvestCommision*/

public class HarvestFlowPage extends ElementManager {

	public HarvestFlowPage(WebDriver driver) {
		this.driver = driver;
	}

	protected static String code;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Dispatch truck']")
	public WebElement dispatchtruck_option;

	@FindBy(id = "com.gaiaventure.mc:id/etSearch")
	public WebElement searchfarmer_searchbox;

	@FindBy(id = "com.gaiaventure.mc:id/etName")
	public WebElement searchfarmer_name;

	@FindBy(id = "com.gaiaventure.mc:id/btn_next")
	public WebElement nextbutton;

	@FindBy(id = "com.gaiaventure.mc:id/harvestDetailsCornGradeDropdown")
	public WebElement grade_dropdown;

	@FindBy(id = "com.gaiaventure.mc:id/harvestDetailsInput")
	public WebElement harvestdetailsinput_fields;

	@FindBy(id = "com.gaiaventure.mc:id/harvestDetailsCalculateBtn")
	public WebElement calculateharvest_btn;

	@FindBy(id = "com.gaiaventure.mc:id/tvPaymentTotal")
	public WebElement paymenttotal;

	@FindBy(id = "com.gaiaventure.mc:id/harvest_price_details_confirm_btn")
	public WebElement confirmbutton;

	@FindBy(id = "com.gaiaventure.mc:id/harvestConfirmationFarmerCode")
	public WebElement confirmationcode;

	@FindBy(id = "com.gaiaventure.mc:id/harvestConfirmationFarmerCodeNextBtn")
	public WebElement confirmationnext_btn;

	@FindBy(id = "com.gaiaventure.mc:id/snackbar_text")
	public WebElement snackbar_text;

	@FindBy(id = "com.gaiaventure.mc:id/harvestConfirmationDoneBtn")
	public WebElement donebutton;

	@FindBy(xpath = "//android.widget.TextView[1]")
	public WebElement successmessage;

	@FindBy(id = "com.gaiaventure.mc:id/harvest_search")
	public WebElement harvestsearch;

	@FindBy(id = "com.gaiaventure.mc:id/nextBtn")
	public WebElement harvestsearch_nextbtn;

	@FindBy(id = "com.gaiaventure.mc:id/tvAddress")
	public WebElement totalweight;

	@FindBy(xpath = "//android.widget.EditText[1]")
	public WebElement drivename_field;

	@FindBy(xpath = "//android.widget.EditText[2]")
	public WebElement drivephno_field;

	@FindBy(xpath = "//android.widget.EditText[3]")
	public WebElement licenseplateno_field;

	@FindBy(id = "com.gaiaventure.mc:id/dialog_msg")
	public WebElement dispatch_message;

	@FindBy(id = "com.gaiaventure.mc:id/btn_confirm")
	public WebElement dispatch_confirmbtn;

	@FindBy(xpath = "//android.widget.TextView[1]")
	public WebElement dispatch_successmessage1;

	@FindBy(xpath = "//android.widget.TextView[2]")
	public WebElement dispatch_successmessage2;

	@FindBy(id = "com.gaiaventure.mc:id/btn_done")
	public WebElement dispatch_donebtn;

	// Negative flow

	@FindBy(id = "com.gaiaventure.mc:id/harvestDetailsInputErrorMessage")
	public WebElement errormessage;

	@FindBy(xpath = "//android.widget.TextView[4]")
	public WebElement invalidphonenumber;

	@FindBy(id = "com.gaiaventure.mc:id/harvest_price_details_cancel_btn")
	public WebElement canceltransaction;

	@FindBy(id = "com.gaiaventure.mc:id/dialogMsg")
	public WebElement canceltransaction_dialogmessage;

	@FindBy(id = "com.gaiaventure.mc:id/dialogBtnOk")
	public WebElement canceltransaction_button;

	private static String total;

	// protected String farmerName = "Ranjith"; //MC - Ranjith
	// protected String farmerName = "jivaautotest03"; // MC - Vignesh
	protected String farmerName = "automation testing"; // MC - Vignesh

	@FindBy(id = "com.gaiaventure.mc:id/navigation_tasks")
	public WebElement taskButton;

	@FindBy(id = "com.gaiaventure.mc:id/tvToolbarRightButton")
	public WebElement harvestButton;

	@FindBy(xpath = "//android.widget.TextView[@text='Purchase harvest']")
	public WebElement purchaseHarvestButton;

	@FindBy(id = "com.gaiaventure.mc:id/etSearch")
	public WebElement searchFarmer;
	
	@FindBy(xpath = "(//android.widget.EditText[@id=com.gaiaventure.mc:id/harvestDetailsInput'])[4]")
	public WebElement waterlvl;

	public void enter_HarvestDetails(Menu menu) throws Exception {

		Reporter.log("MC Harvest Flow is started", true);
		clickElement(taskButton, "TASKSBUTTON");
		clickElement(harvestButton, "HARVESTBUTTON");
		clickElement(purchaseHarvestButton, "PURCHASEHARVEST_OPTION");
		SendKeys(searchFarmer, menu.getParameter3(), "SearchFarmer");

		// Need to rework on list
		clickElement(searchfarmer_name, "SEARCHFARMER_NAME");
		clickElement(nextbutton, "NEXTBUTTON");
		// Assert.assertTrue(waitUntilTextIsPresent("Confirm farmer for harvest
		// purchase"));
		clickElement(nextbutton, "NEXTBUTTON");
		sleep(2000);
		List<WebElement> harvertInputs = driver.findElements(By.id("com.gaiaventure.mc:id/harvestDetailsInput"));
		if (harvertInputs.size() > 0) {
			for (int i = 0; i < harvertInputs.size(); i++) {
				harvertInputs.get(0).sendKeys("50");
				Reporter.log("Entered 50Kg in Gross Weight Field", true);
				harvertInputs.get(1).sendKeys("1");
				Reporter.log("Entered 1 Kg in No.of Bags Field", true);
				harvertInputs.get(2).sendKeys("1");
				Reporter.log("Entered 1 No.of bags from sahabatJiva Field", true);
				scrollAndTextView("Calculate harvest price");

				MobileElement el1 = (MobileElement) driver.findElement(By.xpath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout/android.widget.EditText"));
				el1.sendKeys("11");
				
				Reporter.log("Entered 11% in Water level Field", true);
				break;

			}
		}

		clickElement(calculateharvest_btn, "CALCULATEHARVEST_BTN");
		// waitUntilTextIsPresent("Payment total");
		sleep(10000);
		setTotal(paymenttotal.getText());
		setTotal(getTotal().substring(0, getTotal().lastIndexOf(',', getTotal().length() - 2)).replaceAll("\\D+", ""));
		Reporter.log("Payment total is " + getTotal(), true);
		clickElement(confirmbutton, "CONFIRMBUTTON");
		// Assert.assertTrue(waitUntilTextIsPresent("Enter the code from the farmer"));

	}

	
	@FindBy(id = "com.gaiaventure.advisor.qa:id/fragment_sell")
	public WebElement SELL_TAB;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_confirm_harvest")
	public WebElement GETSECURITYCODE;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/et_cash_amount")
	public WebElement AMOUNTFIELD;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_next")
	public WebElement NEXTBUTTON;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/tv_security_code")
	public WebElement SECURITYCODE;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_done")
	public WebElement DONEBUTTON;

	public void enterAmount_GetCode() throws Exception {

		Reporter.log("Farmer Loan Request is started", true);

		clickElement(SELL_TAB, "Sell");
		scrollPage("Get security code");
		clickElement(GETSECURITYCODE, "SecurityCode");
		// waitUntilTextIsPresent("Enter the amount of cash your Sahabat Jiva has given
		// you");
		SendKeys(AMOUNTFIELD, HarvestFlowPage.getTotal(), "PaymentTotal");
		clickElement(NEXTBUTTON, "NextButton");
		setCode(SECURITYCODE.getText());
		Reporter.log("Security Code is " + getCode(), true);

	}

	public void paste_SecurityCode() throws InterruptedException {

		clickElement(taskButton, "TASKSBUTTON");
		clickElement(harvestButton, "HARVESTBUTTON");
		clickElement(purchaseHarvestButton, "PURCHASEHARVEST_OPTION");
		// waitUntilTextIsPresent("A harvest is pending confirmation for this farmer");
		clickElement(nextbutton, "NEXTBUTTON");
		scrollAndTextView("Calculate harvest price");
		clickElement(calculateharvest_btn, "CALCULATEHARVEST_BTN");
		clickElement(confirmbutton, "CONFIRMBUTTON");
		SendKeys(confirmationcode, HarvestFlowPage.getCode(), "CONFIRMATIONCODE");
		clickElement(confirmationnext_btn, "CONFIRMATIONNEXT_BTN");

		if (elementAvailable(snackbar_text, "Popup Message") == true) {
			Reporter.log(snackbar_text.getText(), true);
			Assert.assertFalse(true);
		} else if (elementAvailable(donebutton, "SuccessMessage")) {
			getText(successmessage, "SUCCESSMESSAGE");
			clickElement(donebutton, "DONEBUTTON");

		} else {
			getText(snackbar_text, "SNACKBAR_TEXT");
		}

		// waitUntilTextIsPresent("Dispatch truck");
	}

	public void dispatchTruck(Menu menu) throws InterruptedException {
		clickElement(dispatchtruck_option, "DISPATCHTRUCK_OPTION");
		if (elementAvailable(snackbar_text, "Popup Message") == true) {
			getText(snackbar_text, "SNACKBAR_TEXT");
			Assert.assertFalse(true);
		} else {
			SendKeys(harvestsearch, menu.getParameter3(), "HARVESTSEARCH");
			if (elementAvailable(searchfarmer_name, "FarmerList") == true) {
				Reporter.log("Farmer is available", true);
			} else {
				Reporter.log("Farmer is not available", true);

			}
			clickElement(searchfarmer_name, "SEARCHFARMER_NAME");
			clickElement(harvestsearch_nextbtn, "HARVESTSEARCH_NEXTBTN");
			// waitUntilTextIsPresent("Confirm farmers for dispatch");
			sleep(10000);
			getText(totalweight, "TOTALWEIGHT");
			clickElement(nextbutton, "NEXTBUTTON");
			// waitUntilTextIsPresent("Truck details");
			SendKeys(drivename_field, "TestDriver", "DRIVENAME_FIELD");
			SendKeys(drivephno_field, "+624565456767", "DRIVEPHNO_FIELD");
			SendKeys(licenseplateno_field, "555", "LICENSEPLATENO_FIELD");
			clickElement(nextbutton, "NEXTBUTTON");
			// waitUntilTextIsPresent("Are you sure?");
			sleep(10000);
			getText(dispatch_message, "DISPATCH_MESSAGE");
			clickElement(dispatch_confirmbtn, "DISPATCH_CONFIRMBTN");
			sleep(10000);
			getText(dispatch_successmessage1, "DISPATCH_SUCCESSMESSAGE1");
			getText(dispatch_successmessage2, "DISPATCH_SUCCESSMESSAGE2");
			clickElement(dispatch_donebtn, "DISPATCH_DONEBTN");

		}

	}

	
	@FindBy(id = "com.gaiaventure.mc:id/navigation_payment")
	public WebElement paymentTab;

	@FindBy(id = "com.gaiaventure.mc:id/tvVolumepurchased")
	public WebElement volumePurchased;

	@FindBy(id = "com.gaiaventure.mc:id/tvEarnedHarvest")
	public WebElement commissionEarned;

	public void harvestCommission() {
		clickElement(paymentTab, "PaymentTab");
		scrollPage("Harvest purchase");
		Reporter.log("**********************************************************", true);
		calculateCommission(volumePurchased, commissionEarned, "HarvestCommission");
	}

	public void calculateCommission(WebElement amountOn, WebElement commissionAmt, String commissionName) {
		String kg = amountOn.getText();
		Reporter.log("Total KG purchased is " + kg, true);
		String loanSplit = (kg.substring(0, kg.lastIndexOf(',', kg.length() - 2)).replaceAll("\\D+", ""));
		int convertToInt = Integer.parseInt(loanSplit);
		int percentage = convertToInt * 100 / 2;
		String finalCommission = Integer.toString(percentage);
		Reporter.log("Harvest Commission amount should be " + finalCommission, true);

		String cAmount = commissionAmt.getText();
		String cSplit = (cAmount.substring(0, cAmount.lastIndexOf(',', cAmount.length() - 2)).replaceAll("\\D+", ""));
		Reporter.log("Commission Earned by MC is " + cSplit, true);
		if (finalCommission.equals(cSplit)) {
			Reporter.log(commissionName + " is correct " + cSplit, true);
		} else {
			Reporter.log(commissionName + " is not correct " + cSplit, true);
		}

	}
	
	
	public void cancelHarvest_Transaction(Menu menu) throws InterruptedException {

		clickElement(taskButton, "TASKSBUTTON");
		clickElement(harvestButton, "HARVESTBUTTON");
		clickElement(purchaseHarvestButton, "PURCHASEHARVEST_OPTION");
		SendKeys(searchfarmer_searchbox, menu.getParameter4(), "SEARCHFARMER_SEARCHBOX");
		clickElement(searchfarmer_name, "SEARCHFARMER_NAME");
		clickElement(nextbutton, "NEXTBUTTON");
		clickElement(nextbutton, "NEXTBUTTON");
		clickElement(canceltransaction, "CANCELTRANSACTION");
		getText(canceltransaction_dialogmessage, "CANCELTRANSACTION_DIALOGMESSAGE");
		clickElement(canceltransaction_button, "CANCELTRANSACTION_BUTTON");
		if (elementAvailable(snackbar_text, "Popup Message") == true) {
			getText(snackbar_text, "SNACKBAR_TEXT");
			Assert.assertFalse(true);
		}

	}

	public void harvestFlow_Negative(Menu menu) throws Exception {

		Reporter.log("MC Harvest Negative Flow is started", true);

		clickElement(taskButton, "TASKSBUTTON");
		clickElement(harvestButton, "HARVESTBUTTON");
		clickElement(purchaseHarvestButton, "PURCHASEHARVEST_OPTION");
		SendKeys(searchfarmer_searchbox, menu.getParameter4(), "SEARCHFARMER_SEARCHBOX");
		clickElement(searchfarmer_name, "SEARCHFARMER_NAME");

		clickElement(nextbutton, "NEXTBUTTON");
		clickElement(nextbutton, "NEXTBUTTON");
		sleep(2000);
		List<WebElement> harvertInputs = driver.findElements(By.id("com.gaiaventure.mc:id/harvestDetailsInput"));
		if (harvertInputs.size() > 0) {
			for (int i = 0; i < harvertInputs.size(); i++) {
				harvertInputs.get(0).sendKeys("50");
				Reporter.log("Entered 100Kg in Gross Weight Field", true);
				harvertInputs.get(1).sendKeys("1");
				Reporter.log("Entered 1 Kg in No.of Bags Field", true);
				harvertInputs.get(2).sendKeys("2");
				Reporter.log("Entered 2 No.of bags from sahabatJiva Field", true);
				scrollAndTextView("Calculate harvest price");
				if (elementAvailable(errormessage, "Input ErrorMessage")) {
					getText(errormessage, "ERRORMESSAGE");
					Reporter.log("Validation message is displayed for Invalid Bags", true);
					harvertInputs.get(2).clear();
					harvertInputs.get(2).sendKeys("1");
				} else {
					Assert.assertFalse(true);
				}

				MobileElement el1 = (MobileElement) driver.findElement(By.xpath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.EditText"));
				el1.sendKeys("200");
				Reporter.log("Entered 200% in Water level Field", true);
				clickElement(calculateharvest_btn, "CALCULATEHARVEST_BTN");
				if (elementAvailable(errormessage, "Input ErrorMessage")) {
					getText(errormessage, "ERRORMESSAGE");
					Reporter.log("Validation message is displayed for Invalid Moisture Level", true);
					Assert.assertFalse(true);
				}
				break;

			}

		}
	}

	
	public void dispatchTruck_Negative(Menu menu) throws InterruptedException {
		clickElement(taskButton, "TASKSBUTTON");
		clickElement(harvestButton, "HARVESTBUTTON");
		clickElement(dispatchtruck_option, "DISPATCHTRUCK_OPTION");

		SendKeys(harvestsearch, menu.getParameter4(), "HARVESTSEARCH");
		if (elementAvailable(searchfarmer_name, "FarmerList") == true) {
			Reporter.log("Farmer is available", true);
		} else {
			Reporter.log("Farmer is not available", true);

		}
		clickElement(searchfarmer_name, "clickElement");
		clickElement(harvestsearch_nextbtn, "HARVESTSEARCH_NEXTBTN");

		clickElement(nextbutton, "NEXTBUTTON");

		SendKeys(drivename_field, "TestDriver", "DRIVENAME_FIELD");
		SendKeys(drivephno_field, "4565456767", "DRIVEPHNO_FIELD");
		if (elementAvailable(invalidphonenumber, "Validation Message")) {
			getText(invalidphonenumber, "INVALIDPHONENUMBER");
			Assert.assertFalse(true);
		} else {
			Reporter.log("Validation message is not displayed ", true);
		}

	}

	public static String getTotal() {
		return total;
	}

	public static void setTotal(String total) {
		HarvestFlowPage.total = total;
	}

	public static String getCode() {
		return code;
	}

	public static void setCode(String code) {
		HarvestFlowPage.code = code;
	}
}
