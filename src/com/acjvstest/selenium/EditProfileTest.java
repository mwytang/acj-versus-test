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

public class EditProfileTest {

	static private WebDriver driver;
	static private Login login = new Login();
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		login.logout(driver);
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
	
	/**
	 * testing that the edit profile is displaying the correct user.
	 */
	@Test 
	public void testViewProfile(){
		//find user's name and click
		driver.findElement(By.xpath("/html/body/div[2]/nav/ul[2]/li[1]/a")).click();
		// find User Profile and click
		driver.findElement(By.xpath("/html/body/div[2]/nav/ul[2]/li[1]/ul/li[1]/a")).click();
		
		// waits for redirect to finish; will wait 10 seconds before throwing exception
 		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/userprofile/0");
            }
        });
 		
 		WebElement username = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[1]/div/p"));
 		assertEquals("admin", username.getText());
	}
	
	/**
	 * testing the feature of editing the profile
	 */
	@Test 
	public void testEditProfile(){
		driver.get("http://localhost:8080/static/index.html#/userprofile/0");
		//find Edit button and click
		driver.findElement(By.xpath("//*[@id='main']/div/div/a[1]")).click();
		
		//adding wrong email address
		WebElement email = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[4]/div/input"));
		email.clear();
		email.sendKeys("shk");
		
		//find submit button and click
		driver.findElement(By.xpath("//*[@id='main']/div/div/a[2]")).click();
		
		//check if the error message is displayed
		WebElement errorMsg = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[4]/span"));
		assertEquals(errorMsg.getText(), "Incorrect email format");
		
		//add email address
		WebElement emailField = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[4]/div/input"));
		emailField.clear(); 
		emailField.sendKeys("shk@gmail.com");
		//find submit button and click
		driver.findElement(By.xpath("//*[@id='main']/div/div/a[2]")).click(); 
		 		
		// check if the the update has been successful 
		WebElement successfulMsg = driver.findElement(By.xpath("//*[@id='flash-messages']/li"));
		assertEquals(successfulMsg.getText(), "The profile has been successfully updated.");
	}

}
