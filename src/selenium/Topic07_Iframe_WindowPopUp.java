package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic07_Iframe_WindowPopUp {
  WebDriver driver;
  JavascriptExecutor js;
  WebDriverWait waitExplicit;
  Actions action;
  Alert alert;
  Select select;
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  js = (JavascriptExecutor) driver;
	  waitExplicit = new WebDriverWait(driver, 30);
	  action = new Actions(driver);
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }
  
  public void isDisplayed(String elementXpath) {
	  
	  
  }
  
  
  
  @Test
  public void TC01() {
	  driver.get("http://hdfcbank.com");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  int notificationIframeSize = notificationIframe.size();
	  
	  if(notificationIframeSize > 0) {
		  driver.switchTo().frame(notificationIframe.get(0));
		  driver.findElement(By.xpath("//div[@id='div-close']")).click();
	  }
	  
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
	  
  }
  
  //@Test
  public void TC02() {
	  
	  
  }
  
  //@Test
  public void TC03() {
	  
	  
  }
  
  //@Test
  public void TC04() {
	  
	  
  }
  
  
  
  @AfterTest
  public void afterTest() {
	  
  }

}
