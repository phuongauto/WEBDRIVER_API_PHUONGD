package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SelectItemInCustomDropdown {
	 WebDriver driver;
	 WebDriverWait waitExplicit;
	 JavascriptExecutor javascriptExecutor;

  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  waitExplicit = new WebDriverWait(driver, 30);
	  javascriptExecutor = (JavascriptExecutor) driver;
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedvalueItem ) {
	  //xpath parent là cái mà mình click vào chỗ nào nó cũng xổ dropdown ra là được(viền, mũi tên,...)
	  
	  //1. Click vao cai dropdown cho no xo het tat ca cac gia tri ra.
	  // parentDropdown la 1 WebElement đại diện cho cái dropdown, parentXpath là xpath của nó
	  WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
	  javascriptExecutor.executeScript("arguments[0].click()", parentDropdown);
	  
	  //2. Chờ cho tất cả item trong dropdown được load ra thành công
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	  
	  List <WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
	  
	  
	  System.out.println("Tat ca phan tu trong dropdown =" + allItems.size());
	  //Duyệt qua hết tất cả các phần tử cho tới khi thỏa mãn điều kiện
	  for(int i=0; i<allItems.size(); i++) {
		  // ví dụ allItems.get(0) = một cái WebElement để mà click/sendkey/getText/...
		  if (allItems.get(i).getText().equals(expectedvalueItem)) 
		  	{
			  System.out.println("Text moi lan chon duoc la:" + allItems.get(i).getText());
			  
			  //3. Scroll tới item cần chọn (javascript executor scroll to element)
			  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", allItems.get(i));
			  
			  //4. Click vào item cần chọn
			  allItems.get(i).click();
			  break;
		  	}
		  }
	  }
	  
	  /*for-each
	  for(WebElement childElement: allItems) {
		  if(childElement.getText().equals(expectedvalueItem)) {
			  System.out.println("Text moi lan chon duoc la:" + childElement.getText());
			  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", childElement);
				if (childElement.isDisplayed()){
				
			  	//selenium click 
			  	childElement.click();}
			  	else
			  	{
			  		javascriptExecutor.executeScript("arguments[0].click()", childElement);
			  	}
			  Thread.sleep(2000);	
			  break;
		  }
	  }*/
  @Test
  public void TC02_CustomDropdown_jqueryui () {
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	  selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li/div", "19");
	  //5. Kiểm tra giá trị đã được chọn thành công
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']"));
  }
  public boolean isElementDisplayed(String xpathValue) {
	  WebElement element = driver.findElement(By.xpath(xpathValue));
	  if(element.isDisplayed()) {
		  System.out.println("Element is displayed");
		  return true;
	  } else {
		  return false;
	  }
  }
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
