package com.mystore.testcases;

import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.mystore.pageobject.accountCreationDetails;
import com.mystore.pageobject.indexPage;
import com.mystore.pageobject.registeredAccountLogin;
import com.mystore.pageobject.registeredUserAccount;

public class TC_MyAccountPageTest extends BaseClass {

	//indexPage pg = new indexPage(driver);
	//String data_info = (pg.generateRandomChars("ABCDFGHIJKLMNOPQRSTUVWXYZ", 4))+"123@gmail.com";
	
	
	@Test
	public void VerifyRegistrationAndLogin() throws IOException {

		indexPage pg = new indexPage(driver);
		pg.clickOnSignIn();
		logger.info("Clicked on sign in link");

		

		pg.createEmailId(pg.getEmaiDataInfo());
		logger.info("Entering email id : "+pg.userEmailID);

		pg.SubmitCreateEmail();

		accountCreationDetails accdetails = new accountCreationDetails(driver);

		accdetails.selectTitleMr();
		accdetails.enterCustName("Sncool", "Nayak");
		accdetails.enterPassword("Pop@123");
		//accdetails.addressName("Sncool","Nayak");
		accdetails.enterAddressDetails("106B Avenue Street", "Bhubaneswar");
		accdetails.selectState("Alaska");
		accdetails.enterZipCode("00000");
		accdetails.selectCountry("United States");
		accdetails.enterMobilePhone("9437177063");
		accdetails.addressAlias("Home");
		accdetails.clickOnRegister();

		registeredUserAccount reguser = new registeredUserAccount(driver);
		String userName = reguser.getUserName();

		if (userName.equals("Sncool Nayak")) {

			logger.info("VerifyRegistrationAndLogin - Passed");
			Assert.assertTrue(true);
		}
		else {
			logger.info("VerifyRegistrationAndLogin - Failed");
			captureScreenShot(driver, "VerifyRegistrationAndLogin");
			Assert.assertTrue(false);
		}

		//Assert.assertEquals("Sncool Nayak1" , userName);

		reguser.logoutUser();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@Test
	public void verifyUserLogin() throws IOException {

		logger.info("verifyUserLogin test execution started....");
		indexPage pg = new indexPage(driver);

		registeredAccountLogin regLogin = new registeredAccountLogin(driver);

		pg.clickOnSignIn();
		logger.info("Clicked on sign in link");

		//String userEmailId = data_info;
		regLogin.registeredUserEmail(pg.userEmailID);
		//regLogin.registeredUserEmail("YPJO123@gmail.com");
		logger.info("User entered useremail id : "+pg.userEmailID);

		regLogin.registeredUserPassword("Pop@123");
		logger.info("User entered password");

		regLogin.submitUserLogin();
		logger.info("User Login Submitted...");

		registeredUserAccount reguser = new registeredUserAccount(driver);
		String userName = reguser.getUserName();

		if (userName.equals("Sncool Nayak")) {

			logger.info("verifyUserLogin - Passed");
			Assert.assertTrue(true);
		}
		else {
			logger.info("verifyUserLogin - Failed");
			captureScreenShot(driver, "verifyUserLogin");
			Assert.assertTrue(false);
		}
	}
}
