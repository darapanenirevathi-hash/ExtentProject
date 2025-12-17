package testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputFilter.Status;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewEnvironmentVariablesAdd {

	public static void main(String[] args) throws IOException 
	{
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
	    Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
		
		ExtentReports extentreports = new ExtentReports();
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter("Report.html");
		
	
	
		// Extent Report Configuration for normal Java file 
		
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setDocumentTitle("New Tiltle");
		sparkreporter.config().setReportName("My Report");
		sparkreporter.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		sparkreporter.config().setCss(".badge-primary{background-color:#6569df;}");
		sparkreporter.config().setJs("document.getElementsByClassName(\"logo\")[0].style.display='none';");
	
		
		// Adding Environment/System Variables to Extent Report
		
		extentreports.setSystemInfo("os", System.getProperty("os.name"));
		extentreports.setSystemInfo("java version", System.getProperty("java.version"));
		extentreports.setSystemInfo("Browser", cap.getBrowserName() + "   " + cap.getVersion());
		extentreports.setSystemInfo("UserName", "darapanenirevathi@gmail.com");
		extentreports.setSystemInfo("Password", "123456");
		extentreports.setSystemInfo("App URI", "http://orangehrm.qedgetech.com");
		
		
		
	/*	
		// Extent Report Configuration for json file
		// For file goto https://github.com/extent-framework/extentreports-java/blob/master/config/spark-config.json
		
		File file = new File("C:\\MyGit\\ExtentProject\\src\\test\\resources\\extent_report_config.json");
	    sparkreporter.loadJSONConfig(file);
	    
    */
	    
	 /*  
	    
	    // Extent Report Configuration for xml file
		//For file goto https://github.com/extent-framework/extentreports-java/blob/master/config/spark-config.xml
	    
	    File file = new File("C:\\MyGit\\ExtentProject\\src\\test\\resources\\extent_report_config.xml");
	    sparkreporter.loadXMLConfig(file);
	 */ 
		
		
        extentreports.attachReporter(sparkreporter);      //attaching sparkreport to Extentreport
		
		extentreports.createTest("Test1","This is my test1")      //creating test and adding to extent report
		.pass("This is passed");
		
		extentreports.createTest("Test2","This is my test2")      //creating test and adding to extent report 
		.fail("This is failed");
		
		extentreports.flush();
		driver.quit();
		Desktop.getDesktop().browse(new File("report.html").toURI());
		
	}

}
