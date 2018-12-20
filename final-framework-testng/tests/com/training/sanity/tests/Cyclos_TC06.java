package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.ChangePassword;
import com.training.pom.HomeCyclos;
import com.training.pom.LoginCyclos;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class Cyclos_TC06 {

	private WebDriver driver;
	private String baseUrl;
	private LoginCyclos cyclosLoginPOM;
	private HomeCyclos cyclosHomePOM;
	private ChangePassword changePassword;
	private static Properties properties;
	private ScreenShot screenShot;
	
	//public LoginCyclos myObject = new LoginCyclos(driver);

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
		changePassword = new ChangePassword(driver);
		baseUrl = properties.getProperty("baseURL");

		screenShot = new ScreenShot(driver);

		// open the browser
		driver.get(baseUrl);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void validLoginTest() {
		screenShot.captureScreenShot();
		cyclosLoginPOM.sendUserName("Asif");
		cyclosLoginPOM.sendPassword("passw0rd");
		screenShot.captureScreenShot();
		cyclosLoginPOM.clickLoginBtn();

		cyclosHomePOM.asserHomePage();

	}

	@Test(dependsOnMethods = { "validLoginTest" })
	public void validHomeTest() {
		screenShot.captureScreenShot();
		cyclosHomePOM.clickPersonal();
		cyclosHomePOM.clickChangePassword();

		changePassword.assertPasswordPage();
	}

	@Test(dependsOnMethods = { "validHomeTest" }, dataProvider = "dp")
	public void changePasswordTest(String a, String b) {
		screenShot.captureScreenShot();

		changePassword.enterOldPassword(a);
		changePassword.enterNewPassword(b);
		changePassword.enterconfirmPassword(b);
		changePassword.clickSubmit();
		driver.switchTo().alert().accept();
		changePassword.assertAfterPasswordChange();
		screenShot.captureScreenShot();

		cyclosHomePOM.clickChangePassword();

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "passw0rd", "pass123" }, new Object[] { "pass123", "passw0rd" }, };
	}
	


}
