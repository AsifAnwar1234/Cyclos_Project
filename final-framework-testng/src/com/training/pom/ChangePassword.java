package com.training.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ChangePassword {
	
	WebDriver driver;
	
	public ChangePassword(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "oldPassword")
	WebElement oldPassword;
	
	@FindBy(name = "newPassword")
	WebElement newPassword;
	
	@FindBy(name = "newPasswordConfirmation")
	WebElement newPasswordConfirmation;
	
	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	WebElement tableHeader;
	
	@FindBy(xpath = "//td[contains(text(),'Quick access')]")
	WebElement homePageContent;
	
	@FindBy(xpath = "//input[@value='Submit']")
	WebElement submit;
	
	public void assertPasswordPage() {
		String expected = tableHeader.getText().trim();
		System.out.println("Expected : "+expected);
		Assert.assertEquals(expected, "Change my login password");
		
	}
	
	public void enterOldPassword(String oldpassword) {
		this.oldPassword.clear();
		this.oldPassword.sendKeys(oldpassword);
	}
	
	public void enterNewPassword(String newPassword) {
		this.newPassword.clear();
		this.newPassword.sendKeys(newPassword);
	}
	
	public void enterconfirmPassword(String confrmPassword) {
		newPasswordConfirmation.clear();
		newPasswordConfirmation.sendKeys(confrmPassword);
	}
	
	public void clickSubmit() {
		this.submit.click();
	}
	
	public void assertAfterPasswordChange() {
		
		String Expected = homePageContent.getText().trim();
		System.out.println("Expected : "+Expected);
		Assert.assertEquals("Quick access", Expected);
		
	}
	
	public void assertAlertForWrongPassword(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		String actual= alert.getText().trim();
		String expected= "Passwords are not Equal\n" +"New password cannot be less than 4 characters";
		System.out.println("Actual alert : "+actual);
		
		Assert.assertEquals(expected, actual);
		alert.accept();
	}
	

}
