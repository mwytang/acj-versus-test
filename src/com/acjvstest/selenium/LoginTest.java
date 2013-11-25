package com.acjvstest.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.acjvstest.driver.Login;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest {
	
	static private WebDriver driver;
	static private Login signin = new Login();

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
	}

	/**
	 * tests logging in to the application with a valid username and password
	 */
	@Test
	public void testRegularLogin() {
		driver.get("http://localhost:8080");
		signin.login(driver, "admin", "password");
        // asserts the application redirects to the correct page
		WebElement title = driver.findElement(By.xpath("//*[@id='main']/div/h2/span[2]"));
        assertEquals(title.getText(), "Courses you belong to");
	}
	
	/**
	 * tests logging out the user.
	 */
	@Test
	public void testRegularLogout() {
		signin.logout(driver);
        // asserts the application redirects to the correct page
		WebElement title = driver.findElement(By.xpath("//*[@id='main']/div/div/h2"));
        assertEquals(title.getText(), "Login Page");
	}
	
	/**
	 * tests an error appears when an invalid username and/or password is used
	 */
	@Test
	public void testInvalidUser() {
		signin.invalidLogin(driver, "invalid", "invalid");
		WebElement error = driver.findElement(By.xpath("//*[@id='flash-messages']/li"));
		assertEquals(error.getText(), "Incorrect username or password");
	}

}
