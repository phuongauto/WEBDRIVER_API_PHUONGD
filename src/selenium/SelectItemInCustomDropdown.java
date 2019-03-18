package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
  
  public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedvalueItem ) throws Exception {
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
			  //3. Scroll tới item cần chọn (javascript executor scroll to element)
			  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", allItems.get(i));
			  Thread.sleep(2000);
			  if (allItems.get(i).isDisplayed())
			  {
				  allItems.get(i).click();
			  }	else 
			  		{
				  		javascriptExecutor.executeScript("arguments[0].click()", allItems.get(i));
			  		}
			  Thread.sleep(2000);
			  break;
		  	}
		  }
	  }
	  
	  /*for-each
	  for(WebElement childElement: allItems) {
		  if(childElement.getText().equals(expectedvalueItem)) {
			  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", childElement);
			  Thread.sleep(2000);
			  if (childElement.isDisplayed())
			    {
			  		//selenium click 
			  		childElement.click();
			  	} else
			  		{
			  			//javascript click
			  			javascriptExecutor.executeScript("arguments[0].click()", childElement);
			  		}
			  Thread.sleep(2000);	
			  break;
		  }
	  }*/
  @Test (enabled = false)
  public void TC02_CustomDropdown_jqueryui () throws Exception {
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
  
  public void  select_multi_items(String parent_xpath, String allItems_xpath, String expectedItems) throws Exception {
	  // khai báo element đại diện cho cái dropdown và gán giá trị xpath cho nó
	  WebElement skillDropdown = driver.findElement(By.xpath(parent_xpath));
	  javascriptExecutor.executeScript("arguments[0].click()", skillDropdown);
	  //skillDropdown.click();
	  
	  //khai báo, khởi tạo 1 biến đại diện tất cả list element trong dropdown đó và gán giá trị xpath cho nó
	  List <WebElement> allMultiItems = driver.findElements(By.xpath(allItems_xpath));
	  
	  //chờ tới khi toàn bộ list element hiện ra đủ 
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItems_xpath)));
	  
	  //duyệt qua từng item trong dropdown
	  for (int i=0; i<allMultiItems.size(); i++) {
		  if (allMultiItems.get(i).getText().equals(expectedItems)) {
			  javascriptExecutor.executeScript("arguments[0].scrollIntoView()", allMultiItems.get(i));
			  if(allMultiItems.get(i).isDisplayed()) {
				  allMultiItems.get(i).click();	
				  Thread.sleep(2000);
			  }   else  {
				  			javascriptExecutor.executeScript("arguments[0].click()", allMultiItems.get(i));
			  			}
			  Thread.sleep(2000);
			  break;
		  }
		  
	  }
  }
  @Test
  public void TC03_select_multi_item() throws Exception {
	  driver.get("https://semantic-ui.com/modules/dropdown.html");
	  select_multi_items("//div[@tabindex='0']//select[@name='skills']", "//select[@name='skills']/following-sibling::div[@tabindex='-1']/div", "Python");
	  select_multi_items("//div[@tabindex='0']//select[@name='skills']", "//select[@name='skills']/following-sibling::div[@tabindex='-1']/div", "CSS");
	  //click chỗ khác để đóng dropdown lại
	  driver.findElement(By.xpath("//h4[text()='Search Selection']")).click();
  }
  @AfterTest
  public void afterTest() {
	  //driver.quit();
  }

}
