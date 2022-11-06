package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class accountCreationDetails {

	WebDriver driver;

	public accountCreationDetails(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(id = "id_gender1")
	WebElement titleMr;
	
	@FindBy(id="customer_firstname")
	WebElement cust_Firstname;
	
	@FindBy(id="customer_lastname")
	WebElement cust_Lastname;
	
	@FindBy(id="passwd")
	WebElement cust_Pwd;
	
	@FindBy(id="firstname")
	WebElement Add_Firstname;
	
	@FindBy(id="lastname")
	WebElement Add_Lastname;
	
	@FindBy(id="address1")
	WebElement address;
	
	@FindBy(id="city")
	WebElement city;
	
	@FindBy(id="id_state")
	WebElement state;
	
	@FindBy(id="postcode")
	WebElement postcode;
	
	@FindBy(id="id_country")
	WebElement country;
	
	@FindBy(id="phone_mobile")
	WebElement phone_mobile;
	
	@FindBy(id="alias")
	WebElement aliasAdd;
	
	@FindBy(id="submitAccount")
	WebElement submitAccount;
	
	public void selectTitleMr() {
		titleMr.click();
	}
	
	public void enterCustName(String custFname , String custLname) {
		cust_Firstname.sendKeys(custFname);
		cust_Lastname.sendKeys(custLname);
	}
	
	public void enterPassword(String password) {
		cust_Pwd.sendKeys(password);
	}
	
	public void addressName(String addFname , String addLname) {
		Add_Firstname.sendKeys(addFname);
		Add_Lastname.sendKeys(addLname);
	}
	
	public void enterAddressDetails(String Address , String cityName) {
		address.sendKeys(Address);
		city.sendKeys(cityName);
	}

	public void selectState(String text) {
		Select obj = new Select(state);
		obj.selectByVisibleText(text);
	}
	
	public void enterZipCode(String zipcode) {
		postcode.sendKeys(zipcode);
	}
	
	public void selectCountry(String text) {
		Select obj = new Select(country);
		obj.selectByVisibleText(text);
	}
	
	public void enterMobilePhone(String mobphone) {
		phone_mobile.sendKeys(mobphone);
	}
	
	public void addressAlias(String addAlias) {
		aliasAdd.sendKeys(addAlias);
	}
	
	public void clickOnRegister() {
		submitAccount.click();;
	}
}
