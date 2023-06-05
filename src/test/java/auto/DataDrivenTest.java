package auto;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;

public class DataDrivenTest extends TestBase{

	String [] set1 = {"Car insurance with flexible payment options.", "54113", "General Info", "No",
			"Rent", "Please provide a valid email address.", "john1.doe@test.com", "Driver 1 (Named Insured) Information"};
	String [] set2 = {"Car insurance with flexible payment options.", "54112", "General Info", "No",
			"Rent", "Please provide a valid email address.", "john2.doe@test.com", "Driver 1 (Named Insured) Information"};
	String [] set3 = {"Car insurance with flexible payment options.", "54111", "General Info", "No",
			"Rent", "Please provide a valid email address.", "john3.doe@test.com", "Driver 1 (Named Insured) Information"};
	
	@DataProvider(name = "autoData")
	public String [][] getData(){
		String [][] data = new String[3][8];
		data[0] = set1;
		data[1] = set2;
		data[2] = set3;
		return data;
	}
	
	@Test(enabled = true, dataProvider = "autoData")
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
