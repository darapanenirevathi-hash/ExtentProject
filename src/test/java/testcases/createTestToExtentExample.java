package testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class createTestToExtentExample {

	public static void main(String[] args) throws IOException 
	{
		ExtentReports extentreports = new ExtentReports();    //It act as Engine
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter("Report.html");  // It act as Bhogi for Engine 
		extentreports.attachReporter(sparkreporter);
		
		//Method1 to create test and add to log
		
		extentreports.createTest("Test1","This is my test")
		.pass("This is passed");
		
		//Method2 to create test and add to log
		
		ExtentTest test2 = extentreports.createTest("Test2");
		test2.fail("This is failed");
		
		//Method3 to create test and add to log
		
		ExtentTest test3 = extentreports.createTest("Test3");
		test3.log(Status.INFO, "This is Info");

		
		extentreports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());
		
	}

}
