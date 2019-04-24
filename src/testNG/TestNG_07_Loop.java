package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_07_Loop {
  WebDriver driver;  
  
  
  @Parameters("browser")
  @BeforeTest
  public void preCondition(String browserName) {
	  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

  // (invocationCount=3) đặt cạnh @Test để nó chạy lặp lại 3 lần
  
  @Parameters({"username","password"})
  @Test(invocationCount=3)
  public void TC01_LoginWithValidInformation(String username, String password) {
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
	  driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	  
	  driver.findElement(By.xpath("//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
  }

  
 @AfterTest
 public void postCondition() {
	 driver.quit();
 }
 
}
