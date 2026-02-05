package com.learning.tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.learn.pages.LoginPageA;

public class FirstTest {

	@Test
	void test1() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(4000);
		LoginPageA loginpageA=new LoginPageA(driver);
	}
	
}
