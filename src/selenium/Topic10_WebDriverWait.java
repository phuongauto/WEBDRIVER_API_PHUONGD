package selenium;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Function;


public class Topic10_WebDriverWait {
  WebDriver driver;
  WebDriverWait waitExplicit;
  JavascriptExecutor js;
  By startButton = By.xpath("//div[@id='start']/button");
  By loadingIcon = By.xpath("//div[@id='loading']/img");
  By helloText = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");
  
  
  
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
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
  		- đánh fail test case
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
  
 // @Test
  public void TC02_ExplicitWait_Invisible() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  // ở đây nếu set Explicit là 5 trở lên thì sẽ pass vì cái Loading mất 5s để biến mất
	  waitExplicit = new  WebDriverWait(driver, 5);
	  driver.findElement(startButton).click();
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
	  Assert.assertTrue(driver.findElement(helloText).isDisplayed());
  }
  
  //@Test
  public void TC03_ExplicitWait_HelloWorld_Visible() {
	  // ở đây nếu set là 5 trở lên thì sẽ pass vì cái Loading mất 5s để biến mất
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  waitExplicit = new  WebDriverWait(driver, 5);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.findElement(startButton).click();
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloText));
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
  }
  
  //@Test
  public void TC04_ExplicitWait_Time() {
	  driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	  waitExplicit = new  WebDriverWait(driver, 5);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
	  // helloText lúc này Invisible + ko co trong DOM
	  System.out.println("Start time: "+ getDateTimeSecond());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloText));
	  System.out.println("End time: "+ getDateTimeSecond());
	  
	  // loadingIcon lúc này Invisible + ko co trong DOM
	  System.out.println("Start time: "+ getDateTimeSecond());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	  System.out.println("End time: "+ getDateTimeSecond());
	  
	  System.out.println("Start time: "+ getDateTimeSecond());
	  driver.findElement(startButton).click();
	  System.out.println("End time: "+ getDateTimeSecond());
	  
	  // ko visible + co trong DOM
	  System.out.println("Start time: "+ getDateTimeSecond());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	  System.out.println("End time: "+ getDateTimeSecond());
	  
	  // ko visible + co trong DOM
	  System.out.println("Start time: "+ getDateTimeSecond());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(startButton));
	  System.out.println("End time: "+ getDateTimeSecond());
  }
  /*@Test 
  public void verifySliderHomepage() {
	  driver.get("https://www.kikki-k.com/row/home?redirect=true");
	  List <WebElement> siteSelectorPopup = driver.findElements(By.xpath("//div[@id='site-selector-popup']"));
	  int siteSelectorPopup_size = siteSelectorPopup.size();
	  if(siteSelectorPopup_size >0) {
		  WebElement countryPicker = driver.findElement(By.xpath("//div[@id='site-selector-popup']//li[@class='site-switcher-header-popup']"));
		  countryPicker.click();
		  List <WebElement> countries = driver.findElements(By.xpath("//div[@id='site-selector-popup']//ul[@class='site-switcher-popup']//ul/li"));
		  for(WebElement country: countries) {
			  
		  }
	  }
  }*/
  
  public Date getDateTimeSecond() {
	  Date date = new Date();
	  date = new java.sql.Timestamp(date.getTime());
      return date;
  }
  
  //@Test
  public void TC05_Ajax_noExplicitWait() {
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='calendarContainer']")).isDisplayed());
	  WebElement beforeSelectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
	  
	  // Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
	  System.out.println("Before Ajax call: " + beforeSelectedDate.getText());
	  
	  // Step 04 - Chọn ngày hiện tại (VD: 23/09/2017) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
	  driver.findElement(By.xpath("//a[text()='30']")).click();
	  
	  // Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility)
	  
	  WebElement Ajax = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1']//div[@class='raDiv']"));
	  
	  // Step 06 -  Wait cho selected date = 30 được visible
	  WebElement selectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Tuesday, April 30, 2019']"));
	  Assert.assertTrue(selectedDate.isDisplayed());
	  
	  // Step 07 - Verify ngày đã chọn
	  String afterSelectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
	  System.out.println("Selected Date: " + afterSelectedDate);
  }
  
  //@Test
  public void TC05_Ajax_ExplicitWait() {
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  waitExplicit = new WebDriverWait(driver, 10);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='calendarContainer']")).isDisplayed());
	  WebElement beforeSelectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
	  
	  // Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
	  System.out.println("Before Ajax call: " + beforeSelectedDate.getText());
	  
	  // Step 04 - Chọn ngày hiện tại (VD: 23/09/2017) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
	  driver.findElement(By.xpath("//a[text()='29']")).click();
	  
	  // Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility)
	  //WebElement Ajax = driver.findElement(By.xpath("//div[@class='raDiv']"));
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
	  
	  // Step 06 -  Wait cho selected date = 30 được visible
	  WebElement selectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Monday, April 29, 2019']"));
	  waitExplicit.until(ExpectedConditions.visibilityOf(selectedDate));
	  
	  // Step 07 - Verify ngày đã chọn
	  Assert.assertTrue(selectedDate.isDisplayed());
  }
  
  @Test
  public void TC06_FluentWait() {
	  driver.get("https://daominhdam.github.io/fluent-wait/");
	  
	  waitExplicit = new WebDriverWait(driver, 5);
	  
	  WebElement countDown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	  waitExplicit.until(ExpectedConditions.visibilityOf(countDown));
	  
	  // Step 03 - Sử dụng Fluent wait để:
	  new FluentWait<WebElement>(countDown)
      // Tổng time wait là 15s
      .withTimeout(15, TimeUnit.SECONDS)
       // Tần số mỗi 1s check 1 lần
       .pollingEvery(1, TimeUnit.SECONDS)
       // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
       // import java.util.NoSuchElementException;
       .ignoring(NoSuchElementException.class)
       // Kiểm tra điều kiện
       // import com.google.common.base.Function;
       .until(new Function<WebElement, Boolean>() {
           public Boolean apply(WebElement element) {
                      // Kiểm tra điều kiện countdount = 00
                      boolean flag =  element.getText().endsWith("00");
                      System.out.println("Time = " +  element.getText());
                      // return giá trị cho function apply
                      return flag;
           }
      });
  }
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
