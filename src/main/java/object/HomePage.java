package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.testng.Assert.*;
import static org.openqa.selenium.support.PageFactory.*;
import static common.CommonAction.*;

public class HomePage {

	public HomePage(WebDriver driver) {
		initElements(driver, this);
	}
	
	@FindBy(tagName = "h1")
	WebElement getAQuoteTitle;
	
	@FindBy(xpath = "(//button[text()='Get a Free Quote'])[1]")
	WebElement getQuoteBtn;
	
	@FindBy(id = "free-quote-zip")
	WebElement zipCodeFld;
	
	public void getAQuoteTitleValidation(String expected) {
		assertEquals(getInnerHTML(getAQuoteTitle), expected);
	}
	
	public void clickGetQuoteBtn() {
		click(getQuoteBtn);
	}
	
	public void insertZipCode(String zip) {
		insert(zipCodeFld, zip);
	}
}
