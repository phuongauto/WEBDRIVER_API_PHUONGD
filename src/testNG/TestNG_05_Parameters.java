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

public class TestNG_05_Parameters {
  WebDriver driver;
  
  @BeforeTest
  public void preCondition() {
	  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }
  
  
  // truyền 2 biến chạy = parameter từ bên file xml vào cái @Test này 
  // bên xml ta sẽ có 2 dòng sau:
  // <parameter name ="username" value="mngr181356@gmail.com"/>  
  // <parameter name ="password" value="123456"/> 
  
  
  @Parameters({"username","password"})
  @Test
  public void TC01_LoginWithValidInformation(String username, String password) {
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
