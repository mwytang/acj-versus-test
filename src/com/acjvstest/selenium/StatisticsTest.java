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
public class StatisticsTest {
	
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
	 * tests that the statistics displayed are accurate
	 */
	@Test
	public void testStatistics() {
		// goes to BIOL 111 statistics page
		driver.get("http://localhost:8080/static/index.html#/stats/2");
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.findElement(By.xpath("//*[@id='main']/div/h2")).getText().equalsIgnoreCase("BIOL 111");
		    }
		});
		// test that the correct stats are printed for Student Five
		WebElement name = driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[4]/td[1]"));
		assertEquals("Student Five", name.getText());
		WebElement ques = driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[4]/td[2]"));
		assertEquals("1", ques.getText());
		WebElement percent = driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[4]/td[3]"));
		assertEquals("10", percent.getText());
		WebElement ans = driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[4]/td[4]"));
		assertEquals("6", ans.getText());
		percent = driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[4]/td[5]"));
		assertEquals("13.64", percent.getText());
		WebElement avg = driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[4]/td[6]"));
		assertEquals("1.65", avg.getText());
	}

}
