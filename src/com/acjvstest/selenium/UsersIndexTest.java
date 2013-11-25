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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.acjvstest.driver.Login;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsersIndexTest {

	static private WebDriver driver;
	static private Login login = new Login();
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		login.logout(driver);
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost:8080");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@BeforeClass
	public static void init() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080");
		login.login(driver, "admin", "password");
	}

	/**
	 * test filtering by username
	 */
	@Test
	public void testFilterByUsername() {
		driver.get("http://localhost:8080/static/index.html#/user");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/user");
		    }
		});
		WebElement username = driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/thead/tr[2]/th[1]/form/div/div/div/input"));
		username.sendKeys("inst");
	 	try {
	 		// the element should not be found because it is deleted.
	 		driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/tbody/tr[6]"));
	 		fail("There should only be 5 users");
	 	} catch (NoSuchElementException e) {
	 		WebElement lastUser = driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/tbody/tr[5]/td[1]/a"));
	 		assertEquals("inst2", lastUser.getText());
	 	}
	}
	
	/**
	 * test filtering by full name
	 */
	@Test
	public void testFilterByFullName() {
		driver.get("http://localhost:8080/static/index.html#/user");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/user");
		    }
		});
		WebElement fullname = driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/thead/tr[2]/th[2]/form/div/div/div/input"));
		fullname.sendKeys("Instructor");
	 	try {
	 		// the element should not be found because it is deleted.
	 		driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/tbody/tr[6]"));
	 		fail("There should only be 5 users");
	 	} catch (NoSuchElementException e) {
	 		WebElement lastUser = driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/tbody/tr[5]/td[2]"));
	 		assertEquals("Instructor Two", lastUser.getText());
	 	}
	}
	
	/**
	 * test filtering by display name
	 */
	@Test
	public void testFilerByDisplayName() {
		driver.get("http://localhost:8080/static/index.html#/user");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/user");
		    }
		});
		WebElement displayname = driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/thead/tr[2]/th[3]/form/div/div/div/input"));
		displayname.sendKeys("instructor");
	 	try {
	 		// the element should not be found because it is deleted.
	 		driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/tbody/tr[4]"));
	 		fail("There should only be 3 users");
	 	} catch (NoSuchElementException e) {
	 		WebElement lastUser = driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/tbody/tr[3]/td[3]"));
	 		assertEquals("instructor2", lastUser.getText());
	 	}
	}
	
	/**
	 * test filtering by user type
	 */
	@Test
	public void testFilerByUserType() {
		driver.get("http://localhost:8080/static/index.html#/user");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/user");
		    }
		});
		WebElement displayname = driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/thead/tr[2]/th[4]/form/div/div/div/input"));
		displayname.sendKeys("Teacher");
	 	try {
	 		// the element should not be found because it is deleted.
	 		driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/tbody/tr[6]"));
	 		fail("There should only be 5 users");
	 	} catch (NoSuchElementException e) {
	 		WebElement lastUser = driver.findElement(By.xpath("//*[@id='main']/div/div[3]/table/tbody/tr[5]/td[3]"));
	 		assertEquals("instructor2", lastUser.getText());
	 	}
	}
}
