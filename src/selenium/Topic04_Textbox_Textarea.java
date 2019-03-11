package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic04_Textbox_Textarea {
	WebDriver driver;
	
	@BeforeTest
	  public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4");
	  }
	
	@Test
	public void f() {
		
		//Login
		driver.findElement(By.xpath("//input[@type='submit']")).sendKeys("mngr183874");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qadYnYv");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		WebElement loginsuccess_msg = driver.findElement(By.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
		Assert.assertEquals(loginsuccess_msg, "Welcome To Manager's Page of Guru99 Bank");
		//Click on New Customer menu
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		//Verify we are on Add New Customer form
		WebElement addNewCus_success_msg = driver.findElement(By.xpath("//p[text()='Add New Customer']"));
		Assert.assertEquals(addNewCus_success_msg, "Add New Customer");
		//Input data to fields
		
		
	  
	}
  
	
	@AfterTest
	public void afterTest() {
	}

}
