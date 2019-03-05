package selenium;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Part_02 {
	WebDriver driver;

	
	 @BeforeTest
	  public void beforeTest() {
		 driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	 

	 @Test
	 
	 public void TC_05() throws InterruptedException {
			Random rand = new Random(); 
			int value = rand.nextInt(50); 
		 driver.get("http://live.guru99.com/");
		 //Click to open My Account on Footer
		 	//xpath parent note
		 driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		 	// xpath 	//div[@class='footer']//a[contains(text(),'Account')]
		 	// xpath	//div[@class='footer']//a[contains(@title,'My Account')]
		 	// xpath	//div[@class='footer']//a[text()='My Account']
		 
		 //Click on Create an account button
		 driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		 //Input data to all fields
		 driver.findElement(By.xpath("//div[@class='field name-firstname']//input")).sendKeys("phuong");
		 driver.findElement(By.xpath("//div[@class='field name-middlename']//input")).sendKeys("");
		 driver.findElement(By.xpath("//div[@class='field name-lastname']//input")).sendKeys("dam");
		 String email = "cantsleep" + rand + "@gmail.com";
		 driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
		 //no tao ra cai nay trong email field: cantsleepjava.util.Random@6a6afff2@gmail.com
		 
		 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
		 driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("12345678");
		 driver.findElement(By.xpath("//button[@title='Register']")).click();
		 //
		 Thread.sleep(5000);
		 // sang trang http://live.guru99.com/index.php/customer/account/index/
		 String success_msg = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		 Assert.assertEquals(success_msg, "Thank you for registering with Main Website Store.");
		 //Open Account on Header 
		 driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@href='http://live.guru99.com/index.php/customer/account/']")).click();
		 driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
	 }
 
	 
	 @AfterTest
	  public void afterTest() {
			driver.quit();
	 }

}
