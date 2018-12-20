package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginCyclos {
	
	private WebDriver driver;

	public LoginCyclos(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "cyclosUsername")
	private WebElement username;
	
	@FindBy(id = "cyclosPassword")
	private WebElement password;
	
	@FindBy(xpath = "//td[@colspan='2']//input[@value='Submit']")
	private WebElement Submit;
	
	@FindBy(xpath = "//div[contains(text(),'If you are a registered member, you can login with')]")
	private WebElement loginPageText;
	
	public void sendUserName(String userName) {
		this.username.clear();
		this.username.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.Submit.click(); 
	}
	
	public void assertLogout() {
		String actual = loginPageText.getText().trim();
		System.out.println("Actual : "+actual);
		String expected = "If you are a registered member, you can login with your username and password below.";
		Assert.assertEquals(actual, expected);
	}
	
	
}
