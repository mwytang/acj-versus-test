package com.acjvstest.selenium;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.acjvstest.driver.Login;



public class UsersTest {

static private WebDriver driver;
	
	private static Login login = new Login();

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
        login.login(driver, "admin", "password");
	}
	
	@Test 
	public void testCreateUser(){
		//click Create User link
		driver.findElement(By.xpath("/html/body/div[2]/nav/ul[1]/li[3]/a")).click();			 
		
		//find Role form field and click on the Student option
		driver.findElement(By.xpath("//*[@id='roleInput']/option[2]")).click();
			
		//find Username form field and enter the username 
		driver.findElement(By.xpath("//*[@id='usernameInput']")).sendKeys("stu11");
		
		//find First Name field and enter the first name
		driver.findElement(By.xpath("//*[@id='firstnameInput']")).sendKeys("Student"); 
		
		//find Last Name field and enter the last name
		driver.findElement(By.xpath("//*[@id='lastnameInput']")).sendKeys("Eleven");
		
		//find Display Name box and enter the display name
		driver.findElement(By.xpath("//*[@id='displaynameInput']")).sendKeys("student11");
		
		//find Email box and enter the email address
		driver.findElement(By.xpath("//*[@id='emailInput']")).sendKeys("s11@example.ca");
		
		//find Password box and enter the password
		driver.findElement(By.xpath("//*[@id='passwordInput']")).sendKeys("password"); 
		
		//find Retype Password box and enter the password
		driver.findElement(By.xpath("//*[@id='retypepwInput']")).sendKeys("password");
		
		//find Confirm button and click
		driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[9]/div/input")).click(); 
		
		// waits for redirect to finish; will wait 10 seconds before throwing exception
 		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/");
            }
   
        });
 		
 		//find the "User created successfully" text
 		WebElement successfulText = driver.findElement(By.xpath(" //*[@id='flash-messages']/li"));
 		assertEquals(successfulText.getText(),"User created successfully");
        
		
	}
	

}
