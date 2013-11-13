package com.acjvstest.instructor;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CoursesTest {
	
	static private WebDriver driver;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();	// exits Firefox Browser
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
        driver.get("http://localhost:8080");	// goes to acj application
     
     		WebElement username = driver.findElement(By.xpath("//*[@id='inputUser']"));
     		username.sendKeys("root");
     		// enter password
     		WebElement password = driver.findElement(By.xpath("//*[@id='inputPass']"));
     		password.sendKeys("password");
     		// sign in
     		WebElement signin = driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[3]/input"));
     		signin.click();
     	    // waits for redirect to finish; will wait 10 seconds before throwing exception
     		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/");
                }
            });
	}
	
	@Test 
	public void testCreateCourse(){
		//find Create Course button
		WebElement createCourse = driver.findElement(By.xpath("//*[@id='step1']"));
		//click on create course 
		createCourse.click(); 
		//enter EECE416 course 
		
		WebElement box = driver.findElement(By.xpath("//*[@id='courseName']"));
		box.sendKeys("EECE416");
		//find the course EECE416
		List<WebElement> course = driver.findElements(By.xpath("//*[@id='step2']/b"));
		course.get(2).click();
		// waits for redirect to finish; will wait 10 seconds before throwing exception
 		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	System.out.println(d.getCurrentUrl());
            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/questionpage/1");
            }
        });
		
		
	}

	//@Test 
	/*public void testEditCourse(){
	WebElement course = driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[1]/td[5]/a"));
	course.click(); 
	assertEquals(course.getText(),"Edit Course");
	
	WebElement addtag = driver.findElement(By.name("+ Add Tag"));
	addtag.click(); 
	addtag.sendKeys("tag1");
	
	WebElement tagg = driver.findElement(By.xpath("//*[@id='main']/div/div/form/table/tbody/tr/td[2]"));
	assertEquals(tagg.getText(),"tag1"); 
	}*/
	
	@Test 
	public void testAllCourse(){
		   List<WebElement> test = driver.findElements(By.xpath("//*[@id='step2']/b"));

	        for (int i = 0; i < test.size(); i++) {

	              System.out.println("course: " + test.get(i).getText()); // println prints it onto the console

	        }

	}
	
	

}

