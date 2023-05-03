package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import static common.CommonWait.*;
import static common.CommonAction.*;

public class InfoPage {

	public InfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		init(driver);
	}
	
	@FindBy(xpath = "//div[@class='page-header']/child::h3")
	WebElement title;
	
	@FindBy(id = "piLapseDays")
	WebElement last_5_yrs_liability;
	
	@FindBy(id = "ownsHomeFlag")
	WebElement ownsHomeFlag;
	
	@FindBy(id = "emailAddress")
	WebElement emailAddressFld;
	
	@FindBy(id = "btn-continue")
	WebElement continueBtn;
	
	@FindBy(css = ".alert.alert-danger")
	WebElement alertError;
	
	public void validateTitle(String expected) {
		Assert.assertEquals(getText(title), expected);
	}
	
	public void select_last_5_yrs_liability(String value) {
		dropdown(last_5_yrs_liability, value);
	}
	
	public void selectOwnsHomeFlag(String ownsHomeValue) {
		dropdown(ownsHomeFlag, ownsHomeValue);
	}
	
	public void insertEmail(String email) {
		insert(emailAddressFld, email);
	}
	
	public void validateError(String expected) {
		waitUntilVisible(alertError);
		Assert.assertEquals(getText(alertError), expected);
	}
	
	public void clickContinue() {
		click(continueBtn);
	}
}
