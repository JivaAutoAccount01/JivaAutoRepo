package excelClasses;



/**
 * An Enum that encapsulates a Java type for each role and associates the role with a spreadsheet tab or an XML data file.
 *
 */
public enum AppModule {


	TestInputs("TestInputs",TestInputsMenu.values(),"");
		
	private final String sheetName;
	private final AppMenu[] appMenus;
	private final String xmlFile;

	AppModule(String sheetName, AppMenu[] appMenus, String xmlFile) {
		this.sheetName = sheetName;
		this.appMenus = appMenus;
		this.xmlFile = xmlFile;
	}


	public String getXmlFile() {
		return xmlFile;
	}

	public String getSheetName() {
		return sheetName;
	}

	public AppMenu[] getAppMenus() {
		return appMenus;
	}

}
