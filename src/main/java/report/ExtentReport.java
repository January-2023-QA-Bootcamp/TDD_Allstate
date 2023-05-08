package report;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;

public class ExtentReport {
	
	static ExtentTest extentTest;
	static HashMap<Long, ExtentTest> map = new HashMap<>();
	
	public static synchronized ExtentTest createTest(String testName) {
		extentTest = ExtentReportManager.report.createTest(testName);
		map.put(Thread.currentThread().getId(), extentTest);
		return extentTest;
	}
	
	public static synchronized ExtentTest getTest() {
		return map.get(Thread.currentThread().getId());
	}
}
