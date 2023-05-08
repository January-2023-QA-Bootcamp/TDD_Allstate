package auto;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;

public class FailedTest extends TestBase{

	@Test
	public void failedTest() {
		homePage.getAQuoteTitleValidation("Car insurance with flexible payment options.");
		homePage.insertZipCode("54114");
		homePage.clickGetQuoteBtn();
		infoPage.validateTitle("General Info");
		infoPage.select_last_5_yrs_liability("No");
		infoPage.selectOwnsHomeFlag("Rent");
		infoPage.clickContinue();
		infoPage.validateError("Please provide a valid email address.");
		infoPage.insertEmail("test@test.com");
		Assert.fail();
	}
}
