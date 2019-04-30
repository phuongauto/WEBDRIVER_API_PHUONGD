package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic04_DefaultDropdown_CustomDropdown {
  WebDriver driver;
  WebDriverWait waitExplicit;
  JavascriptExecutor javascriptExecutor;
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  waitExplicit = new WebDriverWait(driver, 10);
	  javascriptExecutor = (JavascriptExecutor) driver;
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
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
		  if (allItems.get(i).getText().equals(expectedvalueItem)) {
			  
			  //3. Scroll tới item cần chọn (javascript executor scroll to element)
			  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", allItems.get(i));
			  Thread.sleep(2000);
			  //4. Click vào item cần chọn
			  if(allItems.get(i).isDisplayed() ) {
				  allItems.get(i).click();
			  }		else {
				  			javascriptExecutor.executeScript("arguments[0].click()", allItems.get(i));
			  			 }
			  Thread.sleep(2000);
			  break;
		  }
	  }
	  /* Sử dụng cái for-each này cũng được, thay cho vòng lặp For bên trên
	  
	  for(WebElement childElement: allItems) {
		  if(childElement.getText().equals(expectedvalueItem)) {
			  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", childElement);
			  Thread.sleep(2000);
			  if(childElement.isElementDisplayed())
			  {
			  	//selenium click
			  	childElement.click();
			  }	else 	
			  			{
			  				//javascript click
			  				javascriptExecutor.executeScript("arguments[0].click()", childElement);
			  			}
			  Thread.sleep(2000);
			  break;
		  }
	  }*/
  }

 
  @Test(enabled = true)
  public void TC01_DefaultDropdown() throws Exception {

	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  
	  //Khoi tao 1 bien dang WebElement dai dien cho cai dropdown
	  WebElement jobRole01 = driver.findElement(By.xpath("//select[@id='job1']"));
	  //Su dung thu vien Select de handle cai dropdown
	  Select jobRoleSelect = new Select(jobRole01);
	  
	  //Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
	  jobRoleSelect.selectByVisibleText("Automation Tester");
	  Thread.sleep(2000);
	  
	  //Kiểm tra giá trị đã được chọn thành công
	  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Automation Tester");
	  
	  //Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
	  jobRoleSelect.selectByIndex(3);
	  Thread.sleep(2000);
	  
	  //Kiểm tra giá trị đã được chọn thành công
	  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Mobile Tester");
	  
	  //Kiểm tra dropdown có đủ 5 giá trị
	  Assert.assertEquals(jobRoleSelect.getOptions().size(), 5);
	  Thread.sleep(2000);
  }
  
  @Test(enabled = true)
  public void TC02_CustomDropdown_jqueryui () throws Exception {
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	  selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li/div", "19");
	  //5. Kiểm tra giá trị đã được chọn thành công
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']"));  
  }
  
  @Test(enabled = true)
  public void TC03_CustomDropdown_Material_Angular () throws Exception {
	  driver.get("https://material.angular.io/components/select/examples");
	  selectItemInCustomDropdown("//mat-select[@placeholder='State']", "//mat-option/span", "California");
	  Assert.assertTrue(isElementDisplayed("//div[@class='mat-select-value']//span[@class='ng-tns-c21-18 ng-star-inserted']"));
  }
  
  @Test(enabled = true)
  public void TC04_Custom_Dropdown_Telerik_CapColor () throws Exception {
	  driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
	  selectItemInCustomDropdown("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/li", "Orange");
	  Assert.assertTrue(isElementDisplayed("//span[@aria-owns='color_listbox']//span[text()='Orange']"));  
  }
  
  @Test(enabled = true)
  public void TC05_Custom_Dropdown_Telerik_Capsize () throws Exception {
	  driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
	  selectItemInCustomDropdown("//span[@aria-owns='size_listbox']", "//ul[@id='size_listbox']/li", "XL - 7 5/8");
	  Assert.assertTrue(isElementDisplayed("//span[@aria-owns='size_listbox']//span[@class='k-input']"));
  }	  
  
  @Test(enabled = true)
  public void TC6_Custom_Dropdown_Vue() throws Exception {
	  driver.get("https://mikerodham.github.io/vue-dropdowns/");
	  selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
	  Assert.assertTrue(isElementDisplayed("//li[@class='dropdown-toggle']"));
  }
  
  //Hàm nay Phuoc viet, mình chưa hiểu
  @Test
  public void TC07_Custom_Dropdown_Indrimuska_Editable() throws Exception {
	  driver.get("http://indrimuska.github.io/jquery-editable-select/");
		driver.findElement(By.xpath("//div[@id='default-place']/input")).click();
		List<WebElement> listCar = driver.findElements(By.xpath("//div[@id='default-place']//li"));
		System.out.println("size" + listCar.size());
		for (WebElement car : listCar) {
			System.out.println("list car is: " + car.getText());
			if(car.getText().equals("Nissan")) {
				car.click();
			}
		}
		Assert.assertTrue(isPresent(By.xpath("//div[@id='default-place']//li[@class='es-visible' and contains(text(),'Nissan')]")));
  }
  
  //Hàm này chạy kèm TC07. Phải check nó isPresent vì element chỉ hiển thị trong DOM chứ không hiện lên UI
  public boolean isPresent(By value) {
	  
	  if (driver.findElements(value).size()>0) 
	  {
		  return true;
	  }
	  else
	  {
		  return false;
	  }
	  
	  
  }
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
