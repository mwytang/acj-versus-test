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

public class InstructorLoginTest {
	
	static private WebDriver driver;

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
		// enter username
		WebElement username = driver.findElement(By.xpath("//*[@id='inputUser']"));
		username.sendKeys("root");
		// enter password
		WebElement password = driver.findElement(By.xpath("//*[@id='inputPass']"));
		password.sendKeys("demo");
		// sign in
		WebElement signin = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[3]/input"));
		signin.click();
		// waits for redirect to finish; will wait 10 seconds before throwing exception
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/");
            }
        });
        // asserts the application redirects to the correct page
		WebElement title = driver.findElement(By.xpath("//*[@id='main']/div/h2/span[2]"));
        assertEquals(title.getText(), "Courses you belong to");
	}
	
	/**
	 * tests logging out the user.
	 */
	@Test
	public void testRegularLogout() {
		// opens user's menu
		WebElement userMenu = driver.findElement(By.xpath("/html/body/div[2]/nav/ul[2]/li[1]/a"));
		userMenu.click();
		// click logout
		WebElement logout = driver.findElement(By.xpath("/html/body/div[2]/nav/ul[2]/li[1]/ul/li[2]/a"));
		logout.click();
		// waits for redirect to login page
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/login");
            }
        });
        // asserts the application redirects to the correct page
		WebElement title = driver.findElement(By.xpath("//*[@id='main']/div/div/h2"));
        assertEquals(title.getText(), "Login Page");
	}

}
