package FrameworkConcepts.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkConcepts.abstractComponents.AbstractComponent;

public class landingpage extends AbstractComponent  {
	
	WebDriver driver;
	
	//create constructor
	public landingpage(WebDriver driver) {
		
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
	
	//Pagefactory
	
	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;
	
	@FindBy(xpath="//input[@id='userPassword']")
	WebElement passwordvalue;
	
	@FindBy(css="input[class='btn btn-block login-btn']")
	WebElement submit;
	
	public void loginApplication(String email, String password ) {
		
		userEmail.sendKeys(email);
		passwordvalue.sendKeys(password);
		submit.click();
		}
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client/");
	}
	

}
