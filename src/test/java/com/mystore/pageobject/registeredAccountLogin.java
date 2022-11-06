package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class registeredAccountLogin {
	
	WebDriver driver;

	public registeredAccountLogin(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}


	@FindBy(id="email")
	WebElement registeredUserEmail;

	@FindBy(id="passwd")
	WebElement registeredUserPassword;

	@FindBy(id="SubmitLogin")
	WebElement submitLogin;

	public void registeredUserEmail(String regdName) {

		registeredUserEmail.sendKeys(regdName);
	}
	public void registeredUserPassword(String regdPwd) {

		registeredUserPassword.sendKeys(regdPwd);
	}
	public void submitUserLogin() {

		submitLogin.click();
	}

}
