package com.mystore.testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import com.mystore.utilities.ReadConfig;

public class BaseClass {

	ReadConfig rc = new ReadConfig();

	String url = rc.getBaseUrl();
	String browser = rc.getBrowser();

	public static WebDriver driver;
	public static Logger logger;
	String projectpath = System.getProperty("user.dir");

	@BeforeClass
	public void RunBrowser() {

		if (browser.contains("chrome")) { 
			String path = System.setProperty("webdriver.chrome.driver",projectpath+"/Drivers/chromedriver.exe");

			System.out.println(path); 

			driver = new ChromeDriver(); 
			}
		else if(browser.contains("msedge")) {
			String path = System.setProperty("webdriver.chrome.driver",projectpath+"/Drivers/msedgedriver.exe");

			System.out.println(path); 

			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger = LogManager.getLogger("MyStoreV1");

		driver.get(url);
		logger.info("url is opened");
	}



	//	  public void setup() {
	//	  
	//	  switch (browser.toLowerCase()) { 
	//	  case "chrome":
	//	  //WebDriverManager.chromedriver().setup();
	//	  System.setProperty("webdriver.chrome.driver",projectpath+"/Drivers/chromedriver.exe"); 
	//	  //System.out.println(); 
	//	  driver = new ChromeDriver(); 
	//	  break;
	//	  
	//	  case "msedge": 
	//		  WebDriverManager.edgedriver().setup(); 
	//		  driver = new EdgeDriver(); 
	//		  break;
	//	  
	//	  case "firefox": WebDriverManager.firefoxdriver().setup(); 
	//	  driver = new FirefoxDriver(); 
	//	  break;
	//	  
	//	  default: driver = null; 
	//	  break; 
	//	  }


	//driver.manage().timeouts().implicitlyWait(10, null);
	//driver.manage().timeouts().getImplicitWaitTimeout().ofSeconds(10);

	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//driver.manage().timeouts().implicitlyWait(20, null);


	//}


	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
		logger.info("Driver is closed");
	}


	public void captureScreenShot(WebDriver driver , String testName) throws IOException {

		TakesScreenshot screenshot = ((TakesScreenshot)driver);

		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png");

		FileUtils.copyFile(src, dest);
	}

}
