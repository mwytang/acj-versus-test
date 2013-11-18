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
	
	private static final String inputUserXPath = "//*[@id='inputUser']";

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
     
     		WebElement username = driver.findElement(By.xpath(inputUserXPath));
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
	
	/**
	 * tests creating a course as a prof. 
	 */
	@Test 
	public void testCreateCourse(){
		//find Create Course button
		WebElement createCourse = driver.findElement(By.xpath("//*[@id='step1']"));
		//click on create course 
		createCourse.click(); 
		
		//enter EECE416 course 
		WebElement box = driver.findElement(By.xpath("//*[@id='courseName']"));
		//type EECE416
		box.sendKeys("EECE416");
		// find create button 
		WebElement create = driver.findElement(By.xpath("//*[@id='main']/div/form/input"));
		create.click(); 
		
		// click on the course
		List<WebElement> course = driver.findElements(By.xpath("//*[@id='step2']/b"));
		course.get(0).click();
	
		
		// waits for redirect to finish; will wait 10 seconds before throwing exception
 		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/questionpage/5");
            }
        });
			
	}
	

	/**
	 * tests editing a course -> adding and deleting tag 
	 */
	@Test 
	public void testEditCourse(){
		//go to home page
		driver.get("http://localhost:8080");
		//find Edit Course button and click
		WebElement editCourse = driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[1]/td[5]/a"));
		editCourse.click();
		// waits for redirect to finish; will wait 10 seconds before throwing exception
	 		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/editcourse/5");
	            }
	        });
	 		
	 		
	 	//Find the add tag box 
	 	WebElement box = driver.findElement(By.xpath("//*[@id='main']/div/div/form/input"));
	 	// type "tag1"
	 	box.sendKeys("tag1");
	 	// find Add Tag button and click
	 	WebElement addTagButton = driver.findElement(By.xpath("//*[@id='main']/div/div/form/button"));
	 	addTagButton.click();
	 	
	 	
	 	WebElement tag = driver.findElement(By.xpath("//*[@id='main']/div/div/form/table/tbody/tr/td[2]"));
	 	assertEquals(tag.getText(), "tag1");
	 	
	 	//find the delete button
	 	WebElement delete = driver.findElement(By.xpath("//*[@id='main']/div/div/form/table/tbody/tr/td[1]/button/span"));
	 	//click on delete button 
	 	delete.click();
	 	
	}
	
	/**
	 * tests View Statistics 
	 */
	@Test 
	public void testViewStatistics(){
		//go to home page
		driver.get("http://localhost:8080");
		//find View Statistics button and click
		WebElement viewStat = driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[1]/td[6]/a"));
		viewStat.click();
		// waits for redirect to finish; will wait 10 seconds before throwing exception
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/stats/5");
			}
		});
			
	}
	
	/**
	 * tests viewing question(s)  
	 */
	@Test 
	public void testViewQuestions(){
		//go to home page
		driver.get("http://localhost:8080");
		//find question(s) button for EECE416 course and click
		WebElement viewQuestion = driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[1]/td[2]/span"));
		viewQuestion.click();
		// waits for redirect to finish; will wait 10 seconds before throwing exception
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/questionpage/5");
			}
		});
			
	}
	
	/**
	 * tests searching a course  
	 */
	@Test 
	public void testSearchCourse(){
		//go to home page
		driver.get("http://localhost:8080");
		//find the search course box 
		WebElement searchBox = driver.findElement(By.xpath("//*[@id='main']/div/div[2]/form/div/input"));
		//type EECE416
		searchBox.sendKeys("EECE416");
		//checks if the course visible on the page is EECE416
		WebElement courses = driver.findElement(By.xpath("//*[@id='step2']/b"));
		assertEquals(courses.getText(),"EECE416");
	}
	
	/**
	 * Prints out all the courses 
	 */
	@Test 
	public void testAllCourse(){
		   List<WebElement> test = driver.findElements(By.xpath("//*[@id='step2']/b"));
		   for (int i = 0; i < test.size(); i++) {
	              System.out.println("course: " + test.get(i).getText()); // println prints it onto the console
	        }
	}
	

}

