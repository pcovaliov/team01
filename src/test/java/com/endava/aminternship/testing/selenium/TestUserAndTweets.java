package com.endava.aminternship.testing.selenium;

import java.util.Random;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserAndTweets {
	private static WebDriver driver;
	private String baseUrl = "http://localhost:8080/aminternship/";
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();
	static final private String adminEmail = "admin@admin";
	private static String generatedUserEmail;
	private static String userEmailForTweets = adminEmail;

	@BeforeClass
	static public void setUp() throws Exception {
		/*
		 * driver = new FirefoxDriver();
		 * driver.get("http://localhost:8080/aminternship/");
		 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 */
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver_win32/chromedriver.exe");

		driver = new ChromeDriver();
		Random r = new Random();
		int randomNumber = r.nextInt(99999 + 1);
		generatedUserEmail = "test" + randomNumber +"@mail.com";
		
	}

	
	@Test
	public void testCreateUser() throws Exception {
			
		driver.get(baseUrl);
		
	    driver.findElement(By.linkText("Register User")).click();
	    driver.findElement(By.id("firstname")).clear();
	    driver.findElement(By.id("firstname")).sendKeys("nameTest");
	    driver.findElement(By.id("lastname")).clear();
	    driver.findElement(By.id("lastname")).sendKeys("nameTest");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys(generatedUserEmail);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.cssSelector("#see-all-users-link > a")).click();
	    driver.findElement(By.linkText("2")).click();
	    driver.findElement(By.linkText("82")).click();
	    driver.findElement(By.linkText("Logout")).click();
		
	}
	
	@Test
	  public void testCreateTweet() throws Exception {
		 driver.get(baseUrl);
		    driver.findElement(By.linkText("Login")).click();
		    driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys(userEmailForTweets);
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    driver.findElement(By.id("tweet")).clear();
		    driver.findElement(By.id("tweet")).sendKeys("test Tweet selenium");
		    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		    driver.findElement(By.cssSelector("input[type=\"search\"]")).clear();
		    driver.findElement(By.cssSelector("input[type=\"search\"]")).sendKeys("sel");
		    driver.findElement(By.linkText("Logout")).click();
	   
	  }
	

	@AfterClass
	static public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
