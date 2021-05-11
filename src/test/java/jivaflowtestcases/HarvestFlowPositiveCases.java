package jivaflowtestcases;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import excelClasses.Menu;
import excelClasses.MenuFactory;
import excelClasses.TestInputsMenu;
import utilities.DriverType;
import utilities.ElementManager;

public class HarvestFlowPositiveCases extends ElementManager {

	private String userStoryDescription;
	private String successMsgDesc = "";
	private String failMsgDesc = "";

	@Parameters({ "browser" })
	@BeforeClass
	public void setup(String browser) throws IOException, InterruptedException {
		driver = getDriver(DriverType.valueOf(browser));
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	@Test
	public void enterHarvestDetails() throws Exception {

		try {

			try {
				userStoryDescription = "MC - Login";
				logUserStoryStart(userStoryDescription, "MC App - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.MCLOGIN);
				loginPages.mcLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "MC - Purchase Harvest";
				logUserStoryStart(userStoryDescription, "MC - Purchase Harvest for farmer");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.HARVESTFLOWDATA);

				harvestflowpage.enter_HarvestDetails(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}
		} catch (Throwable e) {
			displayExecutionResults(successMsgDesc, failMsgDesc);
		}
		displayExecutionResults(successMsgDesc, failMsgDesc);
	}

	@Test
	public void farmerGetCode() throws Exception {

		try {

			try {
				userStoryDescription = "Farmer App - Login";
				logUserStoryStart(userStoryDescription, "Farmer App Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.FARMERLOGIN);
				loginPages.farmerLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "Farmer - Getcode";
				logUserStoryStart(userStoryDescription, "Farmer - Getcode");

				harvestflowpage.enterAmount_GetCode();

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}
		} catch (Throwable e) {
			displayExecutionResults(successMsgDesc, failMsgDesc);
		}
		displayExecutionResults(successMsgDesc, failMsgDesc);
	}

	@Test
	public void mcCompleteHarvest() throws Exception {

		try {

			try {
				userStoryDescription = "MC App - Login";
				logUserStoryStart(userStoryDescription, "MC App - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.MCLOGIN);
				loginPages.mcLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "MC - Applycode";
				logUserStoryStart(userStoryDescription, "MC - Applycode and complete purchase");

				harvestflowpage.paste_SecurityCode();

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "MC - DispatchTruck";
				logUserStoryStart(userStoryDescription, "MC - DispatchTruck");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.HARVESTFLOWDATA);
				harvestflowpage.dispatchTruck(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}
		} catch (Throwable e) {
			displayExecutionResults(successMsgDesc, failMsgDesc);
		}
		displayExecutionResults(successMsgDesc, failMsgDesc);
	}

	@Test
	public void mcHarvestCommissionChk() throws Exception {

		try {

			try {
				userStoryDescription = "MC App Login";
				logUserStoryStart(userStoryDescription, "MC App Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.MCLOGIN);
				loginPages.mcLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "MC - Calculate HarvestCommission";
				logUserStoryStart(userStoryDescription, "MC - Calculate HarvestCommission");

				harvestflowpage.harvestCommission();

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

		} catch (Throwable e) {
			displayExecutionResults(successMsgDesc, failMsgDesc);
		}
		displayExecutionResults(successMsgDesc, failMsgDesc);

	}

}
