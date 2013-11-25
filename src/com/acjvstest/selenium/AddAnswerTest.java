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
public class AddAnswerTest {
	
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
		login.login(driver, "stu1", "password");*/
	}

	/**
	 * test we are on the homepage
	 */
	@Test
	@Given("^student is on the Home page and he is enrolled in a course$")
	public void student_is_on_the_Home_page_and_he_is_enrolled_in_a_course() throws Throwable {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/login");
		    }
		});
		login.login(driver, "stu1", "password");
		assertEquals("http://localhost:8080/static/index.html#/", driver.getCurrentUrl());
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/");
		    }
		});
	}
	
	/**
	 * test going to the preferred course
	 */
	@Test
	@When("^student clicks on the course name$")
	public void student_clicks_on_the_course_name() throws Throwable {
		driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[1]/td[2]")).click();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/questionpage/1");
		    }
		});
	}
	
	/**
	 * test we are on the correct course page
	 */
	@Test
	@Then("^the course page opens$")
	public void the_course_page_opens() throws Throwable {
		// check we are on the course/question page
		assertEquals("http://localhost:8080/static/index.html#/questionpage/1", driver.getCurrentUrl());
		WebElement ques = driver.findElement(By.xpath("//*[@id='main']/div/div/h2"));
		assertEquals("Question Page", ques.getText());
	}
	
	/**
	 * test clicking on the Answer button
	 */
	@Test
	@When("^student clicks on the Answer button for the question they want to answer$")
	public void student_clicks_on_the_Answer_button_for_the_question_they_want_to_answer() throws Throwable {		driver.findElement(By.xpath("//*[@id='stepAnswer']")).click();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/answerpage/258");
		    }
		});
	}
	
	/**
	 * test we are on the correct answer page
	 */
	@Test
	@Then("^the Answer Page opens$")
	public void the_Answer_Page_opens() throws Throwable {
		// check we are on the course/question page
		assertEquals("http://localhost:8080/static/index.html#/answerpage/258", driver.getCurrentUrl());
		WebElement answer = driver.findElement(By.xpath("//*[@id='main']/div/div/h2"));
		assertEquals("Answer Page", answer.getText());
	}
	
	/**
	 * test clicking on Submit Answer button
	 */
	@Test
	@When("^student clicks on the Submit Answer button$")
	public void student_clicks_on_the_Submit_Answer_button() throws Throwable {
		driver.findElement(By.xpath("//*[@id='stepAnswer']")).click();
	}
	
	/**
	 * test the text fields are available
	 */
	@Test
	@Then("^the answer form appears$")
	public void the_answer_form_appears() throws Throwable {
		List<WebElement> text = driver.findElements(By.xpath("//*[@id='myanswer']"));
		assertTrue(text.size() > 0);
	}
	
	/**
	 * fill in the answer and click submit
	 */
	@Test
	@When("^student fills in the answer and clicks Submit$")
	public void student_fills_in_the_answer_and_clicks_Submit() throws Throwable {
		driver.findElement(By.xpath("//*[@id='myanswer']")).sendKeys("my answer my answer");
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[3]/ng-form/a[2]")).click();
	}
	
	/**
	 * check the answer submission is successful
	 */
	@Test
	@Then("^the answer is submitted$")
	public void the_answer_is_submitted() throws Throwable {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	List<WebElement> answers = d.findElements(By.className("quiz"));
		    	return (answers.size() == 2);
		    }
		});
		login.logout(driver);
		driver.quit();
	}
}
