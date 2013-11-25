package com.acjvstest.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.acjvstest.driver.Login;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuestionsTest {
	
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
		driver = new FirefoxDriver();	// creates Firefox Browser
		driver.get("http://localhost:8080");
		login.login(driver, "admin", "password");
	}
	
	/**
	 * testing the form interface
	 */
	@Test
	public void testFormInterface() {
		driver.get("http://localhost:8080/static/index.html#/questionpage/5");
		driver.findElement(By.xpath("//*[@id='stepNav']")).click();
		WebElement limit = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/ng-form/div[1]/div/div/input"));
		assertEquals(limit.getAttribute("disabled"), "true"); // check the limit field is disabled at default
		driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/ng-form/div[1]/div/div/span/input")).click();
		limit = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/ng-form/div[1]/div/div/input"));
		assertNull(limit.getAttribute("disabled")); // the limit field should not be enabled
	}
	
	/**
	 * test editing questions
	 */
	@Test
	public void testModifyQuestion() {
		driver.get("http://localhost:8080/static/index.html#/questionpage/5");
		// click on edit for the first question
		driver.findElement(By.xpath("//*[@id='main']/div/div/div/ul/li[1]/div[1]/div[3]/div/div/div[1]/a")).click();
		// modify the title and content of the question
		WebElement title = driver.findElement(By.xpath("//*[@id='main']/div/div/div/ul/li[1]/div[3]/ng-form/div[2]/div/input"));
		title.clear();
		title.sendKeys("one one");
		WebElement ques = driver.findElement(By.xpath("//*[@id='question310']"));
		ques.clear();
		ques.sendKeys("one one");
		driver.findElement(By.xpath("//*[@id='main']/div/div/div/ul/li[1]/div[3]/ng-form/a[2]")).click();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return (d.findElements(By.xpath("//*[@id='flash-messages']/li")).size() > 0);
		    }
		});
		assertEquals("The question has been successfully modified.", driver.findElement(By.xpath("//*[@id='flash-messages']/li")).getText());
	}
	
	@Test
	public void testRemoveQuestion() {
		driver.get("http://localhost:8080/static/index.html#/questionpage/5");
		driver.findElement(By.xpath("//*[@id='main']/div/div/div/ul/li[1]/div[1]/div[3]/div/div/div[2]/a")).click();
		// access alert message
		Alert alert = driver.switchTo().alert();
		alert.accept();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return (d.findElements(By.xpath("//*[@id='flash-messages']/li")).size() > 0);
		    }
		});
		assertEquals("The question has been successfully deleted.", driver.findElement(By.xpath("//*[@id='flash-messages']/li")).getText());
	}
}
