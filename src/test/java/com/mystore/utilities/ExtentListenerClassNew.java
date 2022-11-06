package com.mystore.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.net.ssl.X509ExtendedTrustManager;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mystore.testcases.BaseClass;

public class ExtentListenerClassNew implements ITestListener {

	static Date d = new Date();
	ExtentSparkReporter htmlReporter;
	public static ExtentReports reports;
	static String reportName = "Extent_"+d.toString().replace(":", "_").replace(" ", "_")+".html";
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void configureReport() {

		ReadConfig readConfig = new ReadConfig();

		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		//String reportName = "MyStoreTestReport-" + timestamp + ".html";

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

	public void onTestStart(ITestResult result) {
		ExtentTest test=reports.createTest(result.getTestClass().getName()+" TestCase : "+result.getMethod().getMethodName()); 
		testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>"+"Test Case : "+methodName.toUpperCase()+" PASSED"+"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
	}

	public void onTestFailure(ITestResult result) {
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>"+"<summart>"+"<b>"+"<font color="+"red>"+"Exception Occured: Click to see"
				+"</font>"+"</b>"+"</summary>"+exceptionMessage.replaceAll(",", "<br>")+"</details>"+"\n");

		try {
			//TestUtil.captureScreenShot(WebDriver driver, exceptionMessage)
			testReport.get().fail("<b>"+"<font color="+"red>"+"Screenshot of Failure"+"</font>"+"<b>");
			MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotName).build();


		}catch(Exception e){

			
		}

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText="<b>"+"Test Case : "+methodName+"Skipped"+"<b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}



}
