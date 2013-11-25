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

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddQuestionTest  {

	static private WebDriver driver;
	static private Login login = new Login();
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		/*login.logout(driver);
		driver.quit();*/		
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
	 * test we are on the homepage
	 */
	@Test
	@Given("^instructor is on the Homepage and he has created a course$")
	public void instructor_is_on_the_Homepage_and_he_has_created_a_course() throws Throwable {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/login");
		    }
		});
		login.login(driver, "admin", "password");
		driver.get("http://localhost:8080");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/");
		    }
		});
		assertEquals("http://localhost:8080/static/index.html#/", driver.getCurrentUrl());
	}
	
	/**
	 * test clicking on the preferred course's name
	 */
	@Test
	@When("^instructor clicks on course name$")
	public void instructor_clicks_on_course_name() throws Throwable {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	List<WebElement> courses = d.findElements(By.xpath("//*[@id='step2']"));
		    	return (courses.size() > 0);
		    }
		});
		driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[1]/td[2]")).click();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/questionpage/1");
		    }
		});
	}
	
	/**
	 * test we are on the course/question page
	 */
	@Test
	@Then("^the Course Page opens$")
	public void the_Course_Page_opens() throws Throwable {
		// check we are on the course/question page
		assertEquals("http://localhost:8080/static/index.html#/questionpage/1", driver.getCurrentUrl());
		WebElement ques = driver.findElement(By.xpath("//*[@id='main']/div/div/h2"));
		assertEquals("Question Page", ques.getText());
	}
	
	/**
	 * click on the "Create Question" button
	 */
	@Test
	@When("^instructor clicks on the Create Question button$")
	public void instructor_clicks_on_the_Create_Question_button() throws Throwable {
		driver.findElement(By.xpath("//*[@id='stepNav']")).click();
	}
	
	/**
	 * test the text fields are available
	 */
	@Test
	@Then("^the edit text boxes for creating a question is shown$")
	public void the_edit_text_boxes_for_creating_a_question_is_shown() throws Throwable {
		List<WebElement> text = driver.findElements(By.xpath("//*[@id='myquestion']"));
		assertTrue(text.size() > 0);
	}
	
	/**
	 * fill in the form and click submit
	 */
	@Test
	@When("^instructor fills in the question form and clicks on the submit button$")
	public void instructor_fills_in_the_question_form_and_clicks_on_the_submit_button() throws Throwable {
		// fill in the title
		driver.findElement(By.xpath("//*[@id='questionTitle']")).sendKeys("title");
		// fill in the question
		driver.findElement(By.xpath("//*[@id='myquestion']")).sendKeys("question question");
		// fill in the answer
		driver.findElement(By.xpath("//*[@id='myanswerq']")).sendKeys("answer answer");
		// click submit
		driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/ng-form/a[2]")).click();
	}
	
	/**
	 * check the question creation is successful
	 */
	@Test
	@Then("^question is created$")
	public void question_is_created() throws Throwable {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return (d.findElements(By.xpath("//*[@id='flash-messages']/li")).size() > 0);
		    }
		});
		assertEquals("The question has been successfully added.", driver.findElement(By.xpath("//*[@id='flash-messages']/li")).getText());
		login.logout(driver);
		driver.quit();	
	}
}
