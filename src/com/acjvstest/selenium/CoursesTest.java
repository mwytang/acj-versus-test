package com.acjvstest.selenium;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.acjvstest.driver.Course;
import com.acjvstest.driver.Login;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoursesTest {
	
	static private WebDriver driver;
	static private Course course = new Course();
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
	 * tests clicking Create Course without a name. Expects nothing to happen
	 */
	@Test
	public void testCreateEmptyCourse() {
		driver.get("http://localhost:8080/static/index.html#/");
		// refresh the page
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id='step2']/b"));
		int size = courses.size();
		driver.get("http://localhost:8080/static/index.html#/");
		course.addCourse(driver, "");
		// count number of courses: should still be six
		courses = driver.findElements(By.xpath("//*[@id='step2']/b"));
		assertEquals(size, courses.size());	
	}

	/**
	 * tests editing a course
	 */
	@Test 
	public void testEditCourse(){
		// refresh the page
		driver.get("http://localhost:8080/static/index.html#/");
		// edit course
		course.editCourse(driver, 1);
		WebElement success = driver.findElement(By.xpath("//*[@id='flash-messages']/li"));
		assertEquals(success.getText(), "The course has been successfully modified.");
		WebElement name = driver.findElement(By.xpath("//*[@id='main']/div/div/form/span[1]"));
		assertEquals(name.getText(), "APSC 150 101");
	}
	
	/**
	 * tests adding and deleting a tag
	 */
	@Test
	public void testTags() {
		driver.get("http://localhost:8080/static/index.html#/editcourse/1");
		course.addTag(driver, "tag1");
	 	WebElement tag = driver.findElement(By.xpath("//*[@id='main']/div/div/form/table/tbody/tr/td[2]"));
	 	assertEquals(tag.getText(), "tag1");
	 	course.deleteTag(driver, 1);
	 	try {
	 		// the element should not be found because it is deleted.
	 		driver.findElement(By.xpath("//*[@id='main']/div/div/form/table/tbody/tr/td[2]"));
	 		fail("the tag should not exist");
	 	} catch (NoSuchElementException e) {
	 		// do nothing
	 	}
	}
	 	
	/**
	 * tests searching a course  
	 */
	@Test 
	public void testSearchCourse(){
		//go to home page
		driver.get("http://localhost:8080");
		course.searchCourse(driver, "STAT 200");
		//checks if the course visible on the page is EECE416
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id='step2']/b"));
		assertEquals(courses.size(), 1);	
	}
}

