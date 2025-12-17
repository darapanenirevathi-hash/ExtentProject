package utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest
{


	public static WebDriver driver;
	public static String screenshotSubFolderName;
	public static ExtentReports extentreports;
	public static ExtentTest extenttest;
	
	
	
	
	@BeforeSuite
	public void initialiseExtentReports() throws IOException
	{
		
        extentreports = new ExtentReports();
		
		// generating Report to All tests
	    ExtentSparkReporter sparkreporter_all = new ExtentSparkReporter("Report_all.html");
        

	    // generating Report to FAIL-only report
	    ExtentSparkReporter sparkreporter_fail = new ExtentSparkReporter("FailReport.html");
	    sparkreporter_fail.filter().statusFilter().as(new Status[] {Status.FAIL});
	    
	    
	    
	    //attaching sparkreport to ExtentReport
	    
	    extentreports.attachReporter(
	            sparkreporter_all,
	            sparkreporter_fail
	           
	    );
	    
	    
		
		// attach system info to Extentreports
		
		extentreports.setSystemInfo("os", System.getProperty("os.name"));
		extentreports.setSystemInfo("java version", System.getProperty("java.version"));
		
	}
	
	
	@AfterSuite
	public void generateExtentReports() throws IOException
	{
		extentreports.flush();
		
		Desktop.getDesktop().browse(new File("Report_all.html").toURI());
		Desktop.getDesktop().browse(new File("FailReport.html").toURI());
	}
	
	
	
	@BeforeTest
	@Parameters({"browser", "author"})
	public void launchApp(
	        ITestContext context,
	        @Optional("chrome") String browser,
	        @Optional("Automation") String author) {

	    // ---------- Browser setup ----------
	    switch (browser.toLowerCase()) {
	        case "chrome":
	            WebDriverManager.chromedriver().setup();
	            driver = new ChromeDriver();
	            break;

	        case "edge":
	            WebDriverManager.edgedriver().setup();
	            driver = new EdgeDriver();
	            break;

	        case "firefox":
	            WebDriverManager.firefoxdriver().setup();
	            driver = new FirefoxDriver();
	            break;

	        default:
	            throw new IllegalArgumentException("Invalid browser name: " + browser);
	    }

	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	    // ---------- Capabilities ----------
	    Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
	    String device = cap.getBrowserName() + " " +
	            cap.getVersion().split("\\.")[0];

	    // ---------- Extent Reports ----------
	    extentreports.setSystemInfo("Browser", device);

	    extenttest = extentreports.createTest(context.getName());

	    // SAFE author assignment
	    if (author != null && !author.trim().isEmpty()) {
	        extenttest.assignAuthor(author);
	    } else {
	        extenttest.assignAuthor("Automation");
	    }

	    extenttest.assignDevice(device);
	}
	
	
	@AfterTest
	public static void closeApp()
	{
		driver.quit();
	}
	
	
	@AfterMethod
	// This method we are checking status (pass/fail) of test method and attaching screenshot and exception details
	
	public void checkStatus(Method m, ITestResult result) throws Exception 
	{
	    if (result.getStatus() == ITestResult.FAILURE) 
	    {
	        String screenshotPath = takeScreenShot(result.getTestContext().getName() + "_" + result.getMethod().getMethodName() + ".jpg");

	        extenttest.fail("Test Failed: " + result.getThrowable());    // Exception details for what the method fails
	        extenttest.addScreenCaptureFromPath(screenshotPath);        //attaching screenshotpath to ExtenTest

	    } 
	    else if (result.getStatus() == ITestResult.SUCCESS) 
	    {
	        extenttest.pass(m.getName() + " is passed");
	    }

	    extenttest.assignCategory(m.getAnnotation(Test.class).groups());     // adding groups category described at test level to extentest generated report
                                                                             // we can do at @AfterMethod or @BeforeMethod
	}
	
	
	
	public String takeScreenShot(String filename) throws Exception 
	{
	    if (screenshotSubFolderName == null) 
	    {
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
	        screenshotSubFolderName = now.format(format);
	    }

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File srcFile = ts.getScreenshotAs(OutputType.FILE);

	    // Create folder path
	    String folderPath = "./Screenshots/" + screenshotSubFolderName;
	    File screenshotFolder = new File(folderPath);
	    if (!screenshotFolder.exists()) {
	        screenshotFolder.mkdirs();
	    }

	    // Final screenshot path
	    String destPath = folderPath + "/" + filename;
	    File destFile = new File(destPath);

	    FileUtils.copyFile(srcFile, destFile);
	    System.out.println("Screenshot saved successfully at: " + destPath);

	    return destPath;   // return real path, NOT folder name
	    
	
	    
		}
	    
	    

	}




