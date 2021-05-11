package excelClasses;



/**
 * The factory will determine where to retrieve the data from (e.g. Excel, Xml, etc).
 *
 */
public class MenuFactory {

	private static boolean useExcel = true;

	/**
	 * Convenience method to get test data by indicating the WolRole (spreadsheet tab or Xml file) and WolMenu (menu title).
	 * @param role - a Role Enum for the spreadsheet tab or XML file name.
	 * @param wolMenu - WolMenu Enum for the desired menu title (e.g. 'Invoices', 'Go Paperless', etc.).
	 * @return
	 * @throws Exception
	 */
	static public Menu getMenuByTitle(AppModule role, AppMenu wolMenu) throws Exception  {
		DataLoader dataLoader = null;

		if (useExcel) {
			dataLoader = new ExcelDataLoader();
		} else {
			dataLoader = new XmlDataLoader();
		}

		Menu menu = dataLoader.getMenuByTitle(role, wolMenu);

		return menu;
	}
	
	/**
	 * Convenience method to get  Sales Referral Endorser Agent test data.
	 * @param wolMenu - a WolMenu Enum for the desired menu title.
	 * @return
	 * @throws Exception
	 */
	static public Menu getTestInputsMenuByTitle(TestInputsMenu appMenu) throws Exception {
		return getMenuByTitle(AppModule.TestInputs, appMenu);
	}

	/**
	 * convenience method for unit testing
	 */
	static public void useXml() {
		useExcel = false;
	}

	/**
	 * convenience method for unit testing
	 */
	static public void useExcel() {
		useExcel = true;
	}


}
