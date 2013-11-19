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
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditProfileTest {

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
	public void testViewProfile(){
		//find user's name and click
		WebElement userName = driver.findElement(By.xpath("/html/body/div[2]/nav/ul[2]/li[1]/a"));
		userName.click();
		// find User Profile and click
		WebElement userProfile = driver.findElement(By.xpath("/html/body/div[2]/nav/ul[2]/li[1]/ul/li[1]/a"));
		userProfile.click(); 
		
		// waits for redirect to finish; will wait 10 seconds before throwing exception
 		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/userprofile/0");
            }
        });
		
	}
	
	@Test 
	public void testEditProfile(){
		//find Edit button and click
		WebElement edit = driver.findElement(By.xpath("//*[@id='main']/div/div/a[1]"));
		edit.click();
		
		//adding wrong email addresss
		//add email address
		WebElement emailBox = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[4]/div/input"));
		emailBox.sendKeys("shk");
		
		//find submit button and click
		WebElement submitButton = driver.findElement(By.xpath("//*[@id='main']/div/div/a[2]"));
		submitButton.click();
		
		//check if the error message is displayed
		WebElement errorMsg = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[4]/span"));
		System.out.println(errorMsg.getText());
		assertEquals(errorMsg.getText(),"Incorrect email format");
		
		
		//add email address
		emailBox.clear(); 
		emailBox.sendKeys("shk@gmail.com");
		//find submit button and click
		WebElement submitButton2 = driver.findElement(By.xpath("//*[@id='main']/div/div/a[2]"));
		submitButton2.click(); 
		
		// waits for redirect to finish; will wait 10 seconds before throwing exception
		 		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		            public Boolean apply(WebDriver d) {
		            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/userprofile/0");
		            }
		        });
		 		
		// check if the the update has been successful 
		WebElement successfulMsg = driver.findElement(By.xpath("//*[@id='flash-messages']/li"));
		assertEquals(successfulMsg.getText(), "The profile has been successfully updated.");
	}

}
