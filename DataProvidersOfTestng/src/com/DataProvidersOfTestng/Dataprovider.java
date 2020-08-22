package com.DataProvidersOfTestng;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprovider {
	//@Test(dataProvider = "Testdata",dataProviderClass =com.DataProvidersOfTestng.DataProviderforclass.class)
	@Test(dataProvider = "Testdata")
	public void m1(double id, String name, double sal,String address) {
		System.out.println(id + "\t" + name + "\t" + sal +"\t"+address);
	}
	
	@DataProvider(name = "Testdata")
	public static Object[][] m1() throws IOException {
		Object[][] values = null;
		FileInputStream fis = new FileInputStream("D:\\empdata.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();
		Row r = sheet.getRow(0);
		values = new Object[rows][r.getLastCellNum()];
		for (int i = 1; i <= rows; i++) {
			Row row = sheet.getRow(i);
			int cells = row.getLastCellNum();
			for (int j = 0; j < cells; j++) {
				Cell cell = row.getCell(j);
				switch (cell.getCellType()) {
				case NUMERIC:
					values[i - 1][j] = cell.getNumericCellValue();
					break;
				case STRING:
					values[i - 1][j] = cell.getStringCellValue();
					break;

				default:
					System.out.println("Invalid  value found in cell");
				}

			}
		}
		book.close();
		return values;
	}

}
