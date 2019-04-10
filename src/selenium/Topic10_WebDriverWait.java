package selenium;
import java.security.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic10_WebDriverWait {
  WebDriver driver;
  WebDriverWait waitExplicit;
  JavascriptExecutor js;
  By startButton = By.xpath("//div[@id='start']/button");
  By loadingIcon = By.xpath("//div[@id='loading']/img");
  By helloText = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  js = (JavascriptExecutor) driver;
  }
  //@Test
  public void TC01_ImplicitWait_fail() {
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  /* ở đây sau khi click nút start thì sau 5s nó mới hiện cái helloText, do đó 
	  		- không có wait cũng fail: vì mỗi lệnh được thực thi cách nhau 0.5 giây. nó click xong nửa giây sau nó đi tìm helloText luôn => ko có, nó cứ đi tìm sau 0.5s cho tới khi hết timeout => ko có => fail
	  		- có wait nhưng mà wait < 5s: cũng fail
	  		- wait > 5s: pass
	  */ 
	  /*Step 01 - Truy cập vào trang: 
		http://the-internet.herokuapp.com/dynamic_loading/2
		Step 02 - Define an implicit wait (If you set 2 seconds, test will fail)
		Step 03 - Click the Start button
		Step 04 - Wait result text will appear
		Step 05 - Check result text is "Hello World!" */
	  driver.findElement(startButton).click();
	  Assert.assertTrue(driver.findElement(helloText).isDisplayed());
  }
  //@Test
  public void TC02_ImplicitWait_pass() { 
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.findElement(startButton).click();
	  Assert.assertTrue(driver.findElement(helloText).isDisplayed());
  }
  /* check element không hiển thị (isDisplayed) có 2 case:
  		- có trong DOM
  		- ko có trong DOM
  	do đó ko nên dùng isDisplayed() để check element không hiển thị
  */
  /*thằng wait implicit ảnh hưởng trực tiếp tới 2 thằng findElement và findElements  
  findElement 
  	nếu ko tìm thấy element trong tgian mình set thì sẽ
  		- chờ hết timeout mà mình set ở implicit
  		- ánh fail test case 
  		- ném ra no such element,... 
  	
  	nếu thấy 1 element thì tương tác với element đó
  	
  	nếu thấy > 1 element thì sẽ tương tác vs element đầu tiên, do đó nên tìm xpath có 1 matching node để có độ chính xác cao nhất
  	
  findElements: List <WebElement> elements = driver.findElements(By.xxx)
  	nếu ko tìm thấy element
  		- chờ hết timeout mà mình set ở implicit
  		- ko đánh fail test case => chạy step tiếp theo
  		- ko throw exception
  		- trả về 1 list empty
  		
  	nếu thấy 1 element: trả về 1 list chứa element matching
  		
  	nếu thấy > 1 element: trả về 1 list chứa các element matching
  	
  DOM: document object model
  cơ chế của 1 trang web: user dùng browser tương tác vs web(client) => gửi request lên server
  server gửi trả dữ liệu về cho client. browser sẽ render dữ liệu để hiển thị cho user nhìn thấy/tương tác
  DOM chưa tất cả đống code HTML, CSS, JS, Jquery,...
  
  có trong DOM thì chạy nhanh còn ko có thì chạy chậm
  
  Thằng wait Explicit: wait rõ ràng: dùng cho các trạng thái của element
  nếu check cho 1 element
  	presence:
  		- có trong DOM(chỉ cần điều kiện này thỏa là pass test case)
  		- người dùng ko tương tác dc
  	visible(cần 2 điều kiện dưới thỏa mãn thì mới pass dc):
  		- có trong DOM
  		- người dùng có thể tương tác dc
  	invisible:
  		- có trong DOM + ko hiển thị
  		- ko có trong DOM
  	
  
  
  */
  
  @Test
  public void TC02_ExplicitWait() {
	  /*check cho loading icon invisible trước khi hello text được hiển thị
	   * implicit wait chỉ set 2s
	   * step 1: vào page
	   * step 2: click start button
	   * step 3: wait loading invisible
	   * step 4: check result text is hello world
	  */
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  waitExplicit = new  WebDriverWait(driver, 5);
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
	  //waitExplicit.until(ExpectedConditions)
  }
  
  
  public Date getDateTimeSecond() {
      Date date = new Date();
      date = new Timestamp(date.getTime());
      return date;
  }
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
