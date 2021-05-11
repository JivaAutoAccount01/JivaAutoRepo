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

public class JLActivitiesTest extends ElementManager {

	private String userStoryDescription;
	private String successMsgDesc = "";
	private String failMsgDesc = "";

	@Parameters({ "browser" })
	@BeforeClass
	public void setup(String browser) throws IOException, InterruptedException {
		stopEmulator();
		startTabEmulator();
		sleep(15000);
		driver = getDriver(DriverType.valueOf(browser));
	}

	@AfterClass
	public void tearDown() throws IOException, InterruptedException {
		stopTabEmulator();
		driver.close();
	}

	@Test
	public void newMCCreate() throws Exception {

		try {

			try {
				userStoryDescription = "JL - Login";
				logUserStoryStart(userStoryDescription, "JL - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.JLLOGIN);
				loginPages.jlLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}
			try {
				userStoryDescription = "JL - Create New MC";
				logUserStoryStart(userStoryDescription, "Create New MC through JL App");

				jlactivitespage.createMC();

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
	public void jlreassignRejectedTask() throws Exception {

		try {

			try {
				userStoryDescription = "JL - Login";
				logUserStoryStart(userStoryDescription, "JL - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.JLLOGIN);
				loginPages.jlLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}
			try {
				userStoryDescription = "JL - Create New MC";
				logUserStoryStart(userStoryDescription, "Create New MC through JL App");

				jlactivitespage.reassignRejectedTask();

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
	public void jlreassignOverdueTask() throws Exception {

		try {

			try {
				userStoryDescription = "JL - Login";
				logUserStoryStart(userStoryDescription, "JL - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.JLLOGIN);
				loginPages.jlLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}
			try {
				userStoryDescription = "JL - Create New MC";
				logUserStoryStart(userStoryDescription, "Create New MC through JL App");

				jlactivitespage.reassignOverdueTask();

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