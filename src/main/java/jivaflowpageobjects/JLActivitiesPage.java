package jivaflowpageobjects;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import excelClasses.ExcelUtils;
import utilities.ElementManager;
import utilities.EnvironmentManager;

public class JLActivitiesPage extends ElementManager {

	@FindBy(id = "btnNext")
	public WebElement Next_btn;

	@FindBy(id = "editTextphonenumber")
	public WebElement phNo_edit;

	@FindBy(id = "llyButtonWithLoaderParent")
	public WebElement PHNext_btn;

	@FindBy(id = "editTextsms")
	public WebElement sms_edit;

	@FindBy(xpath = "//android.widget.TextView[@text='Jiva Mitras']")
	public WebElement landing_screen;

	@FindBy(id = "btn_add_new_jiva_mitra")
	public WebElement add_newMC;

	@FindBy(id = "edittextname")
	public WebElement name_field;

	@FindBy(id = "acTextCountry")
	public WebElement country_DD;

	@FindBy(xpath = "//android.widget.TextView[@text='Indonesia']")
	public WebElement country_Name;

	@FindBy(id = "editTextphonenember")
	public WebElement mobile_No;

	@FindBy(id = "editTextAddress")
	public WebElement address;

	@FindBy(id = "acTextProvince")
	public WebElement province_DD;

	@FindBy(xpath = "//android.widget.TextView[@text='Lampung']")
	public WebElement province_Name;

	@FindBy(id = "acTextcity")
	public WebElement city_DD;

	@FindBy(xpath = "//android.widget.TextView[@text='Lampung tengah']")
	public WebElement city_name;

	@FindBy(id = "acSubDistrict")
	public WebElement district_DD;

	@FindBy(xpath = "//android.widget.TextView[@text='Kalirejo']")
	public WebElement district_name;

	@FindBy(id = "acTextvillage")
	public WebElement village_DD;

	@FindBy(xpath = "//android.widget.TextView[@text='Kalidadi']\"")
	public WebElement village_name;

	@FindBy(id = "spinnerBank")
	public WebElement bank_DD;

	@FindBy(xpath = "//android.widget.TextView[@text='ICICI BANK']\"")
	public WebElement bank_name;

	@FindBy(id = "editTextbank")
	public WebElement account_no;

	@FindBy(id = "edittextcurrentdebt")
	public WebElement debt_amt;

	@FindBy(xpath = "//android.widget.TextView[@text='Next']")
	public WebElement next;

	@FindBy(id = "com.gaiaventure.jl:id/snackbar_text\"")
	public WebElement error_msg;

	@FindBy(id = "grant_dialog")
	public WebElement dialog_window;

	// Access photos elements

	@FindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	public WebElement take_pictures;

	@FindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	public WebElement allow_accessphotos;

	@FindBy(id = "com.gaiaventure.jl:id/gallaryclick")
	public WebElement select_gallery;

