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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.acjvstest.driver.Login;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddCourseTest {
	
	static private WebDriver driver;
	static private Login login = new Login();
	public static int numCourses;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//login.logout(driver);
		//driver.quit();	// exits Firefox Browser
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@BeforeClass
	public static void init() {
		/*driver = new FirefoxDriver();	// creates Firefox Browser
        driver.get("http://localhost:8080");	// goes to acj application
		login.login(driver, "admin", "password");*/
	}
	
	/**
	 * go to homepage
	 */
	@Test
	@Given("^instructor is on Home page$")
	public void instructor_is_on_Home_page() throws Throwable {
		driver = new FirefoxDriver();	// creates Firefox Browser
        driver.get("http://localhost:8080");	// goes to acj application
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/login");
		    }
		});
		login.login(driver, "admin", "password");
		driver.get("http://localhost:8080/static/index.html#/");
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/static/index.html#/");
	}
	
	/**
	 * click on create course
	 */
	@Test
	@When("^instructor clicks on the Create Course button$")
	public void instructor_clicks_on_the_Create_Course_button() throws Throwable {
		driver.findElement(By.xpath("//*[@id='step1']")).click(); 
		numCourses = driver.findElements(By.xpath("//*[@id='step2']/b")).size();
	}
	
	/**
	 * find course name form field
	 */
	@Test
	@Then("^course name text box is shown$")
	public void course_name_text_box_is_shown() throws Throwable {
		driver.findElement(By.xpath("//*[@id='courseName']")).click();
	}
	
	/**
	 * fill in name field and click "Create"
	 */
	@When("^instructor types the name of the course and click on Create button$")
	public void instructor_types_the_name_of_the_course_and_click_on_Create_button() throws Throwable {
		driver.findElement(By.xpath("//*[@id='courseName']")).sendKeys("CPSC 304");
		// click create
		driver.findElement(By.xpath("//*[@id='main']/div/form/input")).click();
	}
	
	/**
	 * tests successfully creating a course as an administrator. 
	 */
	@Test 
	@Then("^the course is created$")
	public void the_course_is_created() throws Throwable {
		// check that the course is added
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id='step2']/b"));
		assertEquals((numCourses + 1), courses.size());	
		login.logout(driver);
		driver.quit();	// exits Firefox Browser
	}

}
