package excelClasses;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


public class XmlDataLoader extends DataLoader {	
	
	static private Module roleData;
	
	static public void loadData(AppModule role) throws Exception  {
		
		if (roleData == null) {
			JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
			jacksonXmlModule.setDefaultUseWrapper(false);
			
			XmlMapper xmlMapper = new XmlMapper(jacksonXmlModule);
			
			try {
				roleData = xmlMapper.readValue(new File(role.getXmlFile()), Module.class);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}	
		}
		
//		print(data);
		
		
	}
	
	public Menu getCamsResMenuByTitle(AppMenu menu) throws Exception {
		return getMenuByTitle(AppModule.TestInputs, menu);
	}
		
	/**
	 * 
	 * @param role - an Enum that indicates the WOL role
	 * @param menu - a variable Enum argument that indicates the desired menu. 
	 * @return a Menu object that contains the menu's test data.
	 * @throws Exception 
	 */
	public Menu getMenuByTitle(AppModule role, AppMenu menu) throws Exception  {		
		loadData(role);
		
		String title = menu.getMenuTitle();
		
		for (Menu m : roleData.getMenus()) {
			if (m.getTitle().equalsIgnoreCase(title)) {
				return m;
			} else {
				for (Menu n : m.getMenus()) {
					if (n.getTitle().equalsIgnoreCase(title)) {
						return n;
					} else {
						for (Menu o : n.getMenus()) {
							if (o.getTitle().equalsIgnoreCase(title)) {
								return o;
							} else {
								
							}
						}
					}
				}
			}
		}		
		
		return null;		
	}
	
	/**
	 * Retrieve the Memu object for the indicated methodName.
	 * Search the CAMS Residential test data.
	 * 
	 * @param methodName
	 * @return
	 * @throws Exception
	 */
//	static public Menu getCamsResMenuByMethodName(String methodName) throws Exception {
//		return getMenuByMethodName(WolRole.camsRes, methodName);
//	}
	
	/**
	 * Retrieve the Memu object for the indicated methodName.
	 * Search the CAMS Business test data.
	 * 
	 * @param methodName
	 * @return
	 * @throws Exception
	 */
//	static public Menu getCamsBusMenuByMethodName(String methodName) throws Exception {		
//		return getMenuByMethodName(WolRole.camsBus, methodName);
//	}
	
	/**
	 * Find menu by 'key' attribute
	 * @param role
	 * @param key
	 * @return
	 * @throws Exception
	 */
//	static public Menu getMenuByMethodName(WolRole role, String methodName) throws Exception {
//		loadData(role);
//		
//		for (Menu m : roleData.getMenus()) {
//			if (m.getKey().equalsIgnoreCase(methodName)) {
//				return m;
//			} else {
//				for (Menu n : m.getMenus()) {
//					if (n.getKey().equalsIgnoreCase(methodName)) {
//						return n;
//					} else {
//						for (Menu o : n.getMenus()) {
//							if (o.getKey().equalsIgnoreCase(methodName)) {
//								return o;
//							} else {
//								
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		
//		return null;
//	}
	
	
	
	/*
	 * for local testing
	 */
//	public static void main(String[] args) throws Exception {		
//		XmlDataLoader.loadData(WolRole.camsRes);
//	}
	
	//for testing
//	private void print(Role data) {
//		for (Menu m : data.getMenus()) {
//			printMenu(m);
//			if (m.hasChild()) {
//				for (Menu n : m.getMenus()) {
//					printMenu(n);
//				}
//			}
//		}
//	}
	
	//for testing
//	private void printMenu(Menu m) {
//		System.out.println("menu title: "+m.getTitle());
//		System.out.println("   customer number: "+m.getCustomerNumber());
//		System.out.println("          username: "+m.getUsername());
//	}

//	public static Role getRoleData() {
//		return roleData;
//	}
	
	


}
