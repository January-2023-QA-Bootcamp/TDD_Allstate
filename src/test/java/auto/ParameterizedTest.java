package auto;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.TestBase;

public class ParameterizedTest extends TestBase{

	@Parameters({"quoteTitle1", "zipCode1", "infoTitle1", "liab5Yrs1", "ownOrRent1", 
		"emailErrorMsg1", "email1", "driverTtitle1"})
	@Test(enabled = true)
	public void getAutoQuote(String quoteTitle, String zipCode, String infoTitle, String liab5Yrs, 
			String ownOrRent, String emailErrorMsg, String email, String driverTtitle) {
		homePage.getAQuoteTitleValidation(quoteTitle);
		homePage.insertZipCode(zipCode);
		homePage.clickGetQuoteBtn();
		infoPage.validateTitle(infoTitle);
		infoPage.select_last_5_yrs_liability(liab5Yrs);
		infoPage.selectOwnsHomeFlag(ownOrRent);
		infoPage.clickContinue();
		infoPage.validateError(emailErrorMsg);
		infoPage.insertEmail(email);
		infoPage.clickContinue();
		driverDetails.validateTitle(driverTtitle);
	}
}
