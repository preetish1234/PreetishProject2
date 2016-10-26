package Page;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import Utilitys.ExcelDataConfig;
import Utilitys.log.Log;
import Utilitys.util;

public class FinalShippingpage{

	WebDriver driver;

	public FinalShippingpage(WebDriver driver){
	PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how=How.XPATH,using="//input[@id='log']")
	public WebElement user;
	
	@FindBy(how=How.XPATH,using="//input [@id='pwd']")
	public WebElement pass;
	
	@FindBy(how=How.XPATH,using="//input[@id='login']")
	public WebElement login;

	//method 
	public void CheckoutSignin(WebDriver driver) throws Exception{
	
		
		
		ExcelDataConfig execl = new ExcelDataConfig("C:\\workspace\\com.Preetishk\\src\\Excel\\Data.xlsx");
		user.sendKeys(execl.getData(0, 1, 0));
		pass.sendKeys(execl.getData(0, 1, 1));
		
	   }
@FindBy(how=How.XPATH,using="//input[@id='wpsc_checkout_form_2']")
public WebElement FirstNametxt;
	
//Test txt for firstname
 public void Firstname_Method(WebDriver driver) throws Exception {
	 ExcelDataConfig excel = new ExcelDataConfig("C:\\workspace\\com.Preetishk\\src\\Excel\\Data.xlsx");
		
	 FirstNametxt.sendKeys(excel.getData(0, 1, 2));
	 
 }
 
 @FindBy(how=How.XPATH,using="//input[@id='wpsc_checkout_form_3']")
 public WebElement LastNameTXT;  
 
 //Action txt for lastname 
 
 public void LstNme_Method(WebDriver driver) throws Exception{
	 ExcelDataConfig excel = new ExcelDataConfig("C:\\workspace\\com.Preetishk\\src\\Excel\\Data.xlsx");
	 LastNameTXT.sendKeys(excel.getData(0, 1, 3));
	 
 }
 

      @FindBy(how=How.XPATH,using="//textarea[@id='wpsc_checkout_form_4']")
     public WebElement AdressTxt;

public void Adresstxt_Method(WebDriver driver) throws Exception{

	ExcelDataConfig excel = new ExcelDataConfig("C:\\Preetish\\com.Preetishk\\src\\Excel\\Data.xlsx");	
            AdressTxt.sendKeys(excel.getData(0, 1, 4));
}


@FindBy(how=How.XPATH,using="//textarea[@id='wpsc_checkout_form_4']")
public WebElement City_link;

public void city_Method(WebDriver driver) throws Exception{
ExcelDataConfig excel = new ExcelDataConfig("C:\\Preetish\\com.Preetishk\\src\\Excel\\Data.xlsx");	
AdressTxt.sendKeys(excel.getData(0, 1, 5));
}


@FindBy(how=How.XPATH,using="//select[@id='wpsc_checkout_form_7']")
public WebElement selectContry;




@FindBy(how=How.XPATH,using="//select[@id='wpsc_checkout_form_7']/option[@value='IN']")
  public WebElement India;   
        public void CoutrySelect(WebDriver driver){
	util.explicitWait(driver, "//select[@id='wpsc_checkout_form_7']");
	util.SelectDropDown(driver, "//select[@id='wpsc_checkout_form_7']", "IN");
}

        
        @FindBy(how=How.XPATH,using="//input[contains(@class,'button')]")
public WebElement Purchase;


public void purchaseClick(WebDriver driver) throws Exception{

	try {
		
		
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",Purchase);
			util.highLightElement(driver, Purchase);
		
		Purchase.click();
		
		Log.info("Sucessfully purches");
	} catch (Exception e) {
	
		e.printStackTrace();
	}
}
}