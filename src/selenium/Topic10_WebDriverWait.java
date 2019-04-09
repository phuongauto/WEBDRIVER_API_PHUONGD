package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic10_WebDriverWait {
  WebDriver driver;
  WebDriverWait waitExplicit;
  JavascriptExecutor js;
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  js = (JavascriptExecutor) driver;
  }
  
  
  @Test
  public void TestScript01_ImplicitWait() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  /*Step 01 - Truy cập vào trang: 
		http://the-internet.herokuapp.com/dynamic_loading/2
		Step 02 - Define an implicit wait (If you set 2 seconds, test will fail)
		Step 03 - Click the Start button
		Step 04 - Wait result text will appear
		Step 05 - Check result text is "Hello World!" */
	  
	  WebElement startButton = driver.findElement(By.xpath("//div[@id='start']"));
	  WebElement loadingBar = driver.findElement(By.xpath("//div[@id='loading']"));
	  WebElement textHello = driver.findElement(By.xpath("//div[@id='finish']/h4"));
	  startButton.click();
	  
  }
  
  
  @AfterTest
  public void afterTest() {
	  
  }

}
