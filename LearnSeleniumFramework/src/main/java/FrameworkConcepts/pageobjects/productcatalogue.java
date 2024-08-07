package FrameworkConcepts.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkConcepts.abstractComponents.AbstractComponent;

public class productcatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	//create constructor
	public productcatalogue(WebDriver driver) {
		
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Pagefactory
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By toastcontainer = By.cssSelector("#toast-container");
	By spinner = By.cssSelector(".ng-animating");
	
	public void additems(String items) {
		
		List<String> ItemsList = Arrays.asList(items);
		for(WebElement product : products) {
			String productname = product.findElement(By.cssSelector("h5")).getText();
			
			if(ItemsList.contains(productname)) {
				WebElement addtocartbtn = product.findElement(By.cssSelector("button[class = 'btn w-10 rounded']")); 
				addtocartbtn.click();
				
			waiForElementToDisappear(toastcontainer);
			waiForElementToDisappear(spinner);
			
			}	
		}
	}

	
	
	
}
