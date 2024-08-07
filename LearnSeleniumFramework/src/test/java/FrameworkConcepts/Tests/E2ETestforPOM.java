package FrameworkConcepts.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameworkConcepts.TestComponents.E2EBaseTest;
import FrameworkConcepts.pageobjects.checkOutAndPlaceOrder;
import FrameworkConcepts.pageobjects.landingpage;
import FrameworkConcepts.pageobjects.productcatalogue;

import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class E2ETestforPOM extends E2EBaseTest{

	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void E2ETest(HashMap<String,String> input ) throws IOException {
		
		LandingPage.loginApplication(input.get("email"),input.get("password"));
		
		productcatalogue ProductCatalogue = new productcatalogue(driver);
		
		ProductCatalogue.additems(input.get("items"));
		ProductCatalogue.goToCart();
		
		checkOutAndPlaceOrder Order = new checkOutAndPlaceOrder(driver);
		Order.clickCheckOut();
		Order.selectCountry("India");
		Order.placeorder();
		String finalmessage = Order.getConfirmationMessage();
		Assert.assertTrue(finalmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\FrameworkConcepts\\data\\dataset.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}
	
	
	//@DataProvider
	//public Object[][] getData() {
		
	//	return new Object[][] {{(Object) "qa@tester.com", (Object) "Password@1", (Object) "ZARA COAT 3" }, {(Object) "qa12@tester.com", (Object) "Password@02", (Object) "IPHONE 13 PRO"}};
	//}
	//@DataProvider
//	public Object[][] getData() {
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "qa@tester.com");
//		map.put("password", "Password@1");
//		map.put("items", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "qa12@tester.com");
//		map1.put("password", "Password@02");
//		map1.put("items", "IPHONE 13 PRO");
//		
//		return new Object[][] {{map}, {map1}};
		
	}
	
	



