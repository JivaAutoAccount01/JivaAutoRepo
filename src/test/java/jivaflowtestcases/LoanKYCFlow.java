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

public class LoanKYCFlow extends ElementManager {

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
	public void farmerLoanApply() throws Exception {

		try {

			try {
				userStoryDescription = "Farmer - Login";
				logUserStoryStart(userStoryDescription, "Farmer - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.KYCFLOWDATA);
				loginPages.farmerLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "Farmer- Apply Loan";
				logUserStoryStart(userStoryDescription, "Farmer- Send Loan Apply Request to MC");

				loankycflowpage.fapplyLoan();

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
	public void mcSubmitKYCDocs() throws Exception {

		try {

			try {
				userStoryDescription = "MC - Login";
				logUserStoryStart(userStoryDescription, "MC - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.MCLOGIN);
				loginPages.mcLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "MC - Enter Details, Upload and Send the KYC Docs";
				logUserStoryStart(userStoryDescription, "MC - Enter Details, Upload and Send the KYC Docs");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.KYCFLOWDATA);
				loankycflowpage.mcSubmitKYC(menu);

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
	public void farmerCRMApproval() throws Exception {

		try {

			try {
				userStoryDescription = "FarmerCRM - WebPortal Login";
				logUserStoryStart(userStoryDescription, "FarmerCRM - WebPortal Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.FARMERCRMLOGIN);
				loginPages.farmercrmLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "FarmerCRM - Loan Approval";
				logUserStoryStart(userStoryDescription, "FarmerCRM - Loan Approval");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.KYCFLOWDATA);
				loankycflowpage.loanApproval(menu);

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
	public void magentoLoanAmountSubmit() {
		try {
			try {
				userStoryDescription = "Magento - Login";
				logUserStoryStart(userStoryDescription, "Magento - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.MAGENTOLOGIN);
				loginPages.magentoLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "Magento WebPortal - Approve & Submit the Loan Amount";
				logUserStoryStart(userStoryDescription, "Magento WebPortal - Approve & Submit the Loan Amount");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.KYCFLOWDATA);
				loankycflowpage.magentoLoanSubmit(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				takeScreenshot(testMethodName + "_error");
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}
			Reporter.log(testMethodName + " ==> " + "Test case passed", true);

		} catch (Throwable e) {
			displayExecutionResults(successMsgDesc, failMsgDesc);
		}
		displayExecutionResults(successMsgDesc, failMsgDesc);
	}

	@Test
	public void farmerViewLoanResult() throws Exception {

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
				userStoryDescription = "Farmer View the Loan Results";
				logUserStoryStart(userStoryDescription, "Farmer View the Loan Results");

			
				loankycflowpage.viewLoanResults();

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
