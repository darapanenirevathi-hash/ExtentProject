package testcases;



import static org.testng.Assert.assertEquals;
import org.testng.Assert;

import java.security.Key;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



import io.github.bonigarcia.wdm.WebDriverManager;
import utils.BaseTest;

public class ExtentReportTestNG  extends BaseTest
{
	

	
	@Test(testName = "TestGoogle", groups= {"smoke"})
	public void TestGoogle()
	{
		
		driver.get("http://google.com");
		extenttest.info("Navigated to URL");
		driver.findElement(By.name("q")).sendKeys("hyderabad", Keys.ENTER);
		extenttest.info("enter text in search box");
		
		// WAIT for results to load
		
	    WebDriverWait wait = new WebDriverWait(driver,10);
	    wait.until(ExpectedConditions.titleContains("hyderabad"));
	    
		String actualTitle = driver.getTitle();

	    Assert.assertTrue(
	        actualTitle.toLowerCase().contains("hyderabad"),
	        "Title mismatched"
	    );

	    extenttest.pass("Assertion passed: Title contains hyderabad");
		
	}
	
	@Test(testName = "TestFacebook", groups= {"sanity"})
	public void TestFacebook()
	{
		
		driver.get("http://facebook.com");
		extenttest.info("Navigated to URL");
		driver.findElement(By.name("email")).sendKeys("Reva", Keys.ENTER);
		extenttest.info("Enter email id in TextBox");
		String expectedTitle = "Log in to Facebook";
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Title mismatched");
		extenttest.fail("assertion is fail for Facebook ");
	}
	
	
	@Test(testName = "TestOrangeHrm", groups= {"regression"})
	public void TestOrangeHrm()
	{
		driver.get("http://orangehrm.qedgetech.com");
		extenttest.info("Navigated to URL");
	    driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Qedge123!@#1");
		driver.findElement(By.id("btnLogin")).click();
		extenttest.info("Enter details and click");
		
		WebElement welcome = driver.findElement(By.partialLinkText("Welcome"));
		//Assert.assertTrue(welcome.isDisplayed(), "Login failed: Welcome link not displayed!");
		boolean res = welcome.isDisplayed();
		Assert.assertTrue(res);
		//extenttest.fail("assertion is fail for OrangeHrm");
		extenttest.pass("assertion is passed");
	}

}
