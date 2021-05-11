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

public class InputFlow extends ElementManager {

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
	public void farmerRequestItem() throws Exception {

		try {

			try {
				userStoryDescription = "Farmer - Login";
				logUserStoryStart(userStoryDescription, "Farmer - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.FARMERLOGIN);
				loginPages.farmerLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "Farmer- Request Item";
				logUserStoryStart(userStoryDescription, "Farmer- Request Item to assigned MC");

				inputflowpage.fOrdItemRequest();

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
	public void mcOrderCreateCOD() throws Exception {

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
				userStoryDescription = "MC- Create COD Order";
				logUserStoryStart(userStoryDescription, "MC-Accept Farmer Request and place COD Order");

				inputflowpage.mcCODPurchase();

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
	public void mcLoanOrderCreate() throws Exception {

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
				userStoryDescription = "MC- Create Order on Loan";
				logUserStoryStart(userStoryDescription, "MC-Accept Farmer Request and place Order through Loan");

				inputflowpage.mccreateOrdOnLoan();

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
	public void farmerAcceptOrder() {
		try {
			try {
				userStoryDescription = "Farmer - Login";
				logUserStoryStart(userStoryDescription, "Farmer - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.FARMERLOGIN);
				loginPages.farmerLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "Farmer - AcceptOrder";
				logUserStoryStart(userStoryDescription, "Farmer - Accept the order created by MC");

				inputflowpage.acceptOrder();

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
	public void magentoShipment() throws Exception {

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
				userStoryDescription = "Magento - Ordered Item Shipment";
				logUserStoryStart(userStoryDescription, "Magento - Search Farmer and submit the Shipment");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.INPUTFLOWDATA);
				inputflowpage.shipInputOrder(menu);

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
	public void mcCODItemReceive() throws Exception {

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
				userStoryDescription = "MC- Order Item Receive";
				logUserStoryStart(userStoryDescription, "MC- Receive the Ordered Items");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.INPUTFLOWDATA);
				inputflowpage.mcOrderItemRcv(menu);

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
	public void mcCodOrderItemDelv() throws Exception {

		try {

			try {
				userStoryDescription = "Magento - Login";
				logUserStoryStart(userStoryDescription, "Magento - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.MCLOGIN);
				loginPages.mcLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "MC- Deliver the COD - Ordered Item to Farmer";
				logUserStoryStart(userStoryDescription, "MC- Deliver the Ordered Item to Farmer");

				inputflowpage.mccodItemDelv();

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
	public void mcLoanItemRcv() throws Exception {

		try {

			try {
				userStoryDescription = "Magento - Login";
				logUserStoryStart(userStoryDescription, "Magento - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.MCLOGIN);
				loginPages.mcLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "MC Loan Order- Receive the Ordered Item and send the Delivery Code";
				logUserStoryStart(userStoryDescription,
						"MC Receive the Items & Intiate the Item Delivery Process for Loan Order");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.INPUTFLOWDATA);
				inputflowpage.mcloanItemReceive(menu);

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
	public void farmerAcceptDelivery() {

		try {

			try {
				userStoryDescription = "Farmer - Login";
				logUserStoryStart(userStoryDescription, "Farmer - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.FARMERLOGIN);
				loginPages.farmerLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "Farmer Accept the Delivery and Get SecurityCode";
				logUserStoryStart(userStoryDescription, "Farmer -Accept Delivery & Get SecurityCode");

				inputflowpage.acceptDelivery();

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
	public void mcLoanOrderDelivery() {

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
				userStoryDescription = "MC - Use the Delivery Code from Farmer and Complete the Item Delivery";
				logUserStoryStart(userStoryDescription,
						"MC - Use the Delivery Code from Farmer and Complete the Item Delivery");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.INPUTFLOWDATA);
				inputflowpage.mcEnterDeliveryCode(menu);

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
	public void farmer_ViewResults() {

		try {

			try {
				userStoryDescription = "Farmer - Login";
				logUserStoryStart(userStoryDescription, "Farmer - Login");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.FARMERLOGIN);
				loginPages.mcLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "Farmer - View the Receipts";
				logUserStoryStart(userStoryDescription, "Farmer - View the Receipts");

				inputflowpage.viewResult();

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
	public void mcCommision() {

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
				userStoryDescription = "MC- CalculateCommision";
				logUserStoryStart(userStoryDescription, "MC-Calculate the commission percentage earned by MC");

				inputflowpage.mcCommission();

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
	public void mcRejectItemRequest() throws Exception {

		try {

			try {
				userStoryDescription = "MC- Reject the Farmer Request";
				logUserStoryStart(userStoryDescription, "MC Reject the Order Request From Farmer");

				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.MCLOGIN);
				loginPages.mcLogin(menu);

				successMsgDesc = logUserStoryEndSuccess(userStoryDescription, successMsgDesc);
			} catch (Throwable e) {
				e.printStackTrace();
				failMsgDesc = logUserStoryEndFail(userStoryDescription, failMsgDesc);
			}

			try {
				userStoryDescription = "MC- Reject the Farmer Request";
				logUserStoryStart(userStoryDescription, "MC Reject the Order Request From Farmer");
				
				Menu menu = MenuFactory.getTestInputsMenuByTitle(TestInputsMenu.INPUTFLOWDATA);
				inputflowpage.rejectItemReq(menu);

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
