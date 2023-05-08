package base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import object.DriverDetails;
import object.HomePage;
import object.InfoPage;
import report.ExtentReport;
import util.Configuration;
import util.Key;
import static util.Key.*;
import static util.Browser.*;

public class TestBase extends ExtentReportListner{

	protected WebDriver driver;
	protected HomePage homePage;
	protected InfoPage infoPage;
	protected DriverDetails driverDetails;
	Configuration conf = new Configuration();

	@Parameters("browser")
	@BeforeMethod
	public void beforeEachTest(@Optional("firefox") String browserName) {
		// String browserName = conf.readProp(getValue(browser));
		initiatDriver(browserName);
		initObject();
		driver.manage().window().maximize();
		int pageLoadTime = conf.readPropNum(getValue(pageLoad));
		int implicitLoadTime = conf.readPropNum(getValue(implicitLoad));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTime));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitLoadTime));
		String urlName = conf.readProp(getValue(url));
		driver.get(urlName);
	}

	public void initiatDriver(String browser) {
		switch (browser) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case SAFARI:
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
	}

	protected void initObject() {
		homePage = new HomePage(driver);
		infoPage = new InfoPage(driver);
		driverDetails = new DriverDetails(driver);
	}

	private String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}
	
	@AfterMethod
	public void aftereEachTest(ITestResult result, Method method) {
		for(String group : result.getMethod().getGroups()) {
			ExtentReport.getTest().assignCategory(group);
		}
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			ExtentReport.getTest().log(Status.INFO, "Test Passed");
		}else if(result.getStatus() == ITestResult.SKIP) {
			ExtentReport.getTest().log(Status.SKIP, "Test Skipped");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			ExtentReport.getTest().log(Status.FAIL, "Test Failed");
			ExtentReport.getTest().log(Status.FAIL, getStackTrace(result.getThrowable()));
			ExtentReport.getTest().addScreenCaptureFromPath(captureScreenShot(method, driver));
		}
	}
	
	@AfterMethod
	public void closingBrowser() {
		driver.quit();
	}

	private String getValue(Key key) {
		return key.name();
	}

}
