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
public class AddAnswerTest {
	
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
		login.login(driver, "stu1", "password");
	}

	/**
	 * test we are on the homepage
	 */
	@Test
	public void testStepA() {
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
	public void testStepB() {
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
	public void testStepC() {
		// check we are on the course/question page
		assertEquals("http://localhost:8080/static/index.html#/questionpage/1", driver.getCurrentUrl());
		WebElement ques = driver.findElement(By.xpath("//*[@id='main']/div/div/h2"));
		assertEquals("Question Page", ques.getText());
	}
	
	/**
	 * test clicking on the Answer button
	 */
	@Test
	public void testStepD() {
		driver.findElement(By.xpath("//*[@id='stepAnswer']")).click();
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
	public void testStepE() {
		// check we are on the course/question page
		assertEquals("http://localhost:8080/static/index.html#/answerpage/258", driver.getCurrentUrl());
		WebElement answer = driver.findElement(By.xpath("//*[@id='main']/div/div/h2"));
		assertEquals("Answer Page", answer.getText());
	}
	
	/**
	 * test clicking on Submit Answer button
	 */
	@Test
	public void testStepF() {
		driver.findElement(By.xpath("//*[@id='stepAnswer']")).click();
	}
	
	/**
	 * test the text fields are available
	 */
	@Test
	public void testStepG() {
		List<WebElement> text = driver.findElements(By.xpath("//*[@id='myanswer']"));
		assertTrue(text.size() > 0);
	}
	
	/**
	 * fill in the answer and click submit
	 */
	@Test
	public void testStepH() {
		driver.findElement(By.xpath("//*[@id='myanswer']")).sendKeys("my answer my answer");
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[3]/ng-form/a[2]")).click();
	}
	
	/**
	 * check the answer submission is successful
	 */
	@Test
	public void testStepI() {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	List<WebElement> answers = d.findElements(By.className("quiz"));
		    	return (answers.size() == 2);
		    }
		});
	}
}
