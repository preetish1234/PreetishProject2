package Utilitys;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import Page.Checkout;
import Page.FinalShippingpage;
import Page.Product_category;
import Page.loginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class JavaSample {

  public static final String USERNAME = "preetishkumarmah1";
  public static final String AUTOMATE_KEY = "p7XHuxW82Av6pUdpLyvf";
  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
  static WebDriver driver;
  static String user;
  static String pass;
	    @Test

	    public static void CloudSetup() throws MalformedURLException {
	    	
	    	try{
	    	DesiredCapabilities caps = DesiredCapabilities.chrome();
	            caps.setPlatform(Platform.WIN10);

	       driver = new RemoteWebDriver(new URL(URL),
	       DesiredCapabilities.chrome()
	     );
	       caps.setCapability("browser", "Chrome");
	       caps.setCapability("browser_version", "53.0");
	       caps.setCapability("os", "Windows");
	       caps.setCapability("os_version", "10");
	       caps.setCapability("resolution", "1024x768");
	       caps.setCapability("browserstack.debug", "true");
		   caps.setCapability("build", "First build");
	    	
	           	driver.get("http://store.demoqa.com");
	           	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	    			
	        loginPage account = new loginPage(driver);
	       account.My_Account(driver);
	    account.Login(driver, user, pass);


	    Product_category product = new Product_category(driver);
	    product.ProductAction(driver);

	    Checkout product1check = new Checkout(driver);
	    product1check.CheckOut(driver);
	    product1check.cLickB0ttom(driver);



	    FinalShippingpage select = new FinalShippingpage(driver);
	    select.purchaseClick(driver);

	    driver.quit();
	    			
	    		} catch (Exception e) {
	    			
	    			e.printStackTrace();
	    		}	
	    	 
	    }


	    
  }
  