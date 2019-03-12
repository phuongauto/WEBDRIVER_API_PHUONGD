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
	String customerName, gender, dateOfBirth, address, city, state, pin, mobileNumber, email, password, customerID;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;
	//Locate elements
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
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr183874");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qadYnYv");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
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
		
		//Data for Edit form
		editAddress="Duong 422B"; 
		editCity="Ha Noi Hai"; 
		editState="quan Hoai Duc"; 
		editPin="899400"; 
		editPhone="0374525000"; 
		editEmail="editautotest"+randomNumber()+"@gmail.com";
	  }
	//Vì bộ xpath cho customerName, gender,...dùng cho 3 page như New customer, edit, delete nên ta cũng tạo 1 bộ xpath cho đỡ mất công gõ lại nhiều lần.
	
	@Test
	public void TC01_Create_New_Customer() {
		//Verify Login successfully
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		//Thread.sleep(1000);
		//Click on New Customer menu
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		//Verify we are on Add New Customer form
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Add New Customer']")).isDisplayed());
		//Thread.sleep(2000);
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
		//Click to Create a new customer
		driver.findElement(submitButton).click();
		//Verify new customer is added successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
		//Get actual data by getText() then Verify actual data = expected data by assertEquals()
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID on Test case 01 is: "+ customerID);
	}
	@Test
	public void TC02_Edit_Customer() throws InterruptedException {
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		//Open Edit Customer page
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		//Verify we are on Edit Customer form
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Edit Customer Form']")).isDisplayed());
		Thread.sleep(2000);
		//Input customer ID
		System.out.println("Customer ID on Test case 02 is: "+ customerID);
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		//Input data for edit customer
		Thread.sleep(1500);
		driver.findElement(addressTextBox).clear();
		driver.findElement(addressTextBox).sendKeys(editAddress);	Thread.sleep(1500);
		driver.findElement(cityTextBox).clear();
		driver.findElement(cityTextBox).sendKeys(editCity);	Thread.sleep(1500);
		driver.findElement(stateTextBox).clear();
		driver.findElement(stateTextBox).sendKeys(editState);	Thread.sleep(1500);
		driver.findElement(pinTextBox).clear();
		driver.findElement(pinTextBox).sendKeys(editPin);	Thread.sleep(1500);
		driver.findElement(mobileNumberTextBox).clear();
		driver.findElement(mobileNumberTextBox).sendKeys(editPhone);	Thread.sleep(1500);
		driver.findElement(emailTextBox).clear();
		driver.findElement(emailTextBox).sendKeys(editEmail);	Thread.sleep(1500);
		driver.findElement(submitButton).click();	Thread.sleep(1500);
		//Verify customer is edited successfully
				Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());
		//Verify expected data = actual data
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);	
	}
	//Fucntion parse date of birth
	public String convertDateMonthYear(String dateMonthYear) {
		//01/01/1999 => 1999-01-01
		String date, month, year, newDMY;
		String [] dateMonthYearSplit = dateMonthYear.split("/");
		date = dateMonthYearSplit[0];
		month = dateMonthYearSplit[1];
		year = dateMonthYearSplit[2];
		newDMY = year + "-" + month + "-" + date;
		return newDMY;
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
