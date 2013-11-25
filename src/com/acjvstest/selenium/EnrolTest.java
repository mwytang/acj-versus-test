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

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EnrolTest {
	
	static private WebDriver driver;
	static private Login login = new Login();

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		/*login.logout(driver);
		driver.quit();	// exits Firefox Browser*/
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@BeforeClass
	public static void init() {
		/*driver = new FirefoxDriver();
		driver.get("http://localhost:8080");
		login.login(driver, "admin", "password");*/
	}

	/**
	 * test enrolling a student to a course. Go to the homepage.
	 */
	@Test
	@Given("^instructor is on the homepage with a course$")
	public void instructor_is_on_the_homepage_with_a_course() throws Throwable {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080");
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
	 * test click enrol users
	 */
	@Test
	@When("^instructor clicks on the Enrol Users$")
	public void instructor_clicks_on_the_Enrol_Users() throws Throwable {
		driver.findElement(By.xpath("//*[@id='step3']")).click();
	}
	
	/**
	 * reaches enrollment page
	 */
	@Test
	@Then("^enrolment page is shown$")
	public void enrolment_page_is_shown() throws Throwable {
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/static/index.html#/enrollpage/1", url);
	}
	
	/**
	 * search for student
	 */
	@Test
	@When("^instructor types a student name in the Search Student text box$")
	public void instructor_types_a_student_name_in_the_Search_Student_text_box() throws Throwable {
		WebElement search = driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/div[1]/div/div/input"));
		search.sendKeys("Student");
	}
	
	/**
	 * users that match filter is displayed
	 */
	@Test
	@Then("^the information about that student is shown$")
	public void the_information_about_that_student_is_shown() throws Throwable {
		WebElement user = driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/table/tbody/tr/td[1]/a"));
		assertEquals("Student Eight", user.getText());
	}
	
	/**
	 * click on the status button by the user's name to enrol the user
	 */
	@Test
	@When("^instructor clicks on status button  next to student's name$")
	public void instructor_clicks_on_status_button_next_to_student_s_name() throws Throwable {
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/table/tbody/tr/td[2]/div/p")).click();
	}
	
	/**
	 * the user's status becomes enrolled
	 */
	@Test
	@Then("^the button turns green and student's status changes to enrolled$")
	public void the_button_turns_green_and_student_s_status_changes_to_enrolled() throws Throwable {		WebElement status = driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/table/tbody/tr/td[2]/div/p"));
		assertEquals("Enrolled", status.getText());
		login.logout(driver);
		driver.quit();	// exits Firefox Browser
	}
}
