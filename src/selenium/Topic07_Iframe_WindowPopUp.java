package selenium;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic07_Iframe_WindowPopUp {
  WebDriver driver;
  JavascriptExecutor js;
  WebDriverWait waitExplicit;
  Actions action;
  Alert alert;
  Select select;
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  js = (JavascriptExecutor) driver;
	  waitExplicit = new WebDriverWait(driver, 30);
	  action = new Actions(driver);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void TC01() {
	  driver.get("http://hdfcbank.com");
	  /*Tại sao phải wait 10 giây? 
	   * Vì khi selenium đi tìm elements trong 1 list, nếu nó không tìm thấy element nào trong khoảng thời gian trong hàm wait thì nó cũng không đánh fail và nó chuyển tới bước tiếp theo.
	   * Khi selenium đi tìm element mà không thấy thì sẽ đánh fail luôn. Do đó ở đây ta dùng List <WebElement> để chạy được cả 2 case: 
	   			1 - nếu không thấy pop up(ko tìm thấy element) thì bước tới bước tiếp theo
	   			2 - nếu thấy pop up(thấy element - tức là notificationIframeSize > 0 thì nhảy vào pop up và thao tác với nó. Trong trường hợp nó tìm dc nhiều hơn 1 element thì nó sẽ thao tác với element đầu tiên.
	   */
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  //	Để so sánh với 0 thì biến cần phải ở dạng int hoặc float. Ở đây ta khai báo nó là int
	  int notificationIframeSize = notificationIframe.size();
	  System.out.println("So luong iframe tim duoc la " + notificationIframe.size());
	  
	  //	size > 0 thì tức là iframe xuất hiện
	  if(notificationIframeSize > 0) {
		  driver.switchTo().frame(notificationIframe.get(0));
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
		  
		  js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='div-close']")));
		 //	trong thời gian page còn đang load thì pop up đã xổ ra trước rồi, do đó có thể dùng javascript cho chắc ăn cái test case hơn là dùng driver.findElement(By.xpath("//div[@id='div-close']")).click();
		  
		  System.out.println("Close pop up success!");
		  
		 // 	Sau khi thao tác xong với cái iframe A, muốn thao tác với iframe B thì phải nhảy về parentFrame đã.
		  driver.switchTo().parentFrame();
		  
	  }
	  
	  System.out.println("Pass handle pop up!");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  //	Switch qua iframe chứa text
	  WebElement whatAre = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	  driver.switchTo().frame(whatAre);
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and contains(text(),'What are you')]")).isDisplayed());
	  
	  //	Switch về lại parent
	  driver.switchTo().parentFrame();
	  
	  //	Switch vào cái sliding banner
	  WebElement slidingIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	  driver.switchTo().frame(slidingIframe);
	  
	  //	Verify banner images = 6
	  List< WebElement> bannerImages = driver.findElements(By.xpath("//img[@class='bannerimage']"));
	  System.out.println("Banner image = " + bannerImages.size());
	  Assert.assertEquals(bannerImages.size(), 6);
	  
	  //	Check 6 image element = real image
	  for(WebElement image: bannerImages) {
		  Assert.assertTrue(isImageLoadedSuccess(image));
	  }
	  
	  //	Switch về lại parent
	  driver.switchTo().parentFrame();
	  
	  //	Verify flipBanner is displayed
	  WebElement flipBanner = driver.findElement(By.xpath("//div[@class='flipBanner']"));
	  Assert.assertTrue(flipBanner.isDisplayed());
	  
	  //	Verify flipBanner has 8 items
	  List<WebElement>	flipBannerItems = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	  System.out.println("Flip Banner = " + flipBannerItems.size());
	  Assert.assertEquals(flipBannerItems.size(), 8);
	  
	  //	Check 8 flip banner element = real image and they are displayed
	  for (WebElement banner: flipBannerItems ) {
		  Assert.assertTrue(isImageLoadedSuccess(banner));
		  Assert.assertTrue(banner.isDisplayed());
		  //System.out.println("8 flip banner element = real image and they are displayed");
	  }
  }
  
  
  public boolean isImageLoadedSuccess(WebElement imageElement) {
	  return (boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageElement);
  }
  
  //	Switch khi có 2 window/tab
  public void switchToChildWindowByID(String parentID) {
	  //	lấy ra ID của tất cả các cửa sổ đang có
      Set<String> allWindows = driver.getWindowHandles();
      
      /* dùng for thì như này:
      for (int i = 0; i < allWindows.size(); i++)	{
       }
      */
      
      //	dùng for-each để duyệt qua từng cửa sổ
      for (String runWindow : allWindows) {
    	  
    	  		  //	kiểm tra ID của cửa sổ nào mà KHÁC với ID của parent thì switch qua
                  if (!runWindow.equals(parentID)) {
                              driver.switchTo().window(runWindow);
                              break;
                  }
      }
  }
  
  //	Switch khi có nhiều window/tab
  public void switchToWindowByTitle(String expectedTitle) {
	  // lấy ra ID của tất cả cửa sổ và gán cho allWindows
      Set<String> allWindows = driver.getWindowHandles();
      
      // dùng vòng lặp duyệt qua từng cửa sổ
      for (String runWindows : allWindows) {
    	  
    	  		  // switch qua từng cửa sổ
                  driver.switchTo().window(runWindows);
                  
                  // lấy ra title của cửa sổ hiện tại đang đứng
                  String currentWin = driver.getTitle();
                  
                  // nếu cửa sổ hiện tại đang đứng có title bằng với expect thì break
                  if (currentWin.equals(expectedTitle)) {
                              break;
                  }
      }
  }
  
  // Đóng tất cả window / tab trừ parent. Kiểu boolean để về sau còn AssertTrue kiểm tra
  public boolean closeAllWithoutParentWindows(String parentID) {
	  //	lấy ra ID tất cả các cửa sổ rồi gán cho allWindows
      Set<String> allWindows = driver.getWindowHandles();
      
      // dùng vòng lặp duyệt qua từng cửa sổ
      for (String runWindows : allWindows) {
    	  
    	  		  // kiểm tra xem ID của child mà KHÁC ID của parent thì switch qua thằng con rồi đóng nó
                  if (!runWindows.equals(parentID)) {
                              driver.switchTo().window(runWindows);
                              driver.close();
                  }
      }
      driver.switchTo().window(parentID);
      if (driver.getWindowHandles().size() == 1)
                 return true;
      else
                 return false;
  }
  
  
  @Test
  public void TC02() throws Exception {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  String parentID02 = driver.getWindowHandle();
	  System.out.println("Parent ID = " + parentID02);
	  
	  driver.findElement(By.xpath("//a[text()='Click Here']")).click();
	  Thread.sleep(2000);
	  switchToChildWindowByID(parentID02);
	  
	  String googleTitle = driver.getTitle();
	  Assert.assertEquals(googleTitle, "Google");
	  
	  Assert.assertTrue(closeAllWithoutParentWindows(parentID02));
	  Thread.sleep(2000);
	  
	  Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
  }
  
  @Test
  public void TC03() throws Exception {
	  driver.get("http://www.hdfcbank.com/");
	  String parentID03 = driver.getWindowHandle();
	  
	  // Step 02 - Kiểm tra và close quảng cáo nếu có xuất hiện
	  List <WebElement> popUps = driver.findElements(By.xpath("//div[@id='parentdiv']/a/img"));
	  int popUpsize = popUps.size();
	  if(popUpsize > 0) {
		  System.out.println("There is a pop up appears!");
		  // Close cái pop up
		  js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//img[@class='popupCloseButton']")));
	  }
	  // Step 03 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
	  driver.findElement(By.xpath("//li//a[text()='Agri']")).click();
	  Thread.sleep(3000);
	  switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
	  
	  // Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
	  driver.findElement(By.xpath("//div/p[text()='Account Details']")).click();
	  switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
	  
	  // Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
	  driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
	  driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]")).click();
	  switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  
	  // Step 06- Click CSR link on Privacy Policy page
	  driver.findElement(By.xpath("//li//a[text()='CSR']"));
	  
	  // Step 07 - Close tất cả windows/ tabs khác - chỉ giữ lại parent window (http://www.hdfcbank.com/)
	  Assert.assertTrue(closeAllWithoutParentWindows(parentID03));  
  }
  
  @Test
  public void TC04() {
	  driver.get("http://live.guru99.com/index.php/");
	  String parentID04 = driver.getWindowHandle();
	  driver.findElement(By.xpath("//a[text()='Mobile']")).click();
	  
	  // Step 03 - Add sản phẩm Sony Xperia vào để Compare (Add to Compare)
	  driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div[@class='product-info']//a[text()='Add to Compare']")).click();

	  // Step 04 - Add sản phẩm Samsung Galaxy vào để Compare (Add to Compare)
	  driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div[@class='product-info']//a[text()='Add to Compare']")).click();
	  
	  // Step 05 - Click to Compare button
	  driver.findElement(By.xpath("//button[@title='Compare']")).click();
	  
	  // Step 06 - Switch qa cửa sổ mới (chứa 2 sản phẩm đã được Add vào để Compare)
	  switchToWindowByTitle("Products Comparison List - Magento Commerce");
	  
	  // Step 07 - Verify title của cửa sổ bằng: Products Comparison List - Magento Commerce
	  Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
	  
	  // Close tab và chuyển về Parent Window
	  Assert.assertTrue(closeAllWithoutParentWindows(parentID04));
	  
	  driver.navigate().back();
  }
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
