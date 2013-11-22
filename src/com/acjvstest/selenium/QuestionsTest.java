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

import java.util.List;

public class QuestionsTest {
	
	static private WebDriver driver;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
		driver.findElement(By.xpath("//*[@id='inputUser']")).sendKeys("root");
		driver.findElement(By.xpath("//*[@id='inputPass']")).sendKeys("demo");
		driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[3]/input")).click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/");
            }
        });
	}
	
	@Test
	public void testQuestionInterface() {
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id='step2']/b"));
		courses.get(3).click();
		driver.findElement(By.xpath("//*[@id='stepNav']")).click();
		WebElement limit = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/ng-form/div[1]/div/div/input"));
		assertEquals(limit.getAttribute("disabled"), "disabled"); // check the limit field is disabled at default
		driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/ng-form/div[1]/div/div/span/input")).click();
		limit = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[2]/ng-form/div[1]/div/div/input"));
		assertNull(limit.getAttribute("disabled")); // the limit field should not be enabled
		
	}

	@Test
	public void testAddQuestion() {        

	}
	
	@Test
	public void testEditQuestion() {
		
	}
	
	@Test
	public void testDeleteQuestion() {
		
	}
	
	@Test
	public void testNotAllowEditDeleteQuestoin() {
		
	}

}
