package com.mystore.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener {
	
	ExtentSparkReporter htmlReporter;
	public static ExtentReports reports;
	ExtentTest test;
	
	
	public void configureReport() {
		
		ReadConfig readConfig = new ReadConfig();
		
		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportName = "MyStoreTestReport-" + timestamp + ".html";
		
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//"+reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		reports.setSystemInfo("OS", "Windows 10");
		reports.setSystemInfo("browser", readConfig.getBrowser());
		
		htmlReporter.config().setDocumentTitle("Extent Report Test");
		htmlReporter.config().setReportName("First Report Test");
		htmlReporter.config().setTheme(Theme.DARK);
		
		
	}

	public void onStart(ITestContext Result) {

		configureReport();
	}

	public void onFinish(ITestContext Result) {

		reports.flush();
	}

	public void onTestFailure(ITestResult Result) {

		test = reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed Test case is : "+Result.getName(), ExtentColor.RED));
		
		String screenShotPath = System.getProperty("user.dir")+"\\Screenshots\\"+ Result.getName()+".png";
		File screenShotFile = new File(screenShotPath);
		
		if (screenShotFile.exists()) {
			test.fail("Captured screenshot is below: " + test.addScreenCaptureFromPath(screenShotPath));
		}
		
		//test.addScreenCaptureFromBase64String(null);
	}

	public void onTestSkipped(ITestResult Result) {

		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the failed Test case is : "+Result.getName(), ExtentColor.YELLOW));
		
	}

	public void onTestStart(ITestResult Result) {

	}

	public void onTestSuccess(ITestResult Result) {

		test = reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the failed Test case is : "+Result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

	}

}
