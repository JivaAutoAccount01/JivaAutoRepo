package adminportalpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import utilities.ElementManager;

public class FarmersOnCredit extends ElementManager {
	
	@FindBy(xpath = "//select[@name='geo.name']")
	public WebElement dropdownSelect;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement goBtn;
	
	@FindBy(xpath = "//button[text()='OK']")
	public WebElement OKBtn;
	
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	public WebElement viewBtn;
	
	@FindBy(xpath = "//button[@class='close pull-right']")
	public WebElement closeBtn;
	
	@FindBy(xpath = "//span[@class='caret']")
	public WebElement logoutLink;
	
	@FindBy(className = "dd-link")
	public WebElement logoutBtn;
	
	public FarmersOnCredit(WebDriver driver) {
	this.driver = driver;
	}

	
	public void farmersOnCredit() throws Exception {
		
		Reporter.log("Test Metrics View Started",true);
		sleep(5000);
		
		selectByValue(dropdownSelect,"1","Indonesia");
		
		selectByValue(dropdownSelect,"60","Pesawaran");
		
		selectByValue(dropdownSelect,"5381","Jawa Timur");
					
		clickElement(goBtn,"Go Button");
		
		if(elementAvailable(OKBtn, "OK Button")==true)
		{
			Reporter.log("No Records Popup displayed");
			clickElement(OKBtn, "OK Button");
		}else {
			Reporter.log("Records are Available");
		}
		
		clickElement(viewBtn,"View Button");
		clickElement(closeBtn,"View Button");
		
		clickElement(logoutLink, "LogOut Link");
		clickElement(logoutBtn, "LogOut Button");
		
		Reporter.log(" View Task Metrics Completed",true);
}
}
