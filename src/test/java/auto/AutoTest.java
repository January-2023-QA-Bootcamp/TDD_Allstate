package auto;

import org.testng.annotations.Test;
import base.TestBase;

public class AutoTest extends TestBase{

	@Test
	public void getA_Quote() {
		homePage.getAQuoteTitleValidation("Car insurance with flexible payment options.");
		homePage.insertZipCode("54114");
		homePage.clickGetQuoteBtn();
		infoPage.validateTitle("General Info");
		infoPage.select_last_5_yrs_liability("No");
		infoPage.selectOwnsHomeFlag("Rent");
		infoPage.clickContinue();
		infoPage.validateError("Please provide a valid email address.");
		infoPage.insertEmail("test@test.com");
		infoPage.clickContinue();
		driverDetails.validateTitle("Driver 1 (Named Insured) Information");
		driverDetails.insertFirstName("Jean");
		driverDetails.insertMiddleName('H');
		driverDetails.insertLastName("Doe");
		driverDetails.insertDOB("01/02/2000");
		driverDetails.selectGender("Female");
		driverDetails.selectMaritalStatus("Single");
		driverDetails.selectSR22('N');
		driverDetails.selectAnyAccident(false);
		driverDetails.selectAdditionalDriver('N');
		driverDetails.clickContinueBtn();
	}
}
