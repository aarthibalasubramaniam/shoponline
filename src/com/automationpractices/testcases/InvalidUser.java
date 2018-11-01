package com.automationpractices.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InvalidUser {
	WebDriver driver;

	@BeforeClass
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
	}
	
	@Test (priority = 0)
	public void verifyLoginPage()
	{
		String currentTitle = driver.getTitle();
		String currentURL = driver.getCurrentUrl();
		String expectedTitle = "Login - My Store";
		String expectedURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
		Assert.assertEquals(expectedURL, currentURL);
		Assert.assertEquals(expectedTitle, currentTitle);
		
	}
	
	@Test (priority = 1)
	public void invalidUser(){
	driver.findElement(By.id("email")).click();
	driver.findElement(By.id("email")).sendKeys("aarthibvs@gmail.com");
	driver.findElement(By.id("passwd")).click();	
	driver.findElement(By.id("passwd")).sendKeys("Welcome");
	driver.findElement(By.id("SubmitLogin")).click();
	//*[@id="columns"]/div[1]/span[1]
	String actualMsg = driver.findElement(By.xpath("//*[@id='center_column']/div[1]/ol/li")).getText();
	String expectedMsg = "Authentication failed.";
	
	Assert.assertEquals(actualMsg, expectedMsg);
	}

}
