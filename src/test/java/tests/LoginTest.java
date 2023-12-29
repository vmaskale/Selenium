package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Resource.Base;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;

public class LoginTest extends Base {
	public WebDriver driver;
	Logger log;

	@BeforeMethod
	public void openApplication() throws IOException {

		log = LogManager.getLogger(LoginTest.class.getName());
		driver = initializebrowser();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");

	}

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException {

		// object for LandingPage
		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		log.debug("Clicked on My Account dropdown");
		landingPage.loginOption().click();
		log.debug("Clicked on login option");
		// Object for LoginPage
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressTextField().sendKeys(email);
		log.debug("Email addressed got entered");
		loginPage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginPage.loginButton().click();
		log.debug("Clicked on Login Button");
		// Object for Account Page
		AccountPage accountPage = new AccountPage(driver);

		String acutualResult = null;

		try {

			if (accountPage.editYourAccountInformation().isDisplayed()) {
				log.debug("User got logged in");
				acutualResult = "Success";
			}

		} catch (Exception e) {
			log.debug("User didn't log in");
			acutualResult = "Failure";

		}

		if (acutualResult.equals(expectedResult)) {

			log.info("Login Test got passed");

		} else {

			log.error("Login Test got failed");
		}

	}

	@AfterMethod
	public void closure() {

		driver.close();
		log.debug("Browser got closed");

	}

	@DataProvider
	public Object[][] getLoginData() {

		Object[][] data = { { "arun.selenium@gmail.com", "Second@123", "Success" }};

		return data;

	}

}