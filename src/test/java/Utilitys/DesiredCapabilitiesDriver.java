package Utilitys;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;




public class DesiredCapabilitiesDriver {
	
	 public static void main(String[] args) {
		
		 
		 
		 String PROXY = "localhost:8080";
		
		 
		 org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		 proxy.setHttpProxy(PROXY)
		      .setFtpProxy(PROXY)
		      .setSslProxy(PROXY);
		 DesiredCapabilities cap = new DesiredCapabilities();
		 cap.setCapability(CapabilityType.PROXY, proxy);
		 WebDriver driver = new FirefoxDriver(cap);
     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     driver.get(PROXY);
     
}
}