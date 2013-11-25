package com.acjvstest.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

	// login user with provided username and password
	public void login(WebDriver driver, String username, String password) {
		// enter username
		driver.findElement(By.xpath("//*[@id='inputUser']")).sendKeys(username);
		// enter password
		driver.findElement(By.xpath("//*[@id='inputPass']")).sendKeys(password);
		// sign in
		driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[3]/input")).click();
		// waits for redirect to finish; will wait 10 seconds before throwing exception
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/");
            }
        });
	}
	
	public void logout(WebDriver driver) {
		// opens user's menu
		driver.findElement(By.xpath("/html/body/div[2]/nav/ul[2]/li[1]/a")).click();
		// click logout
		driver.findElement(By.xpath("/html/body/div[2]/nav/ul[2]/li[1]/ul/li[2]/a")).click();
		// waits for redirect to login page
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		    	return d.getCurrentUrl().equalsIgnoreCase("http://localhost:8080/static/index.html#/login");
		    }
		});
	}
	
	public void invalidLogin(WebDriver driver, String username, String password) {
		// enter username
		driver.findElement(By.xpath("//*[@id='inputUser']")).sendKeys(username);
		// enter password
		driver.findElement(By.xpath("//*[@id='inputPass']")).sendKeys(password);
		// sign in
		driver.findElement(By.xpath("//*[@id='main']/div/div/form/div[3]/input")).click();
	}
}
