package excelClasses;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * A Menu object encapsulates test data for a particular  menu option.
 * A parent Menu may or may not contain test data (customer number, username, etc.).
 * A parent Menu will contain child Menu(s). * 
 *
 */
public class Menu {

	@JacksonXmlProperty(isAttribute = true)
	private String title;
	
	@JacksonXmlProperty(isAttribute = true)
	private String key;
	
	@JacksonXmlProperty(isAttribute = false)
	private String username;
	
	@JacksonXmlProperty(isAttribute = false)
	private String password;
	
	private String parameter3;
	private String parameter4;
	private String parameter5;
	private String parameter6;
	private String parameter7;
	
	@JacksonXmlProperty(isAttribute = false, localName = "menu")
	private Menu[] menus = new Menu[0];	
	
	private AppModule testinputs;
	
	private List<String> names = new ArrayList<String>();
	
	/**
	 * Returns a collection containing the additional parameter values.
	 * @return a List, if there are not additional parameter values, the List will be empty.
	 */
	public List<String> getNames() {
		return names;
	}	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Menu[] getMenus() {
		return menus;
	}
	
	public boolean hasChild() {
		boolean hasChild = false;
		
		if (this.menus != null) {
			hasChild = true;
		}
		
		return hasChild;
	}

	public void setMenus(Menu[] menus) {
		this.menus = menus;
	}

	public String getKey() {
		if (null == this.key) {
			return "";
		}
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setAppModule(AppModule testinputs) {
		this.testinputs = testinputs;		
	}

	public AppModule getAppModule() {
		return this.testinputs;
	}
	
	/**
	 * Convenience method to return the spreadsheet tab's name.
	 * @return a String that represents the tab's name for this role in the spreadsheet.
	 */
	public String getSheetName() {
		return this.getAppModule().getSheetName();
	}

	public void setParameter5(String parameter5) {
		this.parameter5 = parameter5;
		
	}

	public void setParameter6(String parameter6) {
		this.parameter6 = parameter6 ;
		
	}
	
	public void setParameter7(String parameter7) {
		this.parameter7 = parameter7 ;
		
	}
	
	public String getParameter5() {
		return parameter5;
	}

	public String getParameter6() {
		return parameter6;
	}

	public String getParameter7() {
		return parameter7;
	}
	
	/*
	 * public void setParameter3(String values) { this.parameter3 = values;
	 * this.names = getParameter3AsList(values); }
	 */
	
	public void setParameter3(String farmerName) {
		this.parameter3=farmerName;
	}

	public String getParameter3() {
		return parameter3;
	}
	
	/*
	 * private List<String> getParameter3AsList(String customerValues) {
	 * List<String> values = new ArrayList<String>(); if (null != username &&
	 * username.length() > 0) { values = Arrays.asList(username.split("\\s*,\\s*"));
	 * } return values; }
	 */

	public String getParameter4() {
		return parameter4;
	}

	public void setParameter4(String parameter4) {
		this.parameter4 = parameter4;
	}
	
	
	
	public void printTestData(){
		System.out.println("Username: "+getUsername());
		System.out.println("Password: "+getPassword());
	}
	
}
