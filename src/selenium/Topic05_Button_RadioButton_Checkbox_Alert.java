package selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic05_Button_RadioButton_Checkbox_Alert {
  WebDriver driver;
  JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  WebElement locateElement;
  }
  @Test
  public void f() {
	  driver.get("https://material.angular.io/components/select/examples");
	  WebElement locateElement = driver.findElement(By.xpath("//div[@class='docs-example-viewer-title-spacer']"));
	  locateElement.getLocation();
	  System.out.println("Location is" + locateElement.getLocation());
	  jsexecutor.executeScript("arguments[0].scrollIntoView(true)", locateElement);
  }

  
  
  
  
  @AfterTest
  public void afterTest() {
	  
  }

}
