package jivaflowpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import excelClasses.Menu;
import excelClasses.MenuFactory;
import excelClasses.TestInputsMenu;
import io.appium.java_client.MobileBy;
import utilities.ElementManager;

/*This class covers below steps
1.Farmer request item
2.MC initiate cod/Loan as mode of payment
3.Farmer accept order initiated by MC
4.shipment approval from Magento portal 
5.MC will receive the delivery
6.Farmer accepts the delivery 
7.Calculate MC commission
8.Farmer view receipt
9.MC Enter Delivery Code from Farmer and complete the Delivery for Loan Order
10.Farmer View the Receipts/Loan Status 
11.View and Verify the MC Commission
12.MC Rejects the Item Request from Farmer
*/

public class InputFlowPage extends ElementManager {

	public InputFlowPage(WebDriver driver) {
		this.driver = driver;
	}

	/* 1.Farmer request item */

	@FindBy(id = "com.gaiaventure.advisor.qa:id/fragment_buy")
	public WebElement buyTab;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_chat_now")
	public WebElement acceptordBtn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/et_postal_code")
	public WebElement postalCode;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_confirm")
	public WebElement confirmordRequest;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/fragment_profile")
	public WebElement account;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/link_logout")
	public WebElement logout;

	public void fOrdItemRequest() throws Exception {

		Reporter.log("Farmer Item Request Started", true);

		clickElement(buyTab, "BUY TAB");
		scrollAndClick("Request item");
		clickElement(acceptordBtn, "ACCEPT");
		SendKeys(postalCode, "89067", "Postal Code");
		clickElement(confirmordRequest, "CONFIRM");
		sleep(3000);
		Reporter.log("Farmer Item Request Completed", true);
	}

	/* Method to select Loan Option to create order */

	@FindBy(id = "com.gaiaventure.mc:id/llytPayOnLoan")
	public WebElement loanOption;

	@FindBy(id = "com.gaiaventure.mc:id/llytCashOnDelivery")
	public WebElement cashOnDelivery_Option;

	public void mccreateOrdOnLoan() throws Exception {
		mc_SwitchActivity();
		Reporter.log("ON LOAN FLOW IS STARTED", true);
		mcCreateOrder(loanOption);

	}

	/* Method to select COD Option to create order */

	public void mcCODPurchase() throws Exception {
		// mc_SwitchActivity();
		Reporter.log("CASH ON DELIVERY FLOW IS STARTED", true);
		mcCreateOrder(cashOnDelivery_Option);

	}

	/* 2. MC Create Order */

	@FindBy(id = "com.gaiaventure.mc:id/navigation_tasks")
	public WebElement taskScreen;

	@FindBy(className = "android.widget.ImageView")
	public WebElement taskDropDown;

	private static final By FARMINGREDIENT_OPTION = MobileBy.AndroidUIAutomator("text(\"Farming ingredient\")");

	/*
	 * private static final By COD_ORDER = MobileBy.AndroidUIAutomator(
	 * "new UiScrollable(new UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new UiSelector().textContains(\"Order farming ingredients for browser stack2021\").instance(0))");
	 * 
	 * private static final By LOAN_ORDER = MobileBy.AndroidUIAutomator(
	 * "new UiScrollable(new UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new UiSelector().textContains(\"Order farming ingredients for testing 0203\").instance(0))");
	 */
	@FindBy(id = "com.gaiaventure.mc:id/tv_filled_btn_input_job_progress")
	public WebElement createOrdBtn;

	/*
	 * @FindBy(id = "com.gaiaventure.mc:id/linearLayoutRequest") public WebElement
	 * codOrdOption;
	 * 
	 * @FindBy(id = "com.gaiaventure.mc:id/linearLayoutButtons") public WebElement
	 * loanOption;
	 */
	@FindBy(id = "com.gaiaventure.mc:id/includedShoppingCartSpinnerLayout")
	public WebElement itemDropDown;

	private static final By SEED_TREATMENTOPTION = MobileBy.AndroidUIAutomator("text(\"Seed Treatment\")");

	private static final By ITEM_STANDTAKTOP500 = MobileBy.AndroidUIAutomator("text(\"StandakTop-500-FS\")");

	@FindBy(id = "com.gaiaventure.mc:id/btnQuantityPlus")
	public WebElement plusBtn;

