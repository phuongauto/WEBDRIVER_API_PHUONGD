package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_03_DataProvider {
  WebDriver driver;
  
  @BeforeTest
  public void preCondition() {

	  System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  
  }

  
  
  @Test(dataProvider = "userPassInfor")
  public void TC01_LoginWithValidInformation(String username, String password) {
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
	  driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	  
	  driver.findElement(By.xpath("//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
  }

 @DataProvider
 public Object[][] userPassInfor(){
	return new Object[][] {
		{"mngr181356@gmail.com", "123456"},
		{"mngr181358@gmail.com", "123456"},
		{"mngr181359@gmail.com", "123456"}};
 }
  
 @AfterTest
 public void postCondition() {
	 driver.quit();
 }
 
}
