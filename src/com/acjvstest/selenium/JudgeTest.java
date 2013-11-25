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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.acjvstest.driver.Login;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JudgeTest {
	
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
		login.login(driver, "stu8", "password");
	}

	@Test
	public void testJudgingAnswer() {
		// first have to answer the question
		driver.get("http://localhost:8080/static/index.html#/answerpage/237");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/answerpage/237");
		    }
		});
		driver.findElement(By.xpath("//*[@id='stepAnswer']")).click();
		WebElement answer = driver.findElement(By.xpath("//*[@id='myanswer']"));
		answer.sendKeys("answer answer");
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[3]/ng-form/a[2]")).click();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/answerpage/237");
		    }
		});
		
		driver.get("http://localhost:8080/static/index.html#/judgepage/237");
		// choose the answer on the right
		driver.findElement(By.xpath("//*[@id='stepPick']/div[2]/span/span[1]")).click();
		driver.findElement(By.xpath("//*[@id='stepSubmit']")).click();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/questionpage/4");
		    }
		});
		WebElement success = driver.findElement(By.xpath("//*[@id='flash-messages']/li"));
		assertEquals("Script & Judgement updated", success.getText());
	}
	
	/**
	 * test "can't judge anymore" message
	 */
	@Test
	public void testNoMoreAnswers() {
		// checks that we are redirected to the question page since no more answers can be judged
		driver.get("http://localhost:8080/static/index.html#/judgepage/237");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/questionpage/4");
		    }
		});
		WebElement success = driver.findElement(By.xpath("//*[@id='flash-messages']/li"));
		assertEquals("Either you have already judged all of the high-priority scripts OR there are not enough answers to judge. Please come back later", success.getText());
	}
}
