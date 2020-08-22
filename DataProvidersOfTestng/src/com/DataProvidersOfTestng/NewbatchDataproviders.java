package com.DataProvidersOfTestng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewbatchDataproviders {

	@Test(dataProvider = "Testdata")
	public void testData(double id,String username, double sal) {
		System.out.println(id+"\t"+username + "\t" +sal + "\t");
	}

	@DataProvider(name = "Testdata")
	public static Object[][] testdata() throws IOException {
		FileInputStream fis = new FileInputStream("D:\\empdata.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet("Sheet1");
		int lastrow = sheet.getLastRowNum();
		int lastcolumn = sheet.getRow(0).getLastCellNum();
		
		Object[][] values = new Object[lastrow][lastcolumn];
		
		for (int r = 1; r < lastrow; r++) {
			Row row = sheet.getRow(r);
			for (int c = 1; c < lastcolumn; c++) {
				Cell cell = row.getCell(c);
				switch (cell.getCellType()) {
				case NUMERIC:
					values[r - 1][c-1] = cell.getNumericCellValue();
					break;
				case STRING:
					values[r - 1][c-1] = cell.getStringCellValue();
					break;
					

				default:
					System.out.println("Invalid  value found in cell");
				}

				//values[r-1][c-1]=cell.getStringCellValue();
				
			}
			

		}
		return values;
	}

}
