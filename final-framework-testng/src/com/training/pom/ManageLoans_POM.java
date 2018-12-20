package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ManageLoans_POM {
	
	WebDriver driver;
	
	public ManageLoans_POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text() =\"Accounts\"]")
	WebElement accounts;
	
	@FindBy(xpath = "//span[contains(text(),'Manage Loans')]")
	WebElement manageLoans;
	
	@FindBy(xpath = "//td[@class='tdHeaderTable']")
	WebElement headerText;
	
	@FindBy(id = "memberUsername")
	WebElement memberUsername;
	
	@FindBy(xpath = "//input[@class='button']")
	WebElement submit;
	
	@FindBy(xpath = "//td[contains(text(),'Search results')]")
	WebElement searchResult;
	
	@FindBy(xpath = "//th[contains(text(),'Description')]")
	WebElement description;
	
	@FindBy(xpath = "//th[contains(text(),'Amount')]")
	WebElement amount;
	
	public void clickOnLinks() {
		accounts.click();
		manageLoans.click();
	}
	
	public void verifyManageLoansPage() {
		String actual = headerText.getText().trim();
		System.out.println("manage loan page : "+actual);
		String expected = "Search loans";
		Assert.assertEquals(actual, expected);
	}
	
	public void enterUserName(String name) {
		memberUsername.clear();
		memberUsername.sendKeys(name);
		submit.click();
	}
	
	public void verifySearchResult() {
		boolean searchResul = searchResult.isDisplayed();
		boolean desc = description.isDisplayed();
		boolean amunt = amount.isDisplayed();
		
		System.out.println("searchResult "+searchResul+", description "+desc+", Amount "+amunt);
		
		Assert.assertEquals(searchResul, desc);
	}

}
