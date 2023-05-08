package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	public static ExtentReports report;
	
	public static ExtentReports createReport() {
		if(report == null) report = new ExtentReports();
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");
		sparkReporter.config().setReportName("Automation Regression - The General");
		sparkReporter.config().setDocumentTitle("Regression Tests");
		sparkReporter.config().setTheme(Theme.STANDARD);
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Tester", System.getProperty("user.name"));
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("OS Version", System.getProperty("os.version"));
		report.setSystemInfo("OS Arch", System.getProperty("os.arch"));
		report.setSystemInfo("Team", "Avengers");
		return report;
	}
}
