package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import object.DriverDetails;
import object.HomePage;
import object.InfoPage;
import util.Configuration;
import util.Key;
import static util.Key.*;
import static util.Browser.*;

public class TestBase {

	protected WebDriver driver;
	protected HomePage homePage;
	protected InfoPage infoPage;
	protected DriverDetails driverDetails;
	Configuration conf = new Configuration();
	
	@BeforeMethod
	public void beforeEachTest() {
		String browserName = conf.readProp(getValue(browser));
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
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case SAFARI:
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
	}
	
	public void initObject() {
		homePage = new HomePage(driver);
		infoPage = new InfoPage(driver);
		driverDetails = new DriverDetails(driver);
	}
	
	@AfterMethod
	public void closingBrowser() {
		driver.quit();
	}
	
	private String getValue(Key key) {
		return key.name();
	}
	
}