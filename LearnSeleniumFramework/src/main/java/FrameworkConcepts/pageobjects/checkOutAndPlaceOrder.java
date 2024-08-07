package FrameworkConcepts.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkConcepts.abstractComponents.AbstractComponent;

public class checkOutAndPlaceOrder extends AbstractComponent  {
	
	WebDriver driver;
	
	//create constructor
	public checkOutAndPlaceOrder(WebDriver driver) {
		
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".totalRow button")
	WebElement CheckOut;
	
	@FindBy(css="input[placeholder = 'Select Country']")
	WebElement Country;
	
	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement SelectIndia;
	
	By waitresults = By.cssSelector("section[class = 'ta-results list-group ng-star-inserted']");
	
	@FindBy(css = "a[class = 'btnn action__submit ng-star-inserted']")
	WebElement PlaceOrder;
	
	@FindBy(css = "h1[class = 'hero-primary']")
	WebElement ConfirmatioMessage;
	
	
	public void clickCheckOut() {
		
		CheckOut.click();
	}
	
	public void selectCountry(String countryname) {
		
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryname).build().perform();
		waiForElementToAppear(waitresults);
		SelectIndia.click();
	}
	
	public void placeorder() {
		
	PlaceOrder.click();
	
	}
	
	
	
	public String getConfirmationMessage() {
		
		return ConfirmatioMessage.getText();
	}

}
