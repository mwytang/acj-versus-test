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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.acjvstest.driver.Login;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnswerTest {
	
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
	 * test editing a question
	 */
	@Test
	public void testEditAnswer() {
		// go to the answer page
		driver.get("http://localhost:8080/static/index.html#/answerpage/251");
		// click edit for the first question
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[4]/ul/li[1]/div[1]/div/div/div/div[1]/a")).click();
		//modify the answer
		WebElement answer = driver.findElement(By.xpath("//*[@id='editScript253']"));
		answer.clear();
		answer.sendKeys("three three");
		// submit the modification
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[4]/ul/li[1]/a[2]")).click();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	List<WebElement> answers = d.findElements(By.xpath("//*[@id='flash-messages']/li"));
		    	return (answers.size() > 0);
		    }
		});
		// check the success message is what we expected
		WebElement success = driver.findElement(By.xpath("//*[@id='flash-messages']/li"));
		assertEquals("The answer has been successfully modified.", success.getText());
	}

	/**
	 * test deleting a question
	 */
	@Test
	public void testRemoveAnswer() {
		// go to the answer page
		driver.get("http://localhost:8080/static/index.html#/answerpage/251");
		// press delete for the first answer
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[4]/ul/li[1]/div[1]/div/div/div/div[2]/a")).click();
		// access alert message
		Alert alert = driver.switchTo().alert();
		alert.accept();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	List<WebElement> answers = d.findElements(By.className("quiz"));
		    	return (answers.size() == 1);
		    }
		});
	}
}
