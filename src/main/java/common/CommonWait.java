package common;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static util.Key.*;
import util.Configuration;

public class CommonWait {
	
	static WebDriverWait wait;
	static Configuration config = new Configuration();
	
	public static void init(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(config.readPropNum(explicitWait.name())));
	}
	
	public static void waitUntilVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitUntilClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
