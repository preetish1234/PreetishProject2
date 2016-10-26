package Page;





import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import Utilitys.log.Log;
import Utilitys.util;






public class loginPage {
	
	
	WebDriver driver;
	
	
	public loginPage(WebDriver driver){
    //Initialize my object in this page
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(how=How.XPATH,using="//a[contains(.,'AccountMy Account')]")
	public  WebElement MyAccount;
	
	
	    public void My_Account(WebDriver driver){
	      
	    	util.highLightElement(driver, MyAccount);
	    	JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();",MyAccount);
	       
	       util.captureScreenShot(driver, "MyAccount");
	
}
	    //Login functionality
	    @FindBy(how = How.XPATH,using="//input[@id='log'][@name='log']")
        public WebElement username;
	    
	    @FindBy(how=How.XPATH,using="//input[@id='pwd'][@name='pwd']")
	    public WebElement password;
	    
	    @FindBy(how = How.XPATH,using="//input[@id='login'][@name='submit']")
	    public WebElement LoginBottom;
	    
	  //Login functionality
	    public void Login(WebDriver driver, String user , String pass) throws Exception{

	
	    	  try {
	    		  
	    		  
	    		  try {
	    			  util.explicitWait(driver, "//input[@id='log']");
					Actions action = new Actions(driver);
					 action.sendKeys(username, user).build().perform();
					util.highLightElement(driver, username);
			       
					//	((JavascriptExecutor)driver).executeScript("document.getElementById('log').value='preetish1234';");
					 
					  String userid = "preetish1234";
     Assert.assertEquals("invalid user", userid, user);
  	  Log.info("User can type on the user field");
				} catch (NoSuchElementException e) {
					
					e.printStackTrace();
				}
			
				  
				try {
					util.highLightElement(driver, password);
					password.click();  
					password.sendKeys(pass);
					  
					  
					  String password ="mili21sep@preetish";
 Assert.assertEquals("invaild password",password, pass);
					  Log.info("user can type in the password field");
				} catch ( NoSuchElementException e) {
					e.printStackTrace();
				}
			
	    	  
	    	  
	    	  util.highLightElement(driver, LoginBottom);
	    	  LoginBottom.click();
	       	  Log.info("click on loginbuttom");
	    	
	       	  
	    	  } catch (Exception e) {
					
					e.printStackTrace();
				}
		    	  
	    	
	    }
	    
	    
}