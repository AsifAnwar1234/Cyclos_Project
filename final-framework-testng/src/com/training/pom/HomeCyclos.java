package com.training.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomeCyclos {
	
	WebDriver driver;
	
	public HomeCyclos(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[contains(text(),'Personal')]")
	WebElement personal;
	
	@FindBy(xpath = "//span[contains(text(),'Change password')]")
	WebElement changePassword;
	
	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	WebElement logout;
	
	public void clickPersonal() {
		this.personal.click();
		
	}
	
	public void asserHomePage() {
		String Expected = personal.getText();
		System.out.println("Expected : "+Expected);
		Assert.assertEquals("Personal", Expected);
	}
	
	public void clickChangePassword() {
		this.changePassword.click();
	}
	
	public void clickLogout() {
		logout.click();
	}
	
	public void acceptLogoutAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText().trim();
		System.out.println("Actal logout alert : "+actual);
		Assert.assertEquals(actual, "Please confirm to logout");
		alert.accept();
	}
}