	@FindBy(id = "com.google.android.apps.photos:id/image")
	public WebElement image;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\\\"Photo taken on Feb 11, 2021 10:46:41 AM\\\"]\\r\\n\" + \"")
	public WebElement photo_name;

	@FindBy(id = "com.gaiaventure.jl:id/menu_crop")
	public WebElement crop_image;

	@FindBy(id = "com.gaiaventure.jl:id/tvDotLoaderText")
	public WebElement image_nxtbutton;

	@FindBy(id = "title_KTP_info")
	public WebElement success_window;

	@FindBy(id = "com.gaiaventure.jl:id/dialogBtn")
	public WebElement success_done_btn;

	@FindBy(id = "titleMitraNameWithTxt")
	public WebElement success_msg;

	@FindBy(id = "txtTab2")
	public WebElement notasks_tab;

	@FindBy(id = "txtMcName")
	public WebElement mcnames;

	// Reassign Tasks Objects

	@FindBy(id = "txtTab2")
	public WebElement rejectedtasks_tab;

	@FindBy(id = "com.gaiaventure.jl:id/txtMcName")
	public WebElement taskname;

	@FindBy(id = "com.gaiaventure.jl:id/txtStageInfo")
	public WebElement placeorder_btn;

	@FindBy(id = "com.gaiaventure.jl:id/btnEditDetails")
	public WebElement assign_btn;

	@FindBy(id = "com.gaiaventure.jl:id/tvMcName")
	public WebElement mcname;

	@FindBy(id = "com.gaiaventure.jl:id/btnSelect")
	public WebElement select_button;

	@FindBy(id = "com.gaiaventure.jl:id/titleMitraNameWithTxt")
	public WebElement msg;

	@FindBy(id = "com.gaiaventure.jl:id/dialogBtn")
	public WebElement done_btn;

	private static final By NAME_FIELD = By.id("edittextname");
	private static final By MOBILENUMBER = By.id("editTextphonenember");

	static ExcelUtils excelUtils = new ExcelUtils();
	static String excelFilePath = EnvironmentManager.getExcelPath(TESTDATAPATH_PROPERTY);

	Random random = new Random();
	int x = random.nextInt(500000000);
	String mobilenumber = "+912" + x;

	// Random name
	int y = random.nextInt(50);
	String UserName = "TestingJL" + y;

	public JLActivitiesPage(WebDriver driver) {
		this.driver = driver;
	}

	public void createMC() throws Exception {

		Reporter.log("MC Create started", true);

		clickElement(landing_screen, " LANDINGSCREEN");
		clickElement(add_newMC, "ADDNEWMC");
		elementSendKeys(driver, NAME_FIELD, UserName, 10);
		clickElement(country_DD, "COUNTRY_DD");
		clickElement(country_Name, "COUNTRY_NAME");

		elementSendKeys(driver, MOBILENUMBER, mobilenumber, 10);
		SendKeys(address, "Indonesia", "ADDRESS");

		clickElement(province_DD, "PROVINCE_DD");
		clickElement(province_Name, "PROVINCE_NAME");

		clickElement(city_DD, "CITY_DD");
		clickElement(city_name, "CITY_NAME");
		scrollAndClick("Bank");

		clickElement(district_DD, "DISTRICT_DD");
		clickElement(district_name, "DISTRICT_NAME");

		clickElement(village_DD, "VILLAGE_DD");
		clickElement(village_name, "VILLAGE_NAME");
		clickElement(bank_DD, "BANK_DD");
		clickElement(bank_name, "BANK_NAME");

		SendKeys(account_no, "12345678", "AccountNo");
		scrollAndClick("Rp");
		SendKeys(debt_amt, "100", "DEBT_AMOUNT");
		scrollAndClick("Next");

		if (elementAvailable(error_msg, "ERROR_MSG") == true) {

			clickElement(take_pictures, "ONLYTHISTIME_TAKEPICTURES");
			clickElement(allow_accessphotos, "ALLOW_ACCESSPHOTOS");
			clickElement(select_gallery, "SELECTGALLERY");
			clickElement(image, "IMAGE");
			clickElement(photo_name, "PHOTONAME");
			clickElement(crop_image, "CROPIMAGE");
			clickElement(image_nxtbutton, "IMAGE_NEXTBTN");

			clickElement(select_gallery, "SELECTGALLERY");
			clickElement(image, "IMAGE");
			clickElement(photo_name, "PHOTONAME");
			clickElement(crop_image, "CROPIMAGE");
			clickElement(image_nxtbutton, "IMAGE_NEXTBTN");

			// Upload Profile Photo
			clickElement(select_gallery, "SELECTGALLERY");
			clickElement(image, "IMAGE");
			clickElement(photo_name, "PHOTONAME");
			clickElement(crop_image, "CROPIMAGE");
			clickElement(image_nxtbutton, "IMAGE_NEXTBTN");

			getText(success_msg, " SUCCESSMESSAGE");
			clickElement(success_done_btn, "SUCCESS_DONEBTN");
			clickElement(landing_screen, "LANDINGSCREEN");
			clickElement(notasks_tab, "NOTASK_TAB");

			if (scrollandCheck(UserName) == true) {
				Reporter.log("MC is added successfully", true);

			} else {
				Reporter.log("MC is not added successfully", true);
				Assert.assertFalse(true);
			}
		} else {
			Reporter.log("Sorry, we can�t process your request right now. Please try again, later.", true);
			Assert.assertFalse(true);
		}
	}

	public void reassignRejectedTask() throws InterruptedException, IOException {

		Reporter.log("Reassign Rejected Task started", true);

		clickElement(rejectedtasks_tab, "REJECTEDTASK_TAB");
		clickFirstElement();
		clickElement(placeorder_btn, "PLACEORDER_BTN");
		clickElement(assign_btn, "ASSIGN_BTN");
		clickFirstElement();
		clickElement(mcname, "MCNAME");
		clickElement(select_button, "SELECTBUTTON");
		getText(msg, "MESSSAGE");
		clickElement(done_btn, "DONE_BUTTON");

		Reporter.log("Reassign Rejected Task completed", true);
	}

	public void reassignOverdueTask() throws InterruptedException, IOException {

		Reporter.log("Reassign Overdue Task started", true);
		clickFirstElement();
		clickElement(placeorder_btn, "PLACEORDER_BTN");
		clickElement(assign_btn, "ASSIGN_BTN");
		clickFirstElement();
		clickElement(mcname, "MCNAME");
		clickElement(select_button, "SELECTBUTTON");
		getText(msg, "MESSSAGE");
		clickElement(done_btn, "DONE_BUTTON");

		Reporter.log("Reassign Overdue Task completed", true);
	}

}
