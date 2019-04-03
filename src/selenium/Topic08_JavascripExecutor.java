package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic08_JavascripExecutor {
  WebDriver driver;
  JavascriptExecutor js;
  Actions action;
  Select select;
  Alert alert;
  String customerName, gender, dateOfBirth, address, city, state, pin, mobileNumber, email, password, customerID;
  String firstNameAcc, middleNameAcc, lastNameAcc, emailaddressAcc, passwordAcc, confirmPasswordAcc;
  
  By customerNameTextbox = By.xpath("//input[@name='name']");
	By genderRadio = By.xpath("//input[@value='m']");
	By addressTextBox = By.xpath("//textarea[@name='addr']");
	By dateOfBirthTextBox = By.xpath("//input[@id='dob']");
	By cityTextBox = By.xpath("//input[@name='city']");
	By stateTextBox = By.xpath("//input[@name='state']");
	By pinTextBox = By.xpath("//input[@name='pinno']");
	By mobileNumberTextBox = By.xpath("//input[@name='telephoneno']");
	By emailTextBox = By.xpath("//input[@name='emailid']");
	By passwordTextBox = By.xpath("//input[@name='password']");
	By submitButton =  By.xpath("//input[@name='sub']");
	//Create Random number function
	public int randomNumber() {
		Random random = new Random();
		int number =random.nextInt(999999);
		System.out.println("Random number = " + number);
		return number;
	}
	
  @BeforeTest
  public void beforeTest() {
	  //driver = new FirefoxDriver();
	  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  js = (JavascriptExecutor) driver;
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @Test
  public void TC01_JavascriptExecutor() {
	  navigateToUrlByJS("http://live.guru99.com/");
	  
	  // Verify domain =  live.guru99.com
	  String domain = (String) js.executeScript("return document.domain");
	  Assert.assertEquals(domain, "live.guru99.com");
	  
	  // Verify URL =  http://live.guru99.com/
	  String URL = (String) js.executeScript("return document.URL");
	  System.out.println(URL);
	  Assert.assertEquals(URL, "http://live.guru99.com/");
	  
	  // Open MOBILE page using javascript
	  WebElement mobilePage = driver.findElement(By.xpath("//a[text()='Mobile']"));
	  js.executeScript("arguments[0].click()", mobilePage);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'page-title')]/h1[text()='Mobile']")).isDisplayed());
	  
	  // Add Samsung Galaxy to Cart by using Javascript
	  WebElement addCart_SS_Galaxy = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
	  js.executeScript("arguments[0].click()", addCart_SS_Galaxy);
	  
	  // Verify message
	  String sText = js.executeScript("return document.documentElement.innerText;").toString();
	  Assert.assertTrue(sText.contains("Samsung Galaxy was added to your shopping cart."));
	  
	  // Open Privacy Policy by using Javascript
	  js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='Privacy Policy']")));
	  String privacyTitle = js.executeScript("return document.documentElement.innerText;").toString();
	  Assert.assertTrue(privacyTitle.contains("Privacy Policy"));
	  
	  // Scroll to the bottom of page
	  js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	  Assert.assertTrue(driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']")).isDisplayed());
	  
	  // Verify domain after navigate = demo.guru99.com
	  navigateToUrlByJS("http://demo.guru99.com/v4/");
	  String domainGuru = (String) js.executeScript("return document.domain");
	  Assert.assertEquals(domainGuru, "demo.guru99.com");
	  
  }
  @Test
  public void TC02_Remove_Attribute() throws Exception {
	  driver.get("http://demo.guru99.com/v4");
	  driver.findElement(By.xpath("//input[@type='text']")).sendKeys("mngr181358");
	  driver.findElement(By.xpath("//input[@type='password']")).sendKeys("berydUp");
	  driver.findElement(By.xpath("//input[@type='submit']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//td/marquee[contains(text(),'Welcome To Manager')]")).isDisplayed());
	  
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  
	  		//Data for Create new form
			customerName = "automated";
			gender="male";
			dateOfBirth="1999-01-01";
			address="van canh";
			city="ha noi"; 
			state="hoai duc"; 
			pin="899499";
			mobileNumber="0374525999";
			email="auto"+ randomNumber() +"@gmail.com";
			password ="12345678";
			Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Add New Customer']")).isDisplayed());
			// Thread.sleep(2000);
			
			// Input data to fields
			driver.findElement(customerNameTextbox).sendKeys(customerName);
			driver.findElement(genderRadio).click();
			driver.findElement(addressTextBox).sendKeys(address);
			
			// Remove attribute 'type' then input data to dateOfBirthTextBox
			WebElement dateTextBox = driver.findElement(dateOfBirthTextBox);
			removeAttributeInDOM(dateTextBox, "type");
			Thread.sleep(3000);
			driver.findElement(dateOfBirthTextBox).sendKeys(dateOfBirth);
			
			driver.findElement(cityTextBox).sendKeys(city);
			driver.findElement(stateTextBox).sendKeys(state);
			driver.findElement(pinTextBox).sendKeys(pin);
			driver.findElement(mobileNumberTextBox).sendKeys(mobileNumber);
			driver.findElement(emailTextBox).sendKeys(email);
			driver.findElement(passwordTextBox).sendKeys(password);
			
			// Click to Create a new customer
			driver.findElement(submitButton).click();	  	
			
			// Verify new customer is added successfully
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
			
			// Get actual data by getText() then Verify actual data = expected data by assertEquals()
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNumber);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
  }
  @Test
  public void TC03_CreateAnAccount() throws Exception {
	  driver.get("http://live.guru99.com/");
	  
	  // Click vào link "My Account" để tới trang đăng nhập (Sử dụng JE)
	  WebElement myAccBtn = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
	  clickToElementByJS(myAccBtn);
	  
	  // Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản (Sử dụng JE)
	  WebElement createAccBtn = driver.findElement(By.xpath("//a[@title='Create an Account']"));
	  clickToElementByJS(createAccBtn);
	  
	  // Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password (Sử dụng JE)
	  firstNameAcc = "can" + randomNumber();
	  middleNameAcc = "not";
	  lastNameAcc = "sleep";
	  emailaddressAcc = "cantsleep" + randomNumber() +"@gmail.com";
	  passwordAcc = "thuephao";
	  confirmPasswordAcc = "thuephao";
	  WebElement firstNameAccTextBox = driver.findElement(By.xpath("//input[@id='firstname']"));
	  WebElement middleNameAccTextBox = driver.findElement(By.xpath("//input[@id='middlename']"));
	  WebElement lastNameAccTextBox = driver.findElement(By.xpath("//input[@id='lastname']"));
	  WebElement emailaddressAccTextBox = driver.findElement(By.xpath("//input[@id='email_address']"));
	  WebElement passwordAccTextBox = driver.findElement(By.xpath("//input[@id='password']"));
	  WebElement confirmPasswordAccTextBox = driver.findElement(By.xpath("//input[@id='confirmation']"));
	  sendkeyToElementByJS(firstNameAccTextBox, firstNameAcc);
	  sendkeyToElementByJS(middleNameAccTextBox, middleNameAcc);
	  sendkeyToElementByJS(lastNameAccTextBox, lastNameAcc);
	  sendkeyToElementByJS(emailaddressAccTextBox, emailaddressAcc);
	  sendkeyToElementByJS(passwordAccTextBox, passwordAcc);
	  sendkeyToElementByJS(confirmPasswordAccTextBox, confirmPasswordAcc);
//	  firstNameAccTextBox.sendKeys(firstNameAcc);
//	  middleNameAccTextBox.sendKeys(middleNameAcc);
//	  lastNameAccTextBox.sendKeys(lastNameAcc);
//	  emailaddressAccTextBox.sendKeys(emailaddressAcc);
//	  passwordAccTextBox.sendKeys(passwordAcc);
//	  confirmPasswordAccTextBox.sendKeys(confirmPasswordAcc);
	  WebElement registerBtn = driver.findElement(By.xpath("//button[@title='Register']"));
	  clickToElementByJS(registerBtn);
	  
	  // Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store. (Sử dụng JE)
	  Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Thank you')]")).isDisplayed());
	  
	  // Logout khỏi hệ thống (Sử dụng JE)
	  WebElement headerAccBtn = driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']"));
	  clickToElementByJS(headerAccBtn);
	  WebElement logOutBtn = driver.findElement(By.xpath("//div[@id='header-account']//a[text()='Log Out']"));
	  clickToElementByJS(logOutBtn);
	  
	  // Kiểm tra hệ thống navigate về Home page sau khi logout thành công (Sử dụng JE)
	  Thread.sleep(6000);
	  Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
  }
  
  public void highlightElement(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].style.border='6px groove red'", element);
  }

  public Object executeForBrowser(String javaSript) {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript(javaSript);
  }

  public Object clickToElementByJS(WebElement element) {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].click();", element);
  }

  public Object sendkeyToElementByJS(WebElement element, String value) {
         JavascriptExecutor js = (JavascriptExecutor) driver;
         return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
  }

  public Object removeAttributeInDOM(WebElement element, String attribute) {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
  }

  public Object scrollToBottomPage() {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
  }

  public Object navigateToUrlByJS(String url) {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("window.location = '" + url + "'");
  }
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
//div[@class='footer']//a[text()='My Account']