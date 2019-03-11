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

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Topic_03_WebElements_WebDriver_Another_Solution {
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
	public int randomNumber() {
		Random random = new Random();
		int number =random.nextInt(999999);
		System.out.println("Random number = " + number);
		return number;
	}
	public boolean isElementDisplay(By byValue) {
		if (driver.findElement(byValue).isDisplayed()) {
			System.out.println("Element [" + byValue + "] is displayed");
			return true;
		}	else {
			System.out.println("Element [" + byValue + "] is not displayed");
			return false;
		}
	}
	public boolean isElementEnable(By byValue) {
		if (driver.findElement(byValue).isEnabled()) {
			System.out.println("Element [" + byValue + "] is enable");
			return true;
		}	else {
			System.out.println("Element [" + byValue + "] is disable");
			return false;
		}
	}
	public boolean isElementSelected(By byValue) {
		if (driver.findElement(byValue).isSelected()) {
			System.out.println("Element [" + byValue + "] is selected");
			return true;
		}	else {
			System.out.println("Element [" + byValue + "] is not selected");
			return false;
		}
	}
	@Test
	public void TC02_check_enable() {
		System.out.println("**********Start Test case 02:\n");
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnable(emailTextbox));
		Assert.assertTrue(isElementEnable(ageUnder18Radio));
		Assert.assertTrue(isElementEnable(eduTextarea));
		Assert.assertTrue(isElementEnable(jobRole01));
		Assert.assertTrue(isElementEnable(interestDevelopment));
		Assert.assertTrue(isElementEnable(slider01));
		Assert.assertTrue(isElementEnable(buttonEnable));
		Assert.assertFalse(isElementEnable(password));
		Assert.assertFalse(isElementEnable(radioDisabled));
		Assert.assertFalse(isElementEnable(biography));
		Assert.assertFalse(isElementEnable(jobRole02));
		Assert.assertFalse(isElementEnable(interestDisabled));
		Assert.assertFalse(isElementEnable(slider02));
		Assert.assertFalse(isElementEnable(buttonDisabled));	
	}

	
	@AfterTest
	public void afterTest() {
	}

}
