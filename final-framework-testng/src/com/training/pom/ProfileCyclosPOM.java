package com.training.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProfileCyclosPOM {
	
	WebDriver driver;
	
	public ProfileCyclosPOM(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//span[contains(text(),'Profile')]")
	WebElement profile;
	
	@FindBy(xpath = "//td[@class=\"tdHeaderTable\"]")
	WebElement profilePageHeader;
	
	@FindBy(id = "modifyButton")
	WebElement changeButton;
	
	@FindBy(xpath = "//input[@name='admin(name)']")
	WebElement adminTextBox;
	
	@FindBy(id = "saveButton")
	WebElement submitButton;
	
	@FindBy(xpath = "//span[@class='loginData']")
	WebElement adminName;
	
	public void clickProfile() {
		this.profile.click();
	}
	
	public void assertProfilePage() {
		String actual = profilePageHeader.getText().trim();
		System.out.println("Profile header : "+actual);
		String expected = "My admin profile";
		Assert.assertEquals(actual, expected);
		
	}
	
	public void clickChangeButton() {
		this.changeButton.click();
	}
	
	public void enterTextInAdmin(String str) {
		this.adminTextBox.clear();
		this.adminTextBox.sendKeys(str);
	}
	
	public void clickSubmitButton() {
		this.submitButton.click();
	}
	
	public void alertHandle(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		String alertString = alert.getText();
		System.out.println("Alert String : "+alertString);
		Assert.assertEquals(alertString, "Profile modified");
		alert.accept();
	}
	
	public void asserAdminNameChange(String str) {
		String actual = adminName.getText().trim();
		System.out.println("Admin name after name change : "+actual);
		String expected = "Logged user: admin - "+str;
		Assert.assertEquals(actual, expected);
		
	}
	

}
