package com.acjvstest.driver;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Course {

	public void addCourse(WebDriver driver, String name) {
		// click on create course
		driver.findElement(By.xpath("//*[@id='step1']")).click(); 
		//enter EECE416 course 
		driver.findElement(By.xpath("//*[@id='courseName']")).sendKeys("EECE416");
		// click create
		driver.findElement(By.xpath("//*[@id='main']/div/form/input")).click(); 
	}
	
	public void editCourse(WebDriver driver, int courseNum) {
		//find Edit Course button and click
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id='step2']/b"));
		courses.get(courseNum).click();
		
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
}
