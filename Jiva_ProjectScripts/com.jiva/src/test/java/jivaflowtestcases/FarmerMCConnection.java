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

public class FarmerMCConnection extends ElementManager {

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
		public void farmerMCConnectionReq() throws Exception {

			try {

				try {
					userStoryDescription = "Farmer App - Login";
					logUserStoryStart(userStoryDescription, "Farmer App - Login");

					Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.FARMERLOGIN);
					loginPages.farmerLogin(menu);

					successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
				} catch (Throwable e) {
					e.printStackTrace();
					failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
				}

				try {
					userStoryDescription = "Farmer Send Request To MC for Connection";
					logUserStoryStart(userStoryDescription, "Farmer Send Request To MC for Connection");

					fmcconpage.newFarmerCon();

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
		public void acceptFconnectionMC() {
			
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
				userStoryDescription = "MC Accepts Farmer Connection Request";
				logUserStoryStart(userStoryDescription, "MC Accepts Farmer Connection Request");
				
				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.NEWFARMERLOGIN);
				fmcconpage.mcAcceptConnRequest(menu);

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
