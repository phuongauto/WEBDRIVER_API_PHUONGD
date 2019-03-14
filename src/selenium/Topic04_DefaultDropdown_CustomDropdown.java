package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic04_DefaultDropdown_CustomDropdown {
  WebDriver driver;
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @Test
  public void TC01_DefaultDropdown() throws Exception {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
/*Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
Step 04 - Kiểm tra giá trị đã được chọn thành công
Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
Step 06 - Kiểm tra giá trị đã được chọn thành công
Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
Step 08 - Kiểm tra giá trị đã được chọn thành công
Step 09 - Kiểm tra dropdown có đủ 5 giá trị
*/	
	  //Khoi tao 1 bien dang webelement dai dien cho cai dropdown
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
  @Test
  public void TC02_CustomDropdown () {
	  
  }
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
