package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {

	WebDriver driver;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}
	
	
	@FindBy(xpath="(//a[@title='Faded Short Sleeve T-shirts'])[2])")
	WebElement searchResultProduct;
	
	@FindBy(linkText = "More")
	WebElement more;
	
	public String getSearchResultProductName() {
		return (searchResultProduct.getText());
		
	}
	
	public void ClickOnMoreLink() {
		more.click();
		
	}
}
