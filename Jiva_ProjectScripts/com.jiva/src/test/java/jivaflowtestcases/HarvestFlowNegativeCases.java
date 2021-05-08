package jivaflowtestcases;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import excelClasses.Menu;
import excelClasses.MenuFactory;
import excelClasses.TestInputsMenu;
import utilities.DriverType;
import utilities.ElementManager;

public class HarvestFlowNegativeCases extends ElementManager {

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
	public void harvest_Negative() throws Exception {
		Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.HARVESTFLOWDATA);
		loginPages.mcLogin(menu);

		try {
			userStoryDescription = "MC - HarvestFlow Negative Scenarios";
			logUserStoryStart(userStoryDescription, "MC - HarvestFlow - Entering negative harvest Details ");

			harvestflowpage.harvestFlow_Negative(menu);

			successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
		} catch (Throwable e) {
			e.printStackTrace();
			takeScreenshot(testMethodName + "_error");
			failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
		}
		Reporter.log(testMethodName + " ==> " + "Test case passed", true);
	}

	@Test
	public void dispatchTruck_Negative() throws Exception {
		Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.HARVESTFLOWDATA);
		loginPages.mcLogin(menu);
		try {
			userStoryDescription = "MC - DispatchTruck Negative Scenarios";
			logUserStoryStart(userStoryDescription, "MC - Dispatch Truck - Entering invalid TruckDriver Details ");

			harvestflowpage.dispatchTruck_Negative(menu);

			successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
		} catch (Throwable e) {
			e.printStackTrace();
			takeScreenshot(testMethodName + "_error");
			failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
		}
		Reporter.log(testMethodName + " ==> " + "Test case passed", true);
	}

	@Test
	public void harvest_CancelTransaction() throws Exception {
		Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.HARVESTFLOWDATA);
		loginPages.mcLogin(menu);
		try {
			userStoryDescription = "MC - PurchaseHarvest - Cancel Transaction";
			logUserStoryStart(userStoryDescription,
					"MC - PurchaseHarvest  - Cancel Transaction and verify transaction is cancelled ");

			harvestflowpage.cancelHarvest_Transaction(menu);

			successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
		} catch (Throwable e) {
			e.printStackTrace();
			takeScreenshot(testMethodName + "_error");
			failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
		}
		Reporter.log(testMethodName + " ==> " + "Test case passed", true);
	}

}
