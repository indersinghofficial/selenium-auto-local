package com.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageA {
	WebDriver driver;
	
	public LoginPageA(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath = "//input[@name='username']")
    WebElement usernameField;
	
	public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

}
