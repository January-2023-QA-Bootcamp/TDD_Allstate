package misc;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LinearScripting {

	WebDriver driver;
	
	@BeforeMethod
	public void beforeEachTest() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	@Test
	public void testAllStateClamOverview() {
		driver.get("https://www.allstate.com/claims");
		WebElement element = driver.findElement(By.id("1735ea1f-4b87-460e-bd3c-f5a361a515cc-header-desktop-nav-logo"));
		String titleString = element.getAttribute("title");
		System.out.println(titleString);
	}
	
	@Test
	public void testAllStateQuoteTitle() {
		driver.get("https://www.allstate.com/");
		WebElement element = driver.findElement(By.xpath("//span[@class='mr-2']"));
		String titleString = element.getAttribute("innerHTML");
		System.out.println(titleString);
	}
	
	@AfterMethod
	public void closingBrowser() {
		driver.quit();
	}
}
