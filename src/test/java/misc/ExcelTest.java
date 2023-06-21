package misc;

import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.data.ExcelUtils;

public class ExcelTest {

	@Test
	public void excelReading() throws IOException {
		String fileName = "AutoTestData.xlsx";
		String sheetName = "Sheet1";
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		
		Workbook workbook = null;
		if(fileName.endsWith(".xlsx")) {
			workbook = new XSSFWorkbook(inputStream); //xlsx
		}else if(fileName.endsWith(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(2);
		System.out.println(row.getLastCellNum());
		Cell cell = row.getCell(8);
		
		System.out.println(cell);
		System.out.println(sheet.getLastRowNum());
		
		
		workbook.close();
		inputStream.close();
	}
	
	@Test
	public void arrayTest() {
		Object[] arr = {"a","b", null, "c"};
		System.out.println(arr[1]);
	}
	
	@Test
	public void testListOfMap() throws IOException {
		List<?> list = getMaps(ExcelTest.class);
		System.out.println(list.size());
		for(Object map : list) {
			System.out.println(map);
		}
		//list.forEach(System.out::println);
	}
	
	@SuppressWarnings("rawtypes")
	public static List<?> getMaps(Class cls) throws IOException {
		String fileName = "AutoTestData.xlsx";
		String sheetName = "Sheet1";
		
		InputStream inputStream = cls.getClassLoader().getResourceAsStream(fileName);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		
		List<Map<String, String>> listOfMap = new ArrayList<>();
		
		for(int i = 1; i <= sheet.getLastRowNum(); i++) {
			HashMap<String, String> map = new HashMap<>();
			Row row = sheet.getRow(i);
			try {
			for(int j = 0; j < row.getLastCellNum(); j++) {
				String key = "";
				String value = "";
				try {
					key = sheet.getRow(0).getCell(j).getStringCellValue();
					value = row.getCell(j).getStringCellValue();
				} catch (NullPointerException e) {
					//e.printStackTrace();
				}
				map.put(key, value);
			}
			listOfMap.add(map);
			}catch (NullPointerException e) {
				// TODO: handle exception
			}
		}
		
		workbook.close();
		inputStream.close();
		return listOfMap;
	}
	
	@Test
	public void unitTestExcelUtils() {
		String fileName = "AutoTestData.xlsx";
		String sheetName = "Sheet1";
		ExcelUtils utils = new ExcelUtils(fileName, sheetName);
		List<Map<String, String>> list = utils.getListOfMaps();
		System.out.println(list.size());
		list.forEach(System.out::println);
	}
}
