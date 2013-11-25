package com.acjvstest.driver;

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
		driver.findElement(By.xpath("//*[@id='courseName']")).sendKeys(name);
		// click create
		driver.findElement(By.xpath("//*[@id='main']/div/form/input")).click();
	}
	
	public void editCourse(WebDriver driver, int courseNum) {
		//find Edit Course button and click	
		driver.findElement(By.xpath("//*[@id='main']/div/table/tbody/tr[1]/td[5]/a")).click();
		// waits for redirect to finish; will wait 10 seconds before throwing exception
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().startsWith("http://localhost:8080/static/index.html#/editcourse/");
		    }
		});
		
		// click Edit
		driver.findElement(By.xpath("//*[@id='main']/div/div/a[1]")).click();
		// change course name
		WebElement name = driver.findElement(By.xpath("//*[@id='main']/div/div/form/span[2]/input"));
		name.clear();
		name.sendKeys("APSC 150 101");
		
		driver.findElement(By.xpath("//*[@id='main']/div/div/a[2]")).click();
	}
	
	public void addTag(WebDriver driver, String tag) {
		//Find the add tag box 
	 	driver.findElement(By.xpath("//*[@id='main']/div/div/form/input")).sendKeys(tag);
	 	// click "Add Tag"
	 	driver.findElement(By.xpath("//*[@id='main']/div/div/form/button")).click();
	}
	
	public void deleteTag(WebDriver driver, int tagNum) {
	 	//click the delete button
	 	driver.findElement(By.xpath("//*[@id='main']/div/div/form/table/tbody/tr/td[1]/button/span")).click();
	}
	
	public void searchCourse(WebDriver driver, String name) {
		driver.findElement(By.xpath("//*[@id='main']/div/div[2]/form/div/input")).sendKeys(name);

	}
}
