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
	By ageUnder18Radio = By.xpath("//input[@id='under_18']");
	By eduTextarea = By.xpath("//textarea[@id='edu']");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By interestDevelopment = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonEnable = By.xpath("//button[@id='button-enabled']");
	By password = By.xpath("//input[@name='user_pass']");
	By radioDisabled = By.xpath("//input[@id='radio-disabled']");
	By biography = By.xpath("//textarea[@id='bio']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By interestDisabled = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonDisabled = By.xpath("//button[@id='button-disabled']");
	
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	 
// 	Fuction check element display
//	public boolean isElementDisplayed(By ok1) {
//		if (driver.findElement(ok1).isDisplayed()) {
//			System.out.println("Element " + ok1 + " is displayed" );
//			return true;
//		} else 
//		{
//			System.out.println("Element " + ok1 + " is not displayed" );
//			return false;
//		}
//		
//	}	
//	public boolean isElementEnabled(By ok2) {
//		if (driver.findElement(ok2).isEnabled()) {
//			System.out.println("Element " + ok2 + " is enabled" );
//			return true;
//		} else 
//		{
//			System.out.println("Element " + ok2 + " is disabled" );
//			return false;
//		}
//	}	
//	public boolean isElementSelected(By ok3) {
//		if (driver.findElement(ok3).isSelected()) {
//			System.out.println("Element " + ok3 + " is selected" );
//			return false;
//		} else 
//		{
//			System.out.println("Element " + ok3 + " is not selected" );
//			return true;
//		}
//	}	
	public boolean isElementSelected(By Value) {
		return driver.findElement(Value).isSelected();	
	}	
	public boolean isElementEnabled(By Value) {
		return driver.findElement(Value).isEnabled();
	}
	public boolean isElementDisplayed(By Value) {
		return driver.findElement(Value).isDisplayed();
	}
	@Test
	public void TC01_CheckElementIsDisplayed() {
		System.out.println("**********Start Test case 01:\n");
		//Step 1: Open the URL
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		//Step 2: Check Email/ Age (Under 18)/ Education are displayed then fill data
		if (isElementDisplayed(emailTextbox)) {
			System.out.println("Element Email is displayed");
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
			System.out.println("Successfully input ''Automation Testing'' to Email field");
		}
		if (isElementDisplayed(eduTextarea)) {
			System.out.println("Element Education is displayed");
			driver.findElement(eduTextarea).sendKeys("Automation Testing");
			System.out.println("Successfully input ''Automation Testing'' to Education field");
		}
		if (isElementDisplayed(ageUnder18Radio)) {
			System.out.println("Element Age (Under 18) is displayed");
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Successfully select Age Under 18");
			
		}
	}
	@Test
	public void TC02_CheckElementIsEnabled() {
		//Step 1: Open the URL
		System.out.println("\n**********Start Test case 02:\n");
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		if (isElementEnabled(emailTextbox)) {
			System.out.println("Element Email is enabled");
		}	else {
			System.out.println("Element Email is disabled");
		}
		if (isElementEnabled(ageUnder18Radio)) {
			System.out.println("Element Age under 18 is enabled");
		}	else {
			System.out.println("Element Age under 18 is disabled");
		}
		if (isElementEnabled(eduTextarea)) {
			System.out.println("Element Education is enabled");
		}	else {
			System.out.println("Element Education is disabled");
		}
		if (isElementEnabled(jobRole01)) {
			System.out.println("Element Job Role 01 is enabled");
		}	else {
			System.out.println("Element Job Role 01 is disabled");
		}
		if (isElementEnabled(interestDevelopment)) {
			System.out.println("Element Interests (Development) is enabled");
		}	else {
			System.out.println("Element Interests (Development) is disabled");
		}
		if (isElementEnabled(slider01)) {
			System.out.println("Element Slider 01 is enabled");
		}	else {
			System.out.println("Element Slider 01 is disabled");
		}
		if (isElementEnabled(buttonDisabled)) {
			System.out.println("Element Button is Disabled is enabled");
		}	else {
			System.out.println("Element Email is disabled");
		}
		if (isElementEnabled(buttonEnable)) {
			System.out.println("Element Button is Enabled is enabled");
		}	else {
			System.out.println("Element Button is Enabled is disabled");
		}
		if (isElementEnabled(password)) {
			System.out.println("Element Password is enabled");
		}	else {
			System.out.println("Element Password is disabled");
		}
		if (isElementEnabled(radioDisabled)) {
			System.out.println("Element Age (Radiobutton is disabled) is enabled");
		}	else {
			System.out.println("Element Age (Radiobutton is disabled) is disabled");
		}
		if (isElementEnabled(biography)) {
			System.out.println("Element Biography is enabled");
		}	else {
			System.out.println("Element Biography is disabled");
		}
		if (isElementEnabled(jobRole02)) {
			System.out.println("Element Job Role 02 is enabled");
		}	else {
			System.out.println("Element Job Role 02 is disabled");
		}
		if (isElementEnabled(interestDisabled)) {
			System.out.println("Element Interests (Checkbox is disabled) is enabled");
		}	else {
			System.out.println("Element Interests (Checkbox is disabled) is disabled");
		}
		if (isElementEnabled(slider02)) {
			System.out.println("Element Slider 02 is enabled");
		}	else {
				System.out.println("Element Slider 02 is disabled");
			}	
		}
	@Test
	public void TC03_CheckElementIsSelected() {
		System.out.println("\n**********Start Test case 03:\n");
		//Step 1: Open the URL
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(interestDevelopment).click();
		
		if (isElementSelected(ageUnder18Radio)) {
			System.out.println("Element Age Under 18 is selected");
		} else {
			driver.findElement(ageUnder18Radio).click();
		}
		if (isElementSelected(interestDevelopment)) {
			System.out.println("Element Interest Development is selected");
		} else {
			driver.findElement(interestDevelopment).click();
		}
	}
	@AfterTest
	public void afterTest() {
	}
}
