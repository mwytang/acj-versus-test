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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddQuestionTest {

	static private WebDriver driver;
	static private Login login = new Login();
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		login.logout(driver);
		driver.quit();		
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
	 * test we are on the homepage
	 */
	@Test
	public void testStepA() {
		driver.get("http://localhost:8080");
		assertEquals("http://localhost:8080/static/index.html#/", driver.getCurrentUrl());
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/");
		    }
		});
	}
	
	/**
	 * test clicking on the preferred course's name
	 */
	@Test
	public void testStepB() {
		driver.findElement(By.xpath("//*[@id='step2']")).click();
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
	public void testStepC() {
		// check we are on the course/question page
		assertEquals("http://localhost:8080/static/index.html#/questionpage/1", driver.getCurrentUrl());
		WebElement ques = driver.findElement(By.xpath("//*[@id='main']/div/div/h2"));
		assertEquals("Question Page", ques.getText());
	}
	
	/**
	 * click on the "Create Question" button
	 */
	@Test
	public void testStepD() {
		driver.findElement(By.xpath("//*[@id='stepNav']")).click();
	}
	
	/**
	 * test the text fields are available
	 */
	@Test
	public void testStepE() {
		List<WebElement> text = driver.findElements(By.xpath("//*[@id='myquestion']"));
		assertTrue(text.size() > 0);
	}
	
	/**
	 * fill in the form and click submit
	 */
	@Test
	public void testStepF() {
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
	public void testStepG() {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return (d.findElements(By.xpath("//*[@id='flash-messages']/li")).size() > 0);
		    }
		});
		assertEquals("The question has been successfully added.", driver.findElement(By.xpath("//*[@id='flash-messages']/li")).getText());
	}
}
