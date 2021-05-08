package adminportalpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import utilities.ElementManager;

public class TaskMetricsPage extends ElementManager {
	
	@FindBy(xpath = "//select[@name='selectRegion']")
	public WebElement regionSelect;
	
	@FindBy(xpath = "//select[@name='district']")
	public WebElement districtSelect;
	
	@FindBy(xpath = "//select[@name='type']")
	public WebElement taskTypeSelect;
	
	@FindBy(xpath = "//select[@name='status']")
	public WebElement statusSelect;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement goBtn;
	
	@FindBy(xpath = "//button[text()='OK']")
	public WebElement OKBtn;
	
	@FindBy(xpath = "//span[@class='caret']")
	public WebElement logoutLink;
	
	@FindBy(className = "dd-link")
	public WebElement logoutBtn;
	
	public TaskMetricsPage(WebDriver driver) {
	this.driver = driver;
	}

	
	public void testMetrics() throws Exception {
		
		Reporter.log("Test Metrics View Started",true);
		sleep(5000);
		
		selectByValue(regionSelect,"3","Lampung");
		
		selectByValue(districtSelect,"60","Pesawaran");
		
		selectByValue(taskTypeSelect,"3","Farmer Ingredients");
		
		selectByValue(statusSelect,"1","Pending");
		
		clickElement(goBtn,"Go Button");
		
		if(elementAvailable(OKBtn, "OK Button")==true)
		{
			Reporter.log("No Records Popup displayed");
			clickElement(OKBtn, "OK Button");
		}else {
			Reporter.log("Records are Available");
		}
		
		clickElement(logoutLink, "LogOut Link");
		clickElement(logoutBtn, "LogOut Button");
		
		Reporter.log(" View Task Metrics Completed",true);
}
}