	@FindBy(id = "com.gaiaventure.mc:id/btnQuantityMinus")
	public WebElement minusBtn;

	@FindBy(id = "com.gaiaventure.mc:id/tvAddOrderButton")
	public WebElement addOrderBtn;

	private static final By ITEM_GAUCHO = MobileBy.AndroidUIAutomator("text(\"Gaucho\")");

	@FindBy(id = "com.gaiaventure.mc:id/tvReviewOrderButton")
	public WebElement reviewOrder;

	@FindBy(id = "com.gaiaventure.mc:id/tvDotLoaderText")
	public WebElement codSubmit;

	@FindBy(id = "com.gaiaventure.mc:id/tvDoneButton")
	public WebElement codDone;

	@FindBy(id = "com.gaiaventure.mc:id/navigation_account")
	public WebElement accountLink;

	@FindBy(id = "com.gaiaventure.mc:id/txtLogout")
	public WebElement logOut;

	public void mcCreateOrder(WebElement purchaseMode) throws Exception {

		Reporter.log("MC COD Create is started", true);

		Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.INPUTFLOWDATA);

		clickElement(taskScreen, "Tasks screen");
		clickElement(taskDropDown, "Tasks DropDown");
		elementClick(driver, FARMINGREDIENT_OPTION, 10);
		// elementClick(driver, COD_ORDER, 10);
		scrollClickByContains("Order farming ingredients for " + menu.getParameter3());
		clickElement(createOrdBtn, "Create Order Option");
		clickElement(purchaseMode, "PURCHASE OPTION");
		clickElement(itemDropDown, "Items DropDown");
		elementClick(driver, SEED_TREATMENTOPTION, 10);
		elementClick(driver, ITEM_STANDTAKTOP500, 10);
		sleep(3000);
		scrollAndTextMatchView("Add to order");

		clickElement(plusBtn, "Plus Button");
		clickElement(plusBtn, "Plus Button");
		clickElement(minusBtn, "Minus Button");
		clickElement(addOrderBtn, "Add Order Button");
		sleep(3000);
		elementClick(driver, ITEM_GAUCHO, 10);
		sleep(3000);
		// scrollAndView("Add to order");
		scrollAndTextMatchView("Add to order");
		Thread.sleep(3000);
		clickElement(plusBtn, "Plus Button");
		clickElement(plusBtn, "Plus Button");
		clickElement(minusBtn, "Minus Button");
		clickElement(addOrderBtn, "Add Order Button");
		Thread.sleep(3000);
		clickElement(reviewOrder, "Review Order Button");
		clickElement(codSubmit, "Submit Button");
		clickElement(codDone, "Done Button");

		// clickElement(account, "ACCOUNT");
		// clickElement(logOut, "LOGOUT");

