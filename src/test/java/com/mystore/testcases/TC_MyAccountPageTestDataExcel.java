package com.mystore.testcases;

import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.mystore.pageobject.accountCreationDetails;
import com.mystore.pageobject.indexPage;
import com.mystore.pageobject.registeredAccountLogin;
import com.mystore.pageobject.registeredUserAccount;
import com.mystore.utilities.ExtentListenerClass;
import com.mystore.utilities.ReadExcelFile;

public class TC_MyAccountPageTestDataExcel extends BaseClass {
	

	indexPage pg = new indexPage(driver);
	String data_info = (pg.generateRandomChars("ABCDFGHIJKLMNOPQRSTUVWXYZ", 4))+"123@gmail.com";
	ExtentListenerClass extentListner = new ExtentListenerClass();

	@Test(enabled=false)
	public void VerifyRegistrationAndLogin() throws IOException {

		indexPage pg = new indexPage(driver);
		pg.clickOnSignIn();
		logger.info("Clicked on sign in link");
		//extentListner.reports.get().log(Status.INFO, "Clicked on sign in link");


		pg.createEmailId(data_info);
		logger.info("Entering email id : "+data_info);

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

	@Test(dataProvider="LoginDataProvider")
	public void verifyUserLogin(String userEmail , String userPwd , String expectedUserName ) throws IOException {

		logger.info("verifyUserLogin test execution started....");
		indexPage pg = new indexPage(driver);

		registeredAccountLogin regLogin = new registeredAccountLogin(driver);

		pg.clickOnSignIn();
		logger.info("Clicked on sign in link");

		//String userEmailId = data_info;
		regLogin.registeredUserEmail(userEmail);
		//regLogin.registeredUserEmail("YPJO123@gmail.com");
		logger.info("User entered useremail is  : "+userEmail);

		regLogin.registeredUserPassword(userPwd);
		logger.info("User entered password");

		regLogin.submitUserLogin();
		logger.info("User Login Submitted...");

		registeredUserAccount reguser = new registeredUserAccount(driver);
		String userName = reguser.getUserName();

		if (userName.equals(expectedUserName)) {

			logger.info("verifyUserLogin - Passed");
			Assert.assertTrue(true);
		}
		else {
			logger.info("verifyUserLogin - Failed");
			captureScreenShot(driver, "verifyUserLogin");
			Assert.assertTrue(false);
		}

		reguser.logoutUser();
	}

	@DataProvider(name="LoginDataProvider")
	public String[][] LoginDataProvider() throws IOException{

		String fileName = System.getProperty("user.dir")+"\\TestData\\MyStoreTestData.xls";

		int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginTestData");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginTestData");

		String data[][]=new String[ttlRows-1][ttlColumns];

		for (int i = 1; i < ttlRows; i++) {

			for (int j = 0; j < ttlColumns; j++) {

				data[i-1][j]=ReadExcelFile.getCellValue(fileName, "LoginTestData", i, j);
			}
		}
		return data;
	}
}
