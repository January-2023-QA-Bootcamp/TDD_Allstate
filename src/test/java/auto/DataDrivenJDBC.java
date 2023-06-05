package auto;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import util.data.JDBCUtil;

public class DataDrivenJDBC extends TestBase{

	@DataProvider(name = "autoData")
	public Object[][] getData(){
		Object [][] data = JDBCUtil.getData();
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
