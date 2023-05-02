package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import static common.CommonAction.*;

public class DriverDetails {

	public DriverDetails(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='page-header']/child::h3")
	WebElement title;
	
	public void validateTitle(String expected) {
		Assert.assertEquals(getText(title), expected);
	}
}
