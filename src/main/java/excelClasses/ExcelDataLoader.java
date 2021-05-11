package excelClasses;

import java.util.List;

/**
 * Retrieve the test data for a particular spreadsheet tab (WolRole) and indicated menu title (WolMenu). 
 *
 */
public class ExcelDataLoader extends DataLoader {

	/**
	 * 
	 * @param AppModule - an Enum that indicates the WOL role (sheet name).
	 * @param AppMenu - an Enum argument that indicates the desired menu title. 
	 * @return a Menu object that contains the menu's test data (customer number and user name).
	 * @throws Exception 
	 */
	@Override
	public Menu getMenuByTitle(AppModule module, AppMenu appMenu) throws Exception  {		
		
		Menu menu = new Menu();
		
		List<String> acctArray = new ExcelUtils_SelGrid().setTestDataSelGrid(appMenu.getMenuTitle(), module.getSheetName());
		menu.setUsername(acctArray.get(0));
		menu.setPassword(acctArray.get(1));	
		menu.setParameter3(acctArray.get(2));
		menu.setParameter4(acctArray.get(3));		
		menu.setParameter5(acctArray.get(4));
		menu.setParameter6(acctArray.get(5));
		menu.setAppModule(module);
		
		return menu;		
	}	
	
}
