package testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputFilter.Status;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class NewConfigurationAdding {

	public static void main(String[] args) throws IOException 
	{
		ExtentReports extentreports = new ExtentReports();
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter("Report.html");
		
	
	
		// This is for normal Java file
		
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setDocumentTitle("New Tiltle");
		sparkreporter.config().setReportName("My Report");
		sparkreporter.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		sparkreporter.config().setCss(".badge-primary{background-color:#6569df;}");
		sparkreporter.config().setJs("document.getElementsByClassName(\"logo\")[0].style.display='none';");
	
		
	/*	
		// This is for Json file
		// For file goto https://github.com/extent-framework/extentreports-java/blob/master/config/spark-config.json
		
		File file = new File("C:\\MyGit\\ExtentProject\\src\\test\\resources\\extent_report_config.json");
	    sparkreporter.loadJSONConfig(file);
	    
    */
	    
	 /*  
	    
	    // This is for XML file
		//For file goto https://github.com/extent-framework/extentreports-java/blob/master/config/spark-config.xml
	    
	    File file = new File("C:\\MyGit\\ExtentProject\\src\\test\\resources\\extent_report_config.xml");
	    sparkreporter.loadXMLConfig(file);
	 */ 
		
        extentreports.attachReporter(sparkreporter);
		
		extentreports.createTest("Test1","This is my test1")      //creating test and adding to extent report
		.pass("This is passed");
		
		extentreports.createTest("Test2","This is my test2")      //creating test and adding to extent report 
		.fail("This is failed");
		
		extentreports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());
		
	}

}
