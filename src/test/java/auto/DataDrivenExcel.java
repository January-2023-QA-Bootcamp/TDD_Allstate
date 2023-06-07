package auto;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.TestBase;
import static util.Key.*;
import util.data.ExcelUtils;

public class DataDrivenExcel extends TestBase{

	@DataProvider(name = "excelData")
	public Iterator<Map<String, String>> getData(){
		String fileName = conf.readProp(excelFile.name());
		String sheetName = conf.readProp(excelSheet.name());
		ExcelUtils excelUtils = new ExcelUtils(fileName, sheetName);
		List<Map<String, String>> listOfMaps = excelUtils.getListOfMaps();
		return listOfMaps.iterator();
	}
	
	@Test(enabled = true, dataProvider = "excelData")
	public void getAutoQuote(Map<String, String> map) {
		homePage.getAQuoteTitleValidation(map.get("Get A Quote Title"));
		homePage.insertZipCode(map.get("Zip Code"));
		homePage.clickGetQuoteBtn();
		infoPage.validateTitle(map.get("General Info Title"));
		infoPage.select_last_5_yrs_liability(map.get("Liability last 5 years"));
		infoPage.selectOwnsHomeFlag(map.get("Own or Rent Property"));
		infoPage.clickContinue();
		infoPage.validateError(map.get("Email Error message"));
		infoPage.insertEmail(map.get("Email"));
		infoPage.clickContinue();
		driverDetails.validateTitle(map.get("Driver Page Title"));
	}
}
