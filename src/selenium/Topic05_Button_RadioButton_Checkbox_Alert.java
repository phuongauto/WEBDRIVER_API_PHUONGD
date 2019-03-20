package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic05_Button_RadioButton_Checkbox_Alert {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait	waitExplicit;
    
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  waitExplicit = new WebDriverWait(driver, 30);
	  js = (JavascriptExecutor) driver;
	  
	  
  }
  
  @Test  
  public void TC_01() throws Exception {  
		
		driver.get("http://live.guru99.com/");
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")));
		Thread.sleep(5000);
		String loginURL = "http://live.guru99.com/index.php/customer/account/login/";
		Assert.assertEquals(driver.getCurrentUrl(), loginURL);
		System.out.println("Current URL is: " + loginURL);
		
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@title='Create an Account']")));
		Thread.sleep(5000);
		String createURL = "http://live.guru99.com/index.php/customer/account/create/";
		Assert.assertEquals(driver.getCurrentUrl(), createURL);
  }
  
  
  @Test
  public void TC_02() {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  //click vào label 
	  WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
	  js.executeScript("arguments[0].click()", dualZoneCheckbox);
	  //thay vi dung selenium click thi ta dung javascript de click vì thằng input kia bị ẩn VÀ thẻ label thì không thể dùng isSelect() được
	  Assert.assertTrue(dualZoneCheckbox.isSelected());
	  
	  js.executeScript("arguments[0].click()", dualZoneCheckbox);
	  Assert.assertFalse(dualZoneCheckbox.isSelected());
  }
  
  
  @Test
  public void TC_03() {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
	  //click vào label 
	  WebElement petrol147kw = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
	  js.executeScript("arguments[0].click()", petrol147kw);
	  //thay vi dung selenium click thi ta dung javascript de click vì thằng input kia bị ẩn VÀ thẻ label thì không thể dùng isSelect() được
	  if (petrol147kw.isSelected()) {
	  } else {
		  		js.executeScript("arguments[0].click()", petrol147kw);
	  		 }
	  Assert.assertTrue(petrol147kw.isSelected());
  }

  
  @Test
  public void TC_04() throws Exception {
	  
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	  
	  Alert alert04 = driver.switchTo().alert();
	  String textOnAlert04 = alert04.getText();
	  Assert.assertEquals(textOnAlert04, "I am a JS Alert");
	  
	  alert04.accept();
	  Thread.sleep(2500);
	  WebElement result01 = driver.findElement(By.xpath("//p[@id='result']"));
	  Assert.assertEquals(result01.getText(), "You clicked an alert successfully"); 
  }
  
  @Test
  public void TC_05() throws Exception {
	  
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	  
	  Alert alert05 = driver.switchTo().alert();
	  String textOnAlert05 = alert05.getText();
	  Assert.assertEquals(textOnAlert05, "I am a JS Confirm");
	  
	  alert05.dismiss();
	  Thread.sleep(2500);
	  WebElement result02 = driver.findElement(By.xpath("//p[@id='result']"));
	  Assert.assertEquals(result02.getText(), "You clicked: Cancel"); 
  }
  
  @Test
  public void TC06() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  Alert alert06 = driver.switchTo().alert();
	  String textOnAlert06 =  alert06.getText();
	  Assert.assertEquals(textOnAlert06, "I am a JS prompt");
	  alert06.sendKeys("Phuong Dam is a Software Development Engineer in Test");
	  alert06.accept();
	  WebElement result03 = driver.findElement(By.xpath("//p[@id='result']"));
	  Assert.assertEquals(result03.getText(), "You entered: Phuong Dam is a Software Development Engineer in Test"); 
  }
  
  @Test
  public void TC07() {
	  driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(), "Congratulations! You must have the proper credentials.");  
  }
  @AfterTest
  public void afterTest() {
	  //driver.quit();
  }
}