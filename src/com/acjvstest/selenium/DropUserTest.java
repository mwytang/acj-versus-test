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

public class DropUserTest {
	
	static private WebDriver driver;
	static private Login login = new Login();

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		login.logout(driver);
		driver.quit();	// exist Browser
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
	 * unenrolling a student
	 */
	@Test
	public void testEnrollment() {
		driver.get("http://localhost:8080/static/index.html#/enrollpage/1");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/enrollpage/1");
		    }
		});
		driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/table/tbody/tr[5]/td[2]/div/p")).click();
		WebElement status = driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div/table/tbody/tr[5]/td[2]/div/p"));
		assertEquals("Unenrolled", status.getText());
	}

}
