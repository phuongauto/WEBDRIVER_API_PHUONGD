package selenium;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic06_User_Interactions {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor js;
	Actions action;
	Alert alert;
	
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  action = new Actions(driver);
	  js = (JavascriptExecutor) driver;
	  waitExplicit = new WebDriverWait(driver, 30);
	  
  }

  @Test
  public void TC01_Move_mouse_to_element() throws Exception {
	  driver.get("http://www.myntra.com/");
	  //account: automationtest@mailinator.com passs Auto111!!!
	  
	  //Hover on Profile icon
	  WebElement profileIcon = driver.findElement(By.xpath("//span[contains(@class,'desktop-iconUser')]"));
	  action.moveToElement(profileIcon).perform();
	  
	  Thread.sleep(3000);
	  //Click to Login button
	  WebElement loginBtn = driver.findElement(By.xpath("//a[@class='desktop-linkButton' and text()='log in']"));
	  //loginBtn.click();
	  action.click(loginBtn).perform();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Login to Myntra']")).isDisplayed());  
  }
  
  @Test
  public void TC02_Click_and_hold_element() throws Exception {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  //action.click(driver.findElement(By.xpath("//ol[@class='ui-selectable']/li[text()='1']"))).
	  List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
			
	  //click và giữ chuột để chọn từ số 1 tới số 4 rồi nhả chuột
	  action.clickAndHold(numberItems.get(0)).moveToElement(numberItems.get(3)).release().perform();
	  
	  List <WebElement> numberItemsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  Assert.assertEquals(numberItemsSelected.size(), 4);
  }
  
  
  @Test
  public void TC03_Select_multiple_item() throws Exception {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  
	  action.keyDown(Keys.CONTROL).perform();
	  action.click(numberItems.get(1));
	  action.click(numberItems.get(5));
	  action.click(numberItems.get(8));
	  action.click(numberItems.get(11));
	  action.keyUp(Keys.CONTROL).perform();
	  /*Dung cach nay cung dung
	   action.keyDown(numberItems.get(0), Keys.CONTROL)
	  .click(numberItems.get(2))
	  .click(numberItems.get(11))
	  .click(numberItems.get(6))
	  .click(numberItems.get(8))
	  .click(numberItems.get(10))
	  .keyUp(Keys.CONTROL).perform();*/

	  List <WebElement> numberItemsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  Assert.assertEquals(numberItemsSelected.size(), 4);
  }
  
  @Test
  public void TC04_Double_Click() throws Exception {
	  driver.get("http://www.seleniumlearn.com/double-click");
	  WebElement doubleClickMe = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
	  action.doubleClick(doubleClickMe).perform();
	  alert = driver.switchTo().alert();
	  Assert.assertEquals("The Button was double-clicked.", alert.getText());
	  alert.accept();
  }
  
  @Test
  public void TC05_RightClick() throws Exception {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  //Right click vào element: right click me
	  WebElement rightClickMe = driver.findElement(By.xpath("//span[text()='right click me']"));
	  action.contextClick(rightClickMe).perform();
	  //Step 03 - Hover chuột vào element: Quit
	  action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
	  //Step 04 - Verify element Quit (visible + hover) với xpath
	  Assert.assertEquals(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']")).getText(), "Quit");
	  //Step 05 - Click chọn Quit
	  action.click(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
	  //Step 06 - Accept Javascript alert
	  alert = driver.switchTo().alert();
	  alert.accept();
  }
  
  @Test
  public void TC06_DragAndDrop() throws Exception {
	  driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
	  WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement largeCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
	  action.dragAndDrop(smallCircle, largeCircle).perform();
	  Assert.assertEquals(largeCircle.getText(), "You did great!");
  }
  
  @AfterTest
  public void afterTest() {
  }

}
