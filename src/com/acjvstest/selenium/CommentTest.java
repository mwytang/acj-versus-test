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
public class CommentTest {

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

	/**
	 * test adding a comment
	 */
	@Test
	public void testAddComment() {
		// access a question from STAT 200
		driver.get("http://localhost:8080/static/index.html#/answerpage/237");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/answerpage/237");
		    }
		});
		// click show comments
		driver.findElement(By.xpath("//*[@id='main']/div/div/a[3]")).click();
		// click leave a comment
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/div/a")).click();
		// leave a comment
		WebElement comment = driver.findElement(By.xpath("//*[@id='mycommentQuestion']"));
		comment.sendKeys("comment comment");
		// click submit
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/div/div/ng-form/a")).click();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return (d.findElements(By.xpath("//*[@id='flash-messages']/li")).size() > 0);
		    }
		});
		WebElement success = driver.findElement(By.xpath("//*[@id='flash-messages']/li"));
		assertEquals("The comment has been successfully added", success.getText());
	}

	/**
	 * test editing a comment 
	 */
	@Test
	public void testEditComment() {
		// access a question from STAT 200
		driver.get("http://localhost:8080");
		driver.get("http://localhost:8080/static/index.html#/answerpage/237");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/answerpage/237");
		    }
		});
		// click show comments
		driver.findElement(By.xpath("//*[@id='main']/div/div/a[3]")).click();
		// click edit
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/div/ul/li/div[1]/a[1]")).click();
		// modify comment
		WebElement comment = driver.findElement(By.xpath("//*[@id='editcom312']"));
		comment.clear();
		comment.sendKeys("new comment new comment");
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/div/ul/li/a")).click();
		// check that the modification is successful
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return (d.findElements(By.xpath("//*[@id='flash-messages']/li")).size() > 0);
		    }
		});
		WebElement success = driver.findElement(By.xpath("//*[@id='flash-messages']/li"));
		assertEquals("The comment has been successfully modified", success.getText());
	}
	
	/**
	 * test deleting a comment
	 */
	@Test
	public void testRemoveComment() {
		// access a question from STAT 200
		driver.get("http://localhost:8080");
		driver.get("http://localhost:8080/static/index.html#/answerpage/237");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/answerpage/237");
		    }
		});
		// click show comments
		driver.findElement(By.xpath("//*[@id='main']/div/div/a[3]")).click();
		// click delete
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/div/ul/li/div[1]/a[2]")).click();
		// access alert message
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		driver.get("http://localhost:8080");
		driver.get("http://localhost:8080/static/index.html#/answerpage/237");
		WebElement numQues = driver.findElement(By.xpath("//*[@id='main']/div/div/a[3]"));
		assertEquals(numQues.getText(), "Show Comments (0)");
	}
}
