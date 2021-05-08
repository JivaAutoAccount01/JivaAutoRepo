package adminportaltestcases;

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

public class SetMCCommissionTest extends ElementManager {

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
	public void setMCCommission() throws Exception {

		try {

			try {
				userStoryDescription = "Jiva Web Portal - Login";
				logUserStoryStart(userStoryDescription, "Farmer CRM - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.WEBPORTALLOGIN);
				loginPages.jivaPortalLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}
			
			try {
				userStoryDescription = "Jiva Web Portal - Set MC Commission";
				logUserStoryStart(userStoryDescription, "Set MC Commission");

				setmccommission.setMCCommission();

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