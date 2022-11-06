package com.mystore.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.mystore.pageobject.ProductPage;
import com.mystore.pageobject.SearchResultPage;
import com.mystore.pageobject.indexPage;
import com.mystore.pageobject.registeredAccountLogin;
import com.mystore.pageobject.registeredUserAccount;

import junit.framework.Assert;

public class TC_ProductPageTest extends BaseClass {

	static String keySearch = "T-shirts";

	@Test(enabled=false)
	public void verifySearchProduct() throws IOException {


		logger.info("\n*************Testcase search product started*********************");

		//sign in
		indexPage pg = new indexPage(driver);
		pg.clickOnSignIn();

		//Enter account login creds
		registeredAccountLogin regLogin = new registeredAccountLogin(driver);
		//regLogin.registeredUserEmail("YPJO123@gmail.com");
		
		regLogin.registeredUserEmail(pg.userEmailID);
		logger.info("User entered useremail id"+pg.userEmailID);

		regLogin.registeredUserPassword("Pop@123");
		logger.info("User entered password");

		regLogin.submitUserLogin();
		logger.info("User Login Submitted...");

		//Search product
		registeredUserAccount regUserAcc = new registeredUserAccount(driver);
		regUserAcc.EnterDataInSearchBox(keySearch);
		regUserAcc.ClickOnSearchButton();

		SearchResultPage srp = new SearchResultPage(driver);
		String actualProdName = srp.getSearchResultProductName();

		if(actualProdName.contains(keySearch)) {
			logger.info("search product matched - Passed");
			Assert.assertTrue(true);

			regUserAcc.logoutUser();
		}
		else {
			logger.info("search product not matched - Failed");
			captureScreenShot(driver,"verifySearchProduct");
			Assert.assertTrue(false);
		}

		logger.info("\n*************Testcase search product ended*********************");
	}

	@Test(enabled=false)
	public void verifyBuyProduct() throws IOException {

		logger.info("\n*************Testcase buy product started*********************");

		indexPage pg = new indexPage(driver);
		pg.clickOnSignIn();

		//Enter account login creds
		registeredAccountLogin regLogin = new registeredAccountLogin(driver);
		//regLogin.registeredUserEmail("YPJO123@gmail.com");
		regLogin.registeredUserEmail(pg.userEmailID);
		logger.info("User entered useremail is "+pg.userEmailID);

		regLogin.registeredUserPassword("Pop@123");
		logger.info("User entered password");

		regLogin.submitUserLogin();
		logger.info("User Login Submitted...");

		//Search product
		registeredUserAccount regUserAcc = new registeredUserAccount(driver);
		regUserAcc.EnterDataInSearchBox(keySearch);
		logger.info("User has enter T-shirts in the search box");

		regUserAcc.ClickOnSearchButton();
		logger.info("User has clicked on search button");

		SearchResultPage srp = new SearchResultPage(driver);
		srp.ClickOnMoreLink();
		logger.info("User has clicked on More link");

		ProductPage pp = new ProductPage(driver);

		pp.setProductQuantity("2");
		logger.info("quantity 2 entered");

		pp.setSize("M");
		logger.info("Size M entered");

		pp.clickOnAddToCart();
		pp.clickOnProceedToCheckout();
		pp.OrderSummaryProceedToCheckout();
		pp.shippingPageTermsOfService();
		pp.shippingPageProceed();
		pp.OnAddressProceedToCheckout();
		pp.payByCheck();
		pp.confirmMyOrder();
		logger.info("click on confirmation order");

		registeredUserAccount reguser = new registeredUserAccount(driver);

		String successMsg = pp.getOrderSucessMessage();

		if(successMsg.equals("Your order on My Store is complete.")) {
			logger.info("verifyBuyProduct - Passed");
			Assert.assertTrue(true);

			reguser.logoutUser();
		}
		else {
			logger.info("verifyBuyProduct - Failed");
			captureScreenShot(driver, "verifyBuyProduct");
			Assert.assertTrue(false);
		}

		logger.info("\n*************Testcase buy product ended*********************");
	}

	@Test(enabled=false)
	public void verifySignOut() throws IOException {

		logger.info("\n*************verify sign out started*********************");

		indexPage pg = new indexPage(driver);
		pg.clickOnSignIn();

		//Enter account login creds
		registeredAccountLogin regLogin = new registeredAccountLogin(driver);
		//regLogin.registeredUserEmail("YPJO123@gmail.com");
		//logger.info("User entered useremail id");
		
		regLogin.registeredUserEmail(pg.userEmailID);
		logger.info("User entered useremail is "+pg.userEmailID);

		regLogin.registeredUserPassword("Pop@123");
		logger.info("User entered password");

		regLogin.submitUserLogin();
		logger.info("User Login Submitted...");
		
		registeredUserAccount reguser = new registeredUserAccount(driver);
		reguser.logoutUser();
		
		if(pg.getPageTitle().equals("Login - My Store")) {
			
			logger.info("verifySignOut - Passed");
			Assert.assertTrue(true);
		}
		else {
			logger.info("verifySignOut - Failed");
			captureScreenShot(driver, "verifySignOut");
			Assert.assertTrue(false);
			
		}
		logger.info("\n*************verify sign out ended*********************");

	}
	
	@Test
	public void verifyAddToWishlistWithoutLogin() throws IOException {
		
		logger.info("\n*************verify AddTo Wishlist WithoutLogin started*********************");
		
		indexPage pg = new indexPage(driver);
		pg.clickAndSearchTShirt("T-Shirts");
		pg.clickOnSearchButton();
		
		ProductPage ppage = new ProductPage(driver);
		ppage.mouseOverOnTshirtProduct();
		
		ppage.clickOnAddToWishlist();
		String actualMessage = ppage.getTextOfAlertToLogin();
		String expectedMessage = "You must be logged in to manage your wishlist.";
		
		if(actualMessage.equals(expectedMessage)){
			
			logger.info("verifyAddToWishlistWithoutLogin - Passed");
			Assert.assertTrue(true);
		}else {
			logger.info("verifyAddToWishlistWithoutLogin - Failed");
			captureScreenShot(driver, "verifyAddToWishlistWithoutLogin");
			Assert.assertTrue(false);
		}
		logger.info("\n*************verify ddTo Wishlist Without Login ended*********************");

	}
}
