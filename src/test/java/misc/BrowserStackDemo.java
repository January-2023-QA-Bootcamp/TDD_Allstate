package misc;

import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackDemo {

	public static final String USERNAME = "mdnasiribnehussa_BvaIyP";
    public static final String AUTOMATE_KEY = "dLrG7HyYBoEzS9P9FWJx";
    public static final String URL_STRING = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	@Test
	public void runBrowserstack() throws MalformedURLException {
		MutableCapabilities capabilities = new MutableCapabilities();
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("browserVersion", "latest");
		HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
		browserstackOptions.put("os", "Windows");
		browserstackOptions.put("osVersion", "11");
		capabilities.setCapability("bstack:options", browserstackOptions);

        WebDriver driver = new RemoteWebDriver(new URL(URL_STRING), capabilities);
        
        driver.get("http://www.google.com");
        driver.manage().window().maximize();
        
        driver.quit();
	}
}
