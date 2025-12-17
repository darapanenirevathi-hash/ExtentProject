package testcases;

import java.awt.Desktop;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AttachScreenShot 
{
	public static WebDriver driver;

	public static void main(String[] args) throws Exception 
	{
		ExtentReports extentreports = new ExtentReports();
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter("Report.html");
		extentreports.attachReporter(sparkreporter);
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://google.com");
		String base64Code = takeScreenShot();
		String path = takeScreenShot("Google.img");
		
        // attaching screenshots to extent reports at text level
		
		extentreports.createTest("ScreenShot1","This is my screenshot at test level")
		.pass("This is passed")
		.addScreenCaptureFromPath(path)
		.addScreenCaptureFromPath(path, "google screenshot");  //screenshot with title
		
		
		extentreports.createTest("ScreenShot2","This is my file screenshot at test level")
		.pass("This is passed")
		.addScreenCaptureFromBase64String(base64Code)
		.addScreenCaptureFromBase64String(base64Code, "google screenshot");
		
		
		//attaching screenshots to extent reports at log level
		
		extentreports.createTest("ScreenShot3","This is my file screenshot at test level")
		.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());
		
		
		extentreports.createTest("ScreenShot4","This is my file screenshot at test level")
		.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code, "Google Screenshot").build());
		
		extentreports.createTest("ScreenShot5","This is my file screenshot at test level")
		.fail("This is google screenshot",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		
		Throwable t = new Throwable("This is Exception");
		extentreports.createTest("ScreenShot","This is my file screenshot at test level")
		.fail(t, MediaEntityBuilder.createScreenCaptureFromPath(path, "google image").build());
		
		
		
		extentreports.flush();
		driver.quit();
		Desktop.getDesktop().browse(new File("report.html").toURI());
		
		
	}
	
	public static String takeScreenShot(String filename) throws Exception
	{
	
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/"+filename);
		
		FileUtils.copyFile(sourceFile, destFile);
		System.out.println("Screenshot saved successfully");
		return destFile.getAbsolutePath();
       
	}
	
	public static String takeScreenShot() throws Exception
	{
	
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		String base64code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
		System.out.println("Screenshot saved successfully");
		return base64code;
       
	}
		
		
		

	}



