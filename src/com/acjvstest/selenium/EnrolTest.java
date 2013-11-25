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

import com.acjvstest.driver.Login;

public class EnrolTest {
	
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
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080");
		login.login(driver, "admin", "password");
	}

	/**
	 * test enrolling a student to a course. Go to the homepage.
	 */
	@Test
	public void testStepA() {
		driver.get("http://localhost:8080/static/index.html#/");
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/static/index.html#/");
	}
	
	/**
	 * test click enrol users
	 */
	@Test
	public void testStepB() {
		driver.findElement(By.xpath("//*[@id='step3']")).click();
	}
	
	/**
	 * reaches enrollment page
	 */
	@Test
	public void testStepC() {
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/static/index.html#/enrollpage/1", url);
	}
	
	/**
	 * search for student
	 */
	@Test
	public void testStepD() {
		WebElement search = driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/div[1]/div/div/input"));
		search.sendKeys("Student Four");
	}
	
	/**
	 * users that match filter is displayed
	 */
	@Test
	public void testStepE() {
		WebElement user = driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/table/tbody/tr/td[1]/a"));
		assertEquals("Student Four", user.getText());
	}
	
	/**
	 * click on the status button by the user's name to enrol the user
	 */
	@Test
	public void testStepF() {
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/table/tbody/tr/td[2]/div/p")).click();
	}
	
	/**
	 * the user's status becomes enrolled
	 */
	@Test
	public void testStepG() {
		WebElement status = driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/table/tbody/tr/td[2]/div/p"));
		assertEquals("Enrolled", status.getText());
	}
}
