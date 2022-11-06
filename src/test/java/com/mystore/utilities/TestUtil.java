package com.mystore.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtil {

	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenShot(WebDriver driver , String testName) throws IOException {

		TakesScreenshot screenshot = ((TakesScreenshot)driver);

		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png");

		FileUtils.copyFile(src, dest);
	}
	
}
