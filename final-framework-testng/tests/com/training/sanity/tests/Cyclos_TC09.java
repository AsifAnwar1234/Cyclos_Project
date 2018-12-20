package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.HomeCyclos;
import com.training.pom.LoginCyclos;
import com.training.pom.ProfileCyclosPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class Cyclos_TC09 {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginCyclos cyclosLoginPOM;
	private HomeCyclos cyclosHomePOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private ProfileCyclosPOM profile;
	
	@BeforeTest
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		cyclosLoginPOM = new LoginCyclos(driver);
		cyclosHomePOM = new HomeCyclos(driver);
		profile = new ProfileCyclosPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test
	public void loginPageTest() {
		screenShot.captureScreenShot();
		cyclosLoginPOM.sendUserName("Admin");
		cyclosLoginPOM.sendPassword("1234");
		screenShot.captureScreenShot();
		cyclosLoginPOM.clickLoginBtn();
		cyclosHomePOM.asserHomePage();
	}
	
	@Test(dependsOnMethods = "loginPageTest")
	public void profilePageTest() {
		cyclosHomePOM.clickPersonal();
		profile.clickProfile();
		profile.assertProfilePage();
		profile.clickChangeButton();
		profile.enterTextInAdmin("AdminTest");
		profile.clickSubmitButton();
		profile.alertHandle(driver);
		profile.asserAdminNameChange("AdminTest");
	}

}
