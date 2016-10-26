package com.org.preetish_maven.preetish_maven;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;







import Page.Checkout;
import Page.FinalShippingpage;
import Page.Product_category;
import Page.loginPage;
import Utilitys.BaseClass;
import Utilitys.BrowserFactory;
import Utilitys.util;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class TestApplication extends BaseClass  {
	
	protected ExtentReports report;
	protected static ExtentTest logger;
	Logger log;
	WebDriver driver;
	
	String user = "preetish1234";
	String pass = "mili21sep@preetis";
	
	@BeforeMethod(alwaysRun=true)
	public void Logconfig()throws ClassNotFoundException, SQLException
	{
PropertyConfigurator.configure("C:\\Preetish\\preetish_maven\\log.properties");
  }
	
@BeforeTest(alwaysRun=true)

	public void BrowserEngin() throws Exception{
	
	         report = BaseClass.setup();
	    driver=BrowserFactory.startBrowser("crome");
		logger = report.startTest("ApplicationTestCase");
		   logger.log(LogStatus.INFO, "Browser started");

	
}

	
	@Test(description = "This will take data from database")
	public void testDb() throws ClassNotFoundException, SQLException
	{
try{	
	
	logger = report.startTest("DatabaseTesting");
      Class.forName("com.mysql.jdbc.Driver");	
System.out.println("database loaded");

Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium","root","mili21sep");
	System.out.println("Connected to mysql DataBase");

	
	Statement smt=con.createStatement();
	ResultSet rs = smt.executeQuery("select * from userid where firstname = 'preetish1234'");

	
	while (rs.next()){

		String 	first_Name = rs.getString("firstname");
 System.out.println("Database user record is = " + first_Name + " But found " + user);
    
 if(!rs.getString("firstname").equals(user)){
	   System.out.println("invalid user");
	        logger.log(LogStatus.FAIL, "invalid user "+ user );   
   
   }else
   
	   System.out.println("user with correct credential");
      logger.log(LogStatus.INFO, "user is valid " + user);
	 
	
	String password= rs.getString("password");
System.out.println("Database password record is = "+ password);
if(!rs.getString("password").equalsIgnoreCase(pass)){
	 System.out.println("invalid password");
	   logger.log(LogStatus.FAIL, "invalid Password");

}else
	
	System.out.println("user can login with correct credential");
logger.log(LogStatus.INFO, "password is valid " + pass);
	}
	con.close();	
	}	catch (Exception e){
		System.out.println(e);
	
  }		
}
	

	@Test(dependsOnMethods = { "testDb" },groups="Login",priority=1,alwaysRun=true)	
		
		public void LoginTOApp() throws Exception{
			
            try {
            	
				logger = report.startTest("LoginTest");
         loginPage account = new loginPage(driver);
        account.My_Account(driver);
  logger.log(LogStatus.INFO, "Account click");
     account.Login(driver, user, pass);
			} catch (Exception e) {
				
				e.printStackTrace();
			}	
		 
	}


	@Test(description="this will select the product and click on checkout",dependsOnMethods = { "LoginTOApp" },groups="product",priority=2,alwaysRun=true)	
public void Product() throws Exception{
	

 logger= report.startTest("ProductTest");
 
    Product_category product = new Product_category(driver);
    product.ProductAction(driver);
    logger.log(LogStatus.INFO, "Hover to product click on accessories and selected the item");
    
    Checkout product1check = new Checkout(driver);
    product1check.CheckOut(driver);
    product1check.cLickB0ttom(driver);
    logger.log(LogStatus.INFO, "click on checkout");
    
    
}
   
 
	@Test(description="this wil perfrom shipping details",dependsOnMethods ={"Product"},groups="payment",priority=3,alwaysRun=true)	
 public void Shipping() throws Exception{
	
    logger= report.startTest("ShippingTest");

    FinalShippingpage select = new FinalShippingpage(driver);
    //select.CheckoutSignin(driver);
    //select.Firstname_Method(driver);
    //select.LstNme_Method(driver);
    //select.Adresstxt_Method(driver);
    //select.city_Method(driver);
  
    select.purchaseClick(driver);
    
    logger.log(LogStatus.PASS, "User purches sucessfully");
}

	

	@AfterMethod(alwaysRun=true)
public void tearDown(ITestResult result){
	
		
	try {
		if (result.getStatus()==ITestResult.FAILURE)
			
		{
		
			String screenshot_path =util.captureScreenShot( driver, result.getName());
            String image= logger.addScreenCapture(screenshot_path);
logger.log(LogStatus.INFO,"ExtentReport ScreenShot "+ image );
		}
			
		report.endTest(logger);
		report.flush();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	@AfterClass(alwaysRun=true)

	public void tearDown(){
	   driver.quit();

	}
}

