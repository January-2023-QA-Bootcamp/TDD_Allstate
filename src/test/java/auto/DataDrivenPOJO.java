package auto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.TestBase;
import util.data.AutoData;

public class DataDrivenPOJO extends TestBase{

	AutoData set1 = new AutoData("Car insurance with flexible payment options.", "54113", "General Info", "No",
			"Rent", "Please provide a valid email address.", "john4.doe@test.com", "Driver 1 (Named Insured) Information");
	AutoData set2 = new AutoData("Car insurance with flexible payment options.", "54112", "General Info", "No",
			"Rent", "Please provide a valid email address.", "john5.doe@test.com", "Driver 1 (Named Insured) Information");
	AutoData set3 = new AutoData("Car insurance with flexible payment options.", "54111", "General Info", "No",
			"Rent", "Please provide a valid email address.", "john6.doe@test.com", "Driver 1 (Named Insured) Information");
	
	@DataProvider(name = "autoData")
	public Iterator<AutoData> getDataIterator(){
		List<AutoData> list = new ArrayList<>();
		list.add(set1);
		list.add(set2);
		list.add(set3);
		return list.iterator();
	}
	
	@DataProvider(name = "autoDataArray")
	public Object[][] getDataArray(){
		Object [][] objects = new Object[3][1];
		objects[0] = new Object[] {set1};
		objects[1] = new Object[] {set2};
		objects[2] = new Object[] {set3};
		return objects;
	}
	
	@Test(enabled = true, dataProvider = "autoData")
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
