package FrameworkConcepts.Tests;

import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import FrameworkConcepts.pageobjects.landingpage;

import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class E2ETest {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("qa@tester.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Password@1");
		driver.findElement(By.cssSelector("input[class='btn btn-block login-btn']")).click();
		
		String[] items = {"ZARA COAT 3"};
		addItems(driver, items);
		
	}	
		private static void addItems(WebDriver driver, String[] items) {
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		 
		for(int i = 0; i<products.size(); i++) {
			//System.out.println(products);
			List<String> ItemsList = Arrays.asList(items);
			Object productname = products.get(i).findElement(By.cssSelector("h5")).getText();
			System.out.println(productname);
			 
			if(ItemsList.contains(productname)) {
				
				WebElement addbtn = products.get(i).findElement(By.cssSelector("button[class = 'btn w-10 rounded']")); 
				addbtn.click();
			}
				
}
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		WebElement AddToCart = driver.findElement(By.cssSelector("button[routerlink = '/dashboard/cart']"));
		AddToCart.click();
		
		WebElement Checkout = driver.findElement(By.cssSelector(".totalRow button"));
		Checkout.click();
		
		WebElement SelectCountry = driver.findElement(By.cssSelector("input[placeholder = 'Select Country']"));
		Actions a = new Actions(driver);
		a.sendKeys(SelectCountry, "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class = 'ta-results list-group ng-star-inserted']")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		
		driver.findElement(By.cssSelector("a[class = 'btnn action__submit ng-star-inserted']")).click();
		String finalmessage = driver.findElement(By.cssSelector("h1[class = 'hero-primary']")).getText();
		Assert.assertTrue(finalmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	  
		driver.quit();
	}
	
	
}


