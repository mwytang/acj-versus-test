package com.acjvstest.instructor;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InstructorLoginTest {
	
	private WebDriver driver = new FirefoxDriver();

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
        driver.get("http://localhost:8080");
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	@Test
	public void testRegularLogin() {
		WebElement username = driver.findElement(By.xpath("//*[@id='inputUser']"));
		username.sendKeys("root");
		WebElement password = driver.findElement(By.xpath("//*[@id='inputPass']"));
		password.sendKeys("demo");
		WebElement signin = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[3]/input"));
		signin.click();
		
	}

}