		Reporter.log("MC COD Create is Completed", true);

	}

	/* 3. Farmer Accept the Order */

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_cta")
	public WebElement paylater;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_meet_collector")
	public WebElement meetmcbtn;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_primary")
	public WebElement acceptOrder;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_secondary")
	public WebElement cancelOrder;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/tv_total_price")
	public WebElement totalPrice;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_view_receipt")
	public WebElement viewreceipt;

	@FindBy(id = "android.widget.ImageButton")
	public WebElement backbtn;

	public void acceptOrder() throws Exception {

		clickElement(buyTab, "BUY TAB");
		waitUntilTextIsPresent("Good luck with your planting season!");
		clickElement(paylater, "Pay Later BTN");
		clickElement(meetmcbtn, "Meet MC BTN");

		if (elementAvailable(viewreceipt, "View Receipt") == true) {
			clickElement(viewreceipt, "View Receipt");
			sleep(5000);
			clickElement(backbtn, "Back Button");
			Reporter.log("Receipt is Viewed", true);
		} else {
			Reporter.log("Receipt is not Available to View", true);
		}
		sleep(5000);
		scrollAndClick("Accept order");
		Reporter.log("Order is Accepted", true);
	}

	/* 4. Shipment completed through Magento Portal */

	@FindBy(xpath = "//span[text()='Sales']")
	public WebElement salesTab;

	@FindBy(xpath = "//span[text()='Orders']")
	public WebElement ordersMenu;

	@FindBy(xpath = "//button[@class='action-tertiary action-clear']")
	public WebElement clearAll;

	@FindBy(xpath = "//*[@id=\"fulltext\"]")
	public WebElement searchInput;

	@FindBy(xpath = "//button[@class='action-default']")
	public WebElement clickFilter;

	@FindBy(xpath = "//span[text()='Apply Filters']")
	public WebElement applyFilter;

	@FindBy(xpath = "//a[text()='View']")
	public WebElement viewLink;

	@FindBy(id = "order_ship")
	public WebElement clickShip;

	@FindBy(xpath = "//button[@title='Submit Shipment']")
	public WebElement submitShip;

	@FindBy(xpath = "//span[@class='admin-user-account-text']")
	public WebElement logoutLink;

	@FindBy(xpath = "//a[@class='account-signout']")
	public WebElement logoutBtn;

	public void shipInputOrder(Menu menu) throws Exception {

		Reporter.log("Magento Shipment Started", true);

		clickElement(salesTab, "Sales Menu");
		clickElement(ordersMenu, "Orders Menu");
		waitUntilTextIsPresent("Orders");

		if (elementAvailable(clearAll, "Clear All")) {
			clickElement(clearAll, "Orders Menu");
			Reporter.log("Clear All clicked. Filters cleared");
		} else {
			Reporter.log("Filters are not Applied");
		}

		sleep(10000);

		SendKeys(searchInput, menu.getParameter3(), "FarmerSearch");
		// SendKeys(searchInput, "Autotest farm02.", "Search Input");
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(5000);
		clickElement(clickFilter, "Filter Clicked");
		sleep(3000);
		Select objSelect = new Select(driver.findElement(By.name("status")));
		objSelect.selectByVisibleText("Processing");
		sleep(3000);
		clickElement(applyFilter, "Apply Filter Clicked");
		sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-250)", "");
		clickElement(viewLink, "View Link clicked");
		clickElement(clickShip, "Ship Clicked");

		clickElement(submitShip, "Ship Submitted");

		clickElement(logoutLink, "Account");
		clickElement(logoutBtn, "Log Out");

		Reporter.log("Magento Shipment Completed", true);

	}

	/* 5. MC Receive the Order */

//	private static final By RECEIVE_ORDER = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new UiSelector().textContains(\"Receive order delivery from delivery partner for Autotest farm02\").instance(0))");

//	private static final By VIEW_ORDER = MobileBy.AndroidUIAutomator(
	// "new UiScrollable(new
	// UiSelector().resourceId(\"com.gaiaventure.mc:id/scrollView\")).scrollIntoView(new
	// UiSelector().textContains(\"View order details\").instance(0))");

