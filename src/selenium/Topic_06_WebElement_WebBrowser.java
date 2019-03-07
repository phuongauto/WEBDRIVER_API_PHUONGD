package selenium;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Topic_06_WebElement_WebBrowser {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18Radio = By.xpath("//label[@for='under_18']");
	By eduTextarea = By.xpath("//textarea[@id='edu']");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By interestDevelopment = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonEnable = By.xpath("//button[@id='button-enabled']");
	By password = By.xpath("//input[@name='user_pass']");
	By radioDisabled = By.xpath("//input[@id=''radio-disabled']");
	By biography = By.xpath("//textarea[@id='bio']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By interestDisabled = By.xpath("//input[@id='check-disbaled']']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonDisabled = By.xpath("//button[@id='button-disabled']");
	
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	 
	// Fuction check element display
	public boolean isElementDisplayed(By ok) {
		if (driver.findElement(ok).isDisplayed()) {
			System.out.println("Element " + ok + " is displayed" );
			return true;
		} else 
			{
			System.out.println("Element " + ok + " is not displayed" );
			return false;
			}
		
	}	
	
	public boolean isElementEnable(By tc2) {
		if (driver.findElement(tc2).isEnabled()) {
			System.out.println("Element " + tc2 + "is enabled");
			return true;
		} else 
			{
				System.out.println("Element " + tc2 + "is disabled");
				return false;
			}
	
	}
	
	@Test
	public void TC01_CheckElementIsDisplayed() {
		//Step 1: Open the URL
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		//Step 2: Check Email/ Age (Under 18)/ Education are displayed then fill data
		if (isElementDisplayed(emailTextbox)) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		if (isElementDisplayed(eduTextarea)) {
			driver.findElement(eduTextarea).sendKeys("Automation Testing");
		}
		if (isElementDisplayed(ageUnder18Radio)) {
			driver.findElement(ageUnder18Radio).click();
		}
		
	}
	
	
	
//	@Test
//	public void TC02_CheckElementIsEnableDisable() {
//	  //Step 1: Open the URL
//	  driver.get("https://daominhdam.github.io/basic-form/index.html");
//	  //Step 2: Check Email/ Age (Under 18)/ Education are enable/disable then fill data
//	  if (isElementEnable(emailTextbox)) {
//		  driver.findElement(emailTextbox)
//		 
//		
//	}
//	}
	 


	@AfterTest
	public void afterTest() {
	}
}
