package excelClasses;

public enum TestInputsMenu implements AppMenu {

	
	JLLOGIN("JLLogin"),
	HARVESTFLOWDATA("HarvestFlowData"), 
	FARMERLOGIN("FarmerLogin"), 
	MCLOGIN("MCLogin"), 
	NEWFARMERLOGIN("NewFarmerLogin"), 
	MAGENTOLOGIN("MagentoLogin"),
	FARMERCRMLOGIN("FarmerCRMLogin"),
	WEBPORTALLOGIN("WebPortalLogin"),
	INPUTFLOWDATA("InputFlowData"),
	KYCFLOWDATA("KYCFlowData");

	private String title;

	TestInputsMenu(String value) {
		this.title = value;
	}

	public String getMenuTitle() {
		return this.title;
	}
}
