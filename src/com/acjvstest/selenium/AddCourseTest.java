package com.acjvstest.selenium;

import static org.junit.Assert.*;

import java.util.List;

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
public class AddCourseTest {
	
	static private WebDriver driver;
	static private Login login = new Login();
	public static int numCourses;

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
	 * go to homepage
	 */
	@Test
	public void testStepA() {
		driver.get("http://localhost:8080/static/index.html#/");
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/static/index.html#/");
	}
	
	/**
	 * click on create course
	 */
	@Test
	public void testStepB() {
		driver.findElement(By.xpath("//*[@id='step1']")).click(); 
		numCourses = driver.findElements(By.xpath("//*[@id='step2']/b")).size();
	}
	
	/**
	 * find course name form field
	 */
	@Test
	public void testStepC() {
		driver.findElement(By.xpath("//*[@id='courseName']")).click();
	}
	
	/**
	 * fill in name field and click "Create"
	 */
	@Test
	public void testStepD() {
		driver.findElement(By.xpath("//*[@id='courseName']")).sendKeys("CPSC 304");
		// click create
		driver.findElement(By.xpath("//*[@id='main']/div/form/input")).click();
	}
	
	/**
	 * tests successfully creating a course as an administrator. 
	 */
	@Test 
	public void testStepE(){
		// check that the course is added
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id='step2']/b"));
		assertEquals((numCourses + 1), courses.size());	
	}

}
