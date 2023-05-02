package auto;

import org.testng.annotations.Test;
import base.TestBase;

public class AutoTest extends TestBase{

	@Test
	public void getA_Quote() throws InterruptedException {
		homePage.getAQuoteTitleValidation("Car insurance with flexible payment options.");
		homePage.insertZipCode("54304");
		homePage.clickGetQuoteBtn();
		infoPage.validateTitle("General Info");
		infoPage.select_last_5_yrs_liability("No");
		infoPage.selectOwnsHomeFlag("Rent");
		infoPage.clickContinue();
		infoPage.validateError("Please provide a valid email address.");
		infoPage.insertEmail("test@test.com");
		infoPage.clickContinue();
		Thread.sleep(2000);
		driverDetails.validateTitle("Driver 1 (Named Insured) Information");
	}
}
