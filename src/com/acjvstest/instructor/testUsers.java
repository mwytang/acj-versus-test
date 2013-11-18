package com.acjvstest.instructor;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class testUsers {

static private WebDriver driver;
	
	private static final String inputUserXPath = "//*[@id='inputUser']";

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();	// exits Firefox Browser
	}

	@Before
	public void setUp() throws Exception {
		 
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@BeforeClass
	public static void init() {
		driver = new FirefoxDriver();	// creates Firefox Browser
        driver.get("http://localhost:8080");	// goes to acj application
     
     		WebElement username = driver.findElement(By.xpath(inputUserXPath));
     		username.sendKeys("root");
     		// enter password
     		WebElement password = driver.findElement(By.xpath("//*[@id='inputPass']"));
     		password.sendKeys("password");
     		// sign in
     		WebElement signin = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[3]/input"));
     		signin.click();
     	    // waits for redirect to finish; will wait 10 seconds before throwing exception
     		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/");
                }
            });
	}
	
	@Test 
	public void testCreateUser(){
		//find Create User button and click 
		WebElement createUser = driver.findElement(By.xpath("/html/body/div[2]/nav/ul[1]/li[3]/a"));
		createUser.click();	
		 
		
		//find Role box and click on the Student option
		Select roleBox = new Select(driver.findElement(By.xpath("//*[@id='roleInput']")));
		roleBox.selectByVisibleText("Student"); 

			
		//find Username box and enter the username 
		WebElement userNameBox = driver.findElement(By.xpath("//*[@id='usernameInput']"));
		userNameBox.sendKeys("student1");
		 
		
		//find First Name box and enter the first name
		WebElement firstNameBox = driver.findElement(By.xpath("//*[@id='firstnameInput']"));
		firstNameBox.sendKeys("studentf1"); 
		
		//find Last Name box and enter the last name
		WebElement lastNameBox = driver.findElement(By.xpath("//*[@id='lastnameInput']"));
		lastNameBox.sendKeys("studentf1");
		
		//find Display Name box and enter the display name
		WebElement displayNameBox = driver.findElement(By.xpath("//*[@id='displaynameInput']"));
		displayNameBox.sendKeys("student1");
		
		//find Email box and enter the email address
		WebElement emailBox = driver.findElement(By.xpath("//*[@id='emailInput']"));
		emailBox.sendKeys("studen1@ubc.ca");
		
		//find Password box and enter the password
		WebElement passBox = driver.findElement(By.xpath("//*[@id='passwordInput']"));
		passBox.sendKeys("123456"); 
		
		//find Retype Password box and enter the password
		WebElement retypePassBox = driver.findElement(By.xpath("//*[@id='retypepwInput']"));
		retypePassBox.sendKeys("123456");
		
		//find Confirm button and click
		WebElement confirmButton = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[9]/div/input"));
		confirmButton.click(); 
		
		// waits for redirect to finish; will wait 10 seconds before throwing exception
 		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/");
            }
   
        });
 		
 		//find the "User created successfully" text
 		WebElement successfullText = driver.findElement(By.xpath(" //*[@id='flash-messages']/li"));
 		assertEquals(successfullText.getText(),"User created successfully");
        
		
	}
	

}
