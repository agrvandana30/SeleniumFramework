package FrameworkConcepts.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FrameworkConcepts.pageobjects.landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class E2EBaseTest {
	
	public WebDriver driver;
	public landingpage	LandingPage;
	
	public WebDriver InitializeDriver() throws IOException {
		
		//properties class
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
				"\\src\\main\\java\\FrameworkConcepts\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) 
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//firefox
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
}
	public List<HashMap<String,String>> getJsonDataToMap(String FilePath) throws IOException {
		
		
		//read json to string
			String jsoncontent = FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
			
			//String to Hashmap jackson databind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
					return data;	
		}
	
	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports" + testCaseName +".png" );
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports" + testCaseName +".png";
	}
	
	@BeforeMethod
	public landingpage launchApplication() throws IOException {
		
		driver = InitializeDriver();
		LandingPage = new landingpage(driver);
		LandingPage.goTo();
		return LandingPage;
	}
	
	@AfterMethod
	public void browserclose() {
		
		driver.quit();
	}

}
