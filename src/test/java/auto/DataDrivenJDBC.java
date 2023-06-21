package auto;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import base.TestBase;
import util.data.AutoData;
import util.data.JDBCUtil;

public class DataDrivenJDBC extends TestBase{

	@DataProvider(name = "autoData")
	public Object[][] getData(){
		Object [][] data = JDBCUtil.getData();
		return data;
	}
	
	@DataProvider(name = "autoDataObj")
	public Object[][] getDataObjects(){
		Object[][] data = JDBCUtil.getData();
		Object[][] objects = new Object[data.length][1];
		for(int i = 0; i < data.length; i++) {
			AutoData autoData = new AutoData(data[i][0].toString(),data[i][1].toString(),data[i][2].toString(),data[i][3].toString(),
					data[i][4].toString(),data[i][5].toString(),data[i][6].toString(),data[i][7].toString());
			objects[i] = new Object[] {autoData};	
		}
		return objects;
	}
	
	@Test(enabled = false, dataProvider = "autoData")
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
	
	@Test(enabled = true, dataProvider = "autoDataObj")
	public void getAutoQuote(AutoData autoData) {
		homePage.getAQuoteTitleValidation(autoData.getQuoteTitle());
		homePage.insertZipCode(autoData.getZipCode());
		homePage.clickGetQuoteBtn();
		infoPage.validateTitle(autoData.getInfoTitle());
		infoPage.select_last_5_yrs_liability(autoData.getLiab5Yrs());
		infoPage.selectOwnsHomeFlag(autoData.getOwnOrRent());
		infoPage.clickContinue();
		infoPage.validateError(autoData.getEmailErrorMsg());
		infoPage.insertEmail(autoData.getEmail1());
		infoPage.clickContinue();
		driverDetails.validateTitle(autoData.getDriverTtitle());
	}
}
