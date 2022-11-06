package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

	WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(id="quantity_wanted")
	WebElement quantityWanted;

	@FindBy(id="group_1")
	WebElement size;

	@FindBy(id="add_to_cart")
	WebElement addToCart;

	@FindBy(linkText="Proceed to checkout")
	WebElement proceedToCheckout;
	
	@FindBy(name="processAddress")
	WebElement proceedAddress;
	
	@FindBy(id="cgv")
	WebElement termsOfService;
	
	@FindBy(id="processCarrier")
	WebElement shippingProceed;
	
	@FindBy(className="cheque")
	WebElement payByCheck;
	
	@FindBy(xpath = "//span[text()='I confirm my order']")
	WebElement confirmMyOrder;
	
	@FindBy(className="alert alert-success")
	WebElement successAlert;
	
	@FindBy(xpath="(//a[contains(.,'Faded Short Sleeve T-shirts')])[2]")
	WebElement tShirtProduct;
	
	@FindBy(xpath="//a[@class='addToWishlist wishlistProd_1']")
	WebElement addToWishList;
	
	@FindBy(xpath="//p[text()='You must be logged in to manage your wishlist.']")
	WebElement alertToLogin;
	
	public void setProductQuantity(String quantity) {

		quantityWanted.clear();
		quantityWanted.sendKeys(quantity);
	}

	public void setSize(String sizeType) {

		Select oSelect = new Select(size);
		oSelect.selectByVisibleText(sizeType);
	}

	public void clickOnAddToCart() {

		addToCart.click();
	}

	public void clickOnProceedToCheckout() {
		proceedToCheckout.click();
	}

	public void OrderSummaryProceedToCheckout() {
		proceedToCheckout.click();
	}
	
	public void shippingPageTermsOfService() {
		termsOfService.click();
	}
	
	public void shippingPageProceed() {
		shippingProceed.click();
	}
	
	public void OnAddressProceedToCheckout() {
		proceedAddress.click();
	}
	
	public void payByCheck() {
		payByCheck.click();
	}
	
	public void confirmMyOrder() {
		confirmMyOrder.click();
	}
	
	public String getOrderSucessMessage() {
		return(successAlert.getText());
	}
	
	public void mouseOverOnTshirtProduct() {
		Actions action = new Actions(driver);
		//hover and click
		action.moveToElement(tShirtProduct).build().perform();
	}
	
	public void clickOnAddToWishlist() {
		addToWishList.click();
	}
	
	public String getTextOfAlertToLogin() {
		return(alertToLogin.getText());
	}
}
