package util.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	Workbook workbook;
	Sheet sheet;

	public ExcelUtils(String fileName, String sheetName) {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			if (fileName.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(inputStream);
			} else if (fileName.endsWith(".xls")) {
				workbook = new HSSFWorkbook(inputStream);
			}
			sheet = workbook.getSheet(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, String>> getListOfMaps() {
		List<Map<String, String>> listOfMap = new ArrayList<>();

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			HashMap<String, String> map = new HashMap<>();
			Row row = sheet.getRow(i);
			try {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					String key = "";
					String value = "";
					try {
						key = sheet.getRow(0).getCell(j).getStringCellValue().trim();
						value = row.getCell(j).getStringCellValue().trim();
					} catch (NullPointerException e) {
						// e.printStackTrace();
					}
					map.put(key, value);
				}
				if(map.get("Execution").equalsIgnoreCase("Y")) {
					listOfMap.add(map);
				}
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
		}
		return listOfMap;
	}
}
