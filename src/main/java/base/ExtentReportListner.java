package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Instant;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.google.common.io.Files;
import report.ExtentReport;
import report.ExtentReportManager;
import report.Log;

public class ExtentReportListner {

	ExtentReports report;
	
	@BeforeSuite
	public void initiateReport() {
		report = ExtentReportManager.createReport();
	}
	
	@BeforeMethod
	public void createTest(Method method) {
		ExtentReport.createTest(method.getName());
		ExtentReport.getTest().assignCategory(method.getDeclaringClass().getName());
	}
	
	@AfterSuite
	public void closeReport() {
		report.flush();
	}
	
	String captureScreenShot(Method method, WebDriver driver) {
		/*
		 * Take screenshot instance 
		 * check folder exist create folder if not exist 
		 * name the target screen shot file 
		 * capture the screenshot file runtime 
		 * copy/save it in your local
		 */

		TakesScreenshot ss = (TakesScreenshot) driver;
		File folder = new File(System.getProperty("user.dir") + "/screenShot");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File targetFile = new File(System.getProperty("user.dir") + "/screenShot/error_"+ method.getName() + Instant.now() +".png");
		try {
			File srcFile = ss.getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, targetFile);
			Log.log("Captured Screenshot at : " + targetFile.getAbsolutePath());
		} catch (IOException | WebDriverException e) {
			Log.log("Error while capturing screenshot");
		}
		return targetFile.getAbsolutePath();
	}
}
