package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic04_Textbox_Textarea {
	WebDriver driver;
	//Declare variable
	String customerName, gender, dateOfBirth, address, city, state, pin, mobileNumber, email, password;
	//Locate elements
	By customerNameTextbox = By.xpath("//input[@name='name']");
	By genderRadio = By.xpath("//input[@value='m']");
	By addressTextBox = By.xpath("//textarea[@name='addr']");
	By dateOfBirthTextBox = By.xpath("//input[@id='dob']");
	By cityTextBox = By.xpath("//input[@name='city");
	By stateTextBox = By.xpath("//input[@name='state");
	By pinTextBox = By.xpath("//input[@name='pinno");
	By mobileNumberTextBox = By.xpath("//input[@name='telephoneno']");
	By emailTextBox = By.xpath("//input[@name='emailid']");
	By passwordTextBox = By.xpath("//input[@value='password']");
	By submitButton =  By.xpath("//input[@type='submit']");
	//Create Random number function
	public int randomNumber() {
		Random random = new Random();
		int number =random.nextInt(999999);
		System.out.println("Random number = " + number);
		return number;
	}
	@BeforeTest
	  public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		//Data for test
		customerName = "auto1";
		gender="male";
		dateOfBirth="01/01/1999";
		address="van canh";
		city="ha noi"; 
		state="hoai duc"; 
		pin="899499";
		mobileNumber="0374525999";
		email="auto"+ randomNumber() +"gmail.com";
		password ="12345678";
	  }
	//vi bộ xpath cho customerName, gender,...dùng cho 3 page như New customer, edit, delete nên ta cũng tạo 1 bộ xpath cho đỡ mất công gõ lại nhiều lần.
	
	@Test
	public void f() throws InterruptedException {
		
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		Thread.sleep(3000);
		//Click on New Customer menu
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		//Verify we are on Add New Customer form
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Add New Customer']")).isDisplayed());
		Thread.sleep(3000);
		//Input data to fields
		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();
		driver.findElement(addressTextBox).sendKeys(address);
		driver.findElement(dateOfBirthTextBox).sendKeys(dateOfBirth);
		driver.findElement(cityTextBox).sendKeys(city);
		driver.findElement(stateTextBox).sendKeys(state);
		driver.findElement(pinTextBox).sendKeys(pin);
		driver.findElement(mobileNumberTextBox).sendKeys(mobileNumber);
		driver.findElement(emailTextBox).sendKeys(email);
		driver.findElement(passwordTextBox).sendKeys(password);
		driver.findElement(submitButton).click();
	}
  
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