//	private static final By VIEWORD_TOTAL = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Order total:\"));");

	@FindBy(id = "com.gaiaventure.mc:id/imgViewToolbarLeftIcon")
	public WebElement backNavigation;

	@FindBy(id = "com.gaiaventure.mc:id/tv_filled_btn_input_job_progress")
	public WebElement receiveSubmit;

	@FindBy(id = "com.gaiaventure.mc:id/dotLoaderBtnNext")
	public WebElement rcvSubmitNxt;

	@FindBy(id = "com.gaiaventure.mc:id/tvDoneButton")
	public WebElement doneBtn;

	// private static final By RCV_DONE =
	// MobileBy.AndroidUIAutomator("text(\"Done\")");

	public void mcOrderItemRcv(Menu menu) throws Exception {

		Reporter.log("MC Item Receive started", true);

		clickElement(taskScreen, "Tasks screen");
		clickElement(taskDropDown, "Task Drop Down");

		elementClick(driver, FARMINGREDIENT_OPTION, 10);
		scrollClickByContains("Receive order delivery from delivery partner for " + menu.getParameter3());

		// elementClick(driver, VIEW_ORDER, 10);
		// elementClick(driver, VIEWORD_TOTAL, 10);
		// clickElement(backNavigation, "Back Navigation");

		clickElement(receiveSubmit, "Receive Submit");
		clickElement(rcvSubmitNxt, "Submit Next Button");
		clickElement(doneBtn, "Done Button");

		// clickElement(account, "ACCOUNT");
		// clickElement(logOut, "LOGOUT");

		Reporter.log("MC COD ItemReceive Completed", true);

	}

	/* 6. MC Item Delivery for COD Order */

	// private static final By DLV_ORDER = MobileBy.AndroidUIAutomator("new
	// UiScrollable(new
	// UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new
	// UiSelector().textContains(\"Collect cash for order from\").instance(0))");

	private static final By CONFIRM_ORDER = MobileBy
			.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Confirm\"));");

	public void mccodItemDelv() throws Exception {

		Reporter.log("MC COD Item Delivery Started", true);

		clickElement(taskScreen, "Tasks Screen");
		clickElement(taskDropDown, "Task Drop Down");
		elementClick(driver, FARMINGREDIENT_OPTION, 10);
		scrollClickByContains("Collect cash for order from");
		// elementClick(driver, DLV_ORDER, 10);
		elementClick(driver, CONFIRM_ORDER, 10);
		clickElement(codDone, "Done Button");

		Reporter.log("MC COD Item Delivery Completed", true);

		// clickElement(account, "ACCOUNT");
		// clickElement(logOut, "LOGOUT");

	}

	/* 7. MC Receives the Item and Send Delivery Request to Farmer */

	@FindBy(id = "com.gaiaventure.mc:id/tvInputJobProgressTitle")
	public WebElement delivery_SuccessMessage1;

	@FindBy(id = "com.gaiaventure.mc:id/tvInputJobProgressDesc")
	public WebElement delivery_SuccessMessage2;

	@FindBy(id = "com.gaiaventure.mc:id/tv_hollow_btn_input_job_progress")
	public WebElement doneButton;

	/*
	 * private static final By receiveDelivery = MobileBy.AndroidUIAutomator(
	 * "new UiScrollable(new UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new UiSelector().textContains(\"Receive order delivery from delivery partner for "
	 * + EnvironmentManager.getFarmerName() + "\").instance(0))");
	 * 
	 * private static final By GIVEORDER = MobileBy.AndroidUIAutomator(
	 * "new UiScrollable(new UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new UiSelector().textContains(\"Give the order to the farmer "
	 * + EnvironmentManager.getFarmerName() + "\").instance(0))");
	 */

	public void mcloanItemReceive(Menu menu) {
		clickElement(taskScreen, "Task Screen");
		clickElement(taskDropDown, "Task Drop Down");
		elementClick(driver, FARMINGREDIENT_OPTION, 10);
		scrollClickByContains("Receive order delivery from delivery partner for " + menu.getParameter3());
		// elementClick(driver, receiveDelivery, 10);
		waitUntilTextIsPresent("Receive delivery");
		scrollAndClick("Confirm order delivery");
		waitUntilTextIsPresent("Confirm the order received");
		clickElement(codSubmit, "Submit Button");
		// getText(successMessage1, "");
		// getText(successMessage2, "");
		clickElement(doneBtn, "DONEBUTTON");
		// elementClick(driver, GIVEORDER, 10);
		scrollClickByContains("Give the order to the farmer " + menu.getParameter3());
		waitUntilTextIsPresent("Give the order to the farmer");
		scrollAndClick("Enter code");

	}

	/* 8. Farmer Accept the Delivery and get the Delivery Code */

	@FindBy(id = "com.gaiaventure.advisor.qa:id/fragment_sell")
	public WebElement SellTab;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_confirm_harvest")
	public WebElement getSecurityCode;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/et_cash_amount")
	public WebElement amountField;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_next")
	public WebElement nextButton;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/tv_security_code")
	public WebElement securityCode;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_ok")
	public WebElement securityCode_okButton;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_done")
	public WebElement fdoneButton;

	protected static String code;

	public void acceptDelivery() {
		clickElement(buyTab, "Buy");
		scrollAndClick("Accept delivery");
		setCode(securityCode.getText());
		Reporter.log("Security Code is " + getCode(), true);
		clickElement(securityCode_okButton, "OKButton");

	}

	public static String getCode() {
		return code;
	}

	public static void setCode(String code) {
		InputFlowPage.code = code;
	}

	/*
	 * 9. MC Enter Delivery Code from Farmer and complete the Delivery for Loan Order
	 */

	@FindBy(id = "com.gaiaventure.mc:id/etvEnterCode")
	public WebElement enterCodeField;

	@FindBy(id = "com.gaiaventure.mc:id/dotLoaderBtnNext")
	public WebElement nextBtn;

	@FindBy(id = "com.gaiaventure.mc:id/etvEnterCode")
	public WebElement confirmationcode;

	@FindBy(id = "com.gaiaventure.mc:id/snackbar_text")
	public WebElement snackbar_text;

	@FindBy(id = "com.gaiaventure.mc:id/tvNotificationTitle")
	public WebElement successMessage1;

	@FindBy(id = "com.gaiaventure.mc:id/tvNotificationMsg")
	public WebElement successMessage2;

	// private static final By ORDERDELIVERY = MobileBy.AndroidUIAutomator("new
	// UiScrollable(new
	// UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new
	// UiSelector().textContains(\"Pending delivery confirmation from "
	// + EnvironmentManager.getFarmerName() + "\").instance(0))");

	public void mcEnterDeliveryCode(Menu menu) {
		clickElement(taskScreen, "Task Screen");
		clickElement(taskDropDown, "Task Drop Down");
		elementClick(driver, FARMINGREDIENT_OPTION, 10);
		scrollClickByContains("Pending delivery confirmation from " + menu.getParameter3());
		// elementClick(driver, ORDERDELIVERY, 10);
		// scrollAndClick("Pending delivery confirmation from " +
		// EnvironmentManager.getFarmerName());
		scrollAndClick("Enter code");
		SendKeys(confirmationcode, InputFlowPage.getCode(), "CONFIRMATIONCODE");
		clickElement(nextBtn, "NEXT_BTN");
		if (elementAvailable(successMessage1, "SUCCESSMESSSAGE")) {
			getText(successMessage1, "");
			getText(successMessage2, "");
			clickElement(doneBtn, "DONEBUTTON");
		} else if (elementAvailable(snackbar_text, "ERRORMESSAGE")) {
			Reporter.log(snackbar_text.getText(), true);
			Assert.assertFalse(true);
		} else {
			Reporter.log("Something went wrong", true);
		}

	}

	/* 10. Farmer View the Receipts/Loan Status */

	@FindBy(id = "com.gaiaventure.advisor.qa:id/tv_credit_title")
	public WebElement loanstatus;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/tv_credit_body")
	public WebElement statusmessage;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/layout_loan_details")
	public WebElement loanamount;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/tv_loan_remaining")
	public WebElement amountremaining;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/tv_loan_amount")
	public WebElement totalamount;

	@FindBy(id = "com.gaiaventure.advisor.qa:id/btn_credit")
	public WebElement loanbtn;

	public void viewResult() {

		Reporter.log("Farmer View Results is started", true);
		clickElement(buyTab, "BUY TAB");
		waitUntilTextIsPresent("Good luck with your planting season!");
		clickElement(paylater, "Pay Later BTN");
		clickElement(meetmcbtn, "Meet MC BTN");
		if (elementAvailable(viewreceipt, "View Receipt") == true) {
			clickElement(viewreceipt, "View Receipt");
			clickElement(backbtn, "Back Button");
			Reporter.log("Receipt is Viewed", true);
		} else {
			Reporter.log("Receipt is not Available to View", true);
		}

		if (elementAvailable(loanstatus, "Loan Status") == true) {
			String loanStatus = getText(statusmessage, "Loan status Message");
			Reporter.log("Loan Status Message ->>" + loanStatus, true);

		} else {
			Reporter.log("Loan card is Not Available To View", true);
		}

		if (elementAvailable(loanamount, "Loan Amount") == true) {
			scrollAndTextMatchView("Amount remaining");
			String remainingAmount = getText(amountremaining, "Amount Remaining");
			String totalAmount = getText(totalamount, "Loan status Message");
			Reporter.log("Loan Remaining Amount ->>" + remainingAmount, true);
			Reporter.log("Loan Total Loan Amount ->>" + totalAmount, true);

		} else {
			Reporter.log("Loan card is Not Available To View", true);
		}

	}

	/* 11. View and Verify the MC Commission */

	@FindBy(id = "com.gaiaventure.mc:id/navigation_payment")
	public WebElement paymentTab;

	@FindBy(id = "com.gaiaventure.mc:id/tvAmountonLoan")
	public WebElement amountOnLoan;

	@FindBy(id = "com.gaiaventure.mc:id/tvEarned")
	public WebElement commissionEarned_Loan;

	@FindBy(id = "com.gaiaventure.mc:id/tvAmountonLoanCash")
	public WebElement amountOnCash;

	@FindBy(id = "com.gaiaventure.mc:id/tvEarnedCash")
	public WebElement commissionEarned_Cash;

	public void calculateCommission(WebElement amountOn, WebElement commissionAmt, int loanPer, String commissionName) {
		String amount = amountOn.getText();
		Reporter.log("Total amount is " + amount, true);
		String loanSplit = (amount.substring(0, amount.lastIndexOf(',', amount.length() - 2)).replaceAll("\\D+", ""));
		int convertToInt = Integer.parseInt(loanSplit);
		int percentage = convertToInt / 100 * loanPer;
		String finalCommission = Integer.toString(percentage);
		Reporter.log("MC Commission amount should be " + finalCommission, true);

		String cAmount = commissionAmt.getText();
		String cSplit = (cAmount.substring(0, cAmount.lastIndexOf(',', cAmount.length() - 2)).replaceAll("\\D+", ""));
		Reporter.log("Commission Earned by MC is " + cSplit, true);
		if (finalCommission.equals(cSplit)) {
			Reporter.log(commissionName + " is correct " + cSplit, true);
		} else {
			Reporter.log(commissionName + " is not correct " + cSplit, true);
		}

	}

	public void mcCommission() {
		clickElement(paymentTab, "PaymentTab");
		scrollPage("Credit given to farmers");
		Reporter.log("**********************************************************", true);
		calculateCommission(amountOnLoan, commissionEarned_Loan, 2, "LoanCommision");
		Reporter.log("**********************************************************", true);
		// scrollPage("Amount on cash:");
		scrollPage("Inputs sold");
		calculateCommission(amountOnCash, commissionEarned_Cash, 1, "CashCommision");

		/*
		 * getText(amountOnLoan, "LoanAmount"); String loanAmt = amountOnLoan.getText();
		 * String lAmount = (loanAmt.substring(0, loanAmt.lastIndexOf(',',
		 * loanAmt.length() - 2)) .replaceAll("\\D+", ""));
		 * 
		 * int per = Integer.parseInt(lAmount); int loanPercentage = per / 100 * 2;
		 * String finalLoanamt = Integer.toString(loanPercentage);
		 * Reporter.log("Loan Commission should be " + finalLoanamt,true);
		 * 
		 * String commAmount = commissionEarned.getText(); String cAmount =
		 * (commAmount.substring(0, commAmount.lastIndexOf(',', commAmount.length() -
		 * 2)) .replaceAll("\\D+", "")); Reporter.log("Loan Commission Earned is " +
		 * cAmount,true);
		 * 
		 * if (finalLoanamt.equals(cAmount)) {
		 * Reporter.log("Loan commission is correct " + cAmount, true); } else {
		 * Reporter.log("Loan commission is not correct " + cAmount, true); }
		 */

	}

	/* 12. MC Rejects the Item Request from Farmer */

	// private static final By ORDER_INGREDIENTS = MobileBy.AndroidUIAutomator(
	// "new UiScrollable(new
	// UiSelector().resourceId(\"com.gaiaventure.mc:id/container\")).scrollIntoView(new
	// UiSelector().textContains(\"Order farming ingredients\").instance(0))");

	@FindBy(id = "com.gaiaventure.mc:id/tvToolbarRightButton")
	public WebElement rejectBtn;

	private static final By REJECT_REASON = MobileBy.AndroidUIAutomator("text(\"I have too many tasks\")");

	@FindBy(id = "com.gaiaventure.mc:id/tvBtnCancelTask")
	public WebElement cancelBtn;

	@FindBy(id = "com.gaiaventure.mc:id/dialogBtnOk")
	public WebElement okBtn;

	public void rejectItemReq(Menu menu) throws Exception {

		Reporter.log("MC COD Reject started", true);

		clickElement(taskScreen, "Farmer screen");
		clickElement(taskDropDown, "Farmer screen");
		elementClick(driver, FARMINGREDIENT_OPTION, 10);
		// elementClick(driver, ORDER_INGREDIENTS, 10);
		scrollClickByContains("Order farming ingredients " + menu.getParameter3());
		clickElement(rejectBtn, "Farmer screen");
		elementClick(driver, REJECT_REASON, 10);
		clickElement(cancelBtn, "Farmer screen");
		clickElement(okBtn, "Farmer screen");
		
		// clickElement(account, "Account");
		// clickElement(logOut, "LogOut");

		Reporter.log("MC COD Reject Completed", true);

	}

}
