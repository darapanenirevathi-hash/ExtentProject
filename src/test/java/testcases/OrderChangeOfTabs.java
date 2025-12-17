
package testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputFilter.Status;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class OrderChangeOfTabs {

	public static void main(String[] args) throws IOException 
	{
		ExtentReports extentreports = new ExtentReports();
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter("Report.html");
		
		// changing/removing tabs from spark reporter
		
		sparkreporter.viewConfigurer().viewOrder().as(new ViewName[]
				{
					ViewName.DASHBOARD,
					ViewName.TEST,
					ViewName.CATEGORY,
					ViewName.DEVICE,
					ViewName.EXCEPTION
					//ViewName.AUTHOR		//deleting Author tab
				});     // enum creation becoz it return enum
		
		
		extentreports.attachReporter(sparkreporter);
		
		extentreports.createTest("Test1","This is my test1")
		.pass("This is passed")
		.assignAuthor("Revathi")
		.assignCategory("Sanity")
		.assignDevice("Chrome 142");
		
		extentreports.createTest("Test2","This is my test2")
		.fail("This is failed")
		.assignCategory("Smoke")
		.assignDevice("Edge 128")
		.assignAuthor("Rani");
		
		extentreports.createTest("Test3","This is my test3")
		.assignCategory("Regression")
		.assignDevice("Edge 128")
		.pass("This is passed");
		
		extentreports.createTest("Test4","This is my test4")
		.assignAuthor("Rani")
		.assignAuthor("Revathi")
		.assignCategory("Regression")
		.assignDevice("Chrome 142")
		.assignDevice("Chrome 141")
		.fail("This is failed");
		
		extentreports.createTest("Test5","This is my test5")
		.info("This is my info")
		.assignAuthor("Revathi","Raju","Vikram","Rani")
		.assignCategory("Sanity","Smaoke","Regression")
		.assignDevice("Chrome 142", "Chrome 141", "Edge 128");
		
		extentreports.createTest("Test6","This is my test6")
		.skip("This is skipped")
		.assignAuthor(new String[] {"Revathi","Raju","Vikram","Rani"})
		.assignCategory(new String[] {"Sanity","Regression"})
		.assignDevice(new String[] {"Chrome 142","Chrome 141","Edge 128"});
		
		Throwable t = new RuntimeException("This is custom Exception");
		extentreports.createTest("Test 7", "This my Test7")
		.fail(t)
		.assignAuthor("Revathi")
		.assignCategory("smaoke");
		
		
		extentreports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());
		
	}

}
