package testcases;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {

	public static void main(String[] args) 
	{
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
	/* 
	    Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
	    System.out.println(cap.getBrowserName());
	    System.out.println(cap.getVersion());
	    
	*/  
	    
	   // System.getProperties().list(System.out);  //print all current system properties and their values in console 	    
	    
	    System.out.println(System.getProperty("os.name"));
	    System.out.println("java version " +System.getProperty("java.version"));
	    
		driver.quit();
		
	

	}

}
