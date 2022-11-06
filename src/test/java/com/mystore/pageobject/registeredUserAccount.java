package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class registeredUserAccount {

	WebDriver driver;

	public registeredUserAccount(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}


	@FindBy(xpath = "//a[@title='View my customer account']")
	WebElement username;

	@FindBy(className = "logout")
	WebElement logOutUser;

	@FindBy(name = "search_query")
	WebElement searchBox;

	@FindBy(name = "submit_search")
	WebElement submit_search;


	public String getUserName() {
		String text = username.getText();
		return text;
	}

	public void logoutUser() {
		logOutUser.click();
	}


	public void EnterDataInSearchBox(String searchKey) {
		searchBox.sendKeys(searchKey);
	}

	public void ClickOnSearchButton() {
		submit_search.click();
	}




}
