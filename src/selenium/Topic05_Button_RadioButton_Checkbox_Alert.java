package selenium;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic05_Button_RadioButton_Checkbox_Alert {
    WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
	@Test  
  public void TC_01_Move_mouse_to_element() {  

		
	}
  @Test
  public void TC_02_Click_and_hold_element__select_multiple_item() {

  }
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
}