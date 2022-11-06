package com.mystore.pageobject;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class indexPage {

	WebDriver driver;

public indexPage(WebDriver driver) {
	this.driver = driver;
	
	PageFactory.initElements(driver, this);
	
}

@FindBy(className = "login")
WebElement signIn;

@FindBy(id = "email_create")
WebElement createEmailId;

@FindBy(id = "SubmitCreate")
WebElement SubmitCreateEmail;

@FindBy(xpath="(//a[text()='T-Shirt'])[2]")
WebElement tShirtMenu;

@FindBy(name = "search_query")
WebElement firstSearch;

@FindBy(xpath="//button[@name='submit_search']")
WebElement clickOnSearchBtn;

//Action methods

public void clickOnSignIn() {
	signIn.click();
}

public void createEmailId(String emailAdd) {
	createEmailId.sendKeys(emailAdd);
}

public void SubmitCreateEmail() {
	SubmitCreateEmail.click();
}

public String getPageTitle() {
	return(driver.getTitle());
}

public void clickOnTShirtMenu() {
	tShirtMenu.click();
	
}

public void clickAndSearchTShirt(String searchProduct) {
	firstSearch.click();
	firstSearch.sendKeys(searchProduct);
}

public void clickOnSearchButton() {
	clickOnSearchBtn.click();
}

public static String userEmailID;

//public String getUserEmailID() {
//	return userEmailID;
//}
//
//public void setUserEmailID(String userEmailID) {
//	this.userEmailID = userEmailID;
//}

public String generateRandomChars(String candidateChars , int length) {
	
	StringBuilder sb = new StringBuilder();
	Random random = new Random();
	for (int i = 0; i < length; i++) {
		sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
	}
	
	return sb.toString();
	
}

//String data_info = (generateRandomChars("ABCDFGHIJKLMNOPQRSTUVWXYZ", 4))+"123@gmail.com";

public String getEmaiDataInfo(){
	userEmailID=null;
		
	String data_info = (generateRandomChars("ABCDFGHIJKLMNOPQRSTUVWXYZ", 4))+"123@gmail.com";
	userEmailID = data_info;
	//String text = data_info
	
	return userEmailID;
	
}




}
