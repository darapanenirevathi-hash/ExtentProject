

package testcases;

import java.awt.Desktop;
import com.aventstack.extentreports.Status;
import java.io.File;
import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class GenerateMultReports 
{

	public static void main(String[] args) throws IOException 
	{
		ExtentReports extentreports = new ExtentReports();
		
		// All tests
	    ExtentSparkReporter sparkreporter_all = new ExtentSparkReporter("Report_all.html");
	    
	    //ExtentSparkReporter sparkreporter_all = new ExtentSparkReporter("C:\\MyGit\\ExtentProject\\Report_all.html");

	    // FAIL-only report
	    ExtentSparkReporter sparkreporter_fail = new ExtentSparkReporter("FailReport.html");
	    sparkreporter_fail.filter().statusFilter().as(new Status[] {Status.FAIL});

	    // SKIP + WARNING report
	    ExtentSparkReporter sparkreporter_skipandwarning = new ExtentSparkReporter("Skip&WarningReport.html");
	    sparkreporter_skipandwarning.filter().statusFilter().as(new Status[] 
	    		{ 
	    				Status.SKIP,
	    				Status.WARNING
	            });

	 
		
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
		
		
		// Attach all reporters
	    extentreports.attachReporter(
	            sparkreporter_all,
	            sparkreporter_fail,
	            sparkreporter_skipandwarning
	    );
		
		extentreports.flush();
		
		Desktop.getDesktop().browse(new File("Report_all.html").toURI());
		Desktop.getDesktop().browse(new File("FailReport.html").toURI());
		Desktop.getDesktop().browse(new File("Skip&WarningReport.html").toURI());
		
		
	}

}
