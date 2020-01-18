package com.healthpath.selenium.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {

	public static ArrayList<String> getValueFromExcel() throws IOException {
		String excelFilePath = BaseTest.getPath() + "//src//test//resources//testdata//HoboConnectTestData.xlsx";
		ArrayList<String> al = new ArrayList<String>();
		int totalColumns;
		Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int totalRows = sheet.getLastRowNum();
		for (int i = 1; i <= totalRows; i++) {
			row = sheet.getRow(i);
			totalColumns = row.getLastCellNum();

			for (int y = 0; y < totalColumns; y++) {
				al.add(row.getCell(y).getStringCellValue());
			}
		}
		return al;
	}
	
	public static int getNumberOfRows() throws IOException {
		String excelFilePath = BaseTest.getPath() + "//src//test//resources//testdata//HoboConnectTestData.xlsx";
	//	Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		@SuppressWarnings("resource")
		XSSFWorkbook wbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wbook.getSheet("Sheet1");
		int totalRows = sheet.getLastRowNum();
		return totalRows;
	}
	
	
	public static String getDeviceFromExcel(int rownum) throws IOException {
		String excelFilePath = BaseTest.getPath() + "//src//test//resources//testdata//HoboConnectTestData.xlsx";
		Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		row= sheet.getRow(rownum);
		
		String devicename= row.getCell(0).getStringCellValue();		
		return devicename;
	}
	
	public static String getDevicePasswordExcel(int rownum) throws IOException {
		String excelFilePath = BaseTest.getPath() + "//src//test//resources//testdata//HoboConnectTestData.xlsx";
		Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		row= sheet.getRow(rownum);
		String devicepassword= row.getCell(1).getStringCellValue();		
		return devicepassword;
	}
	
	public static String getLoggerNameFromExcel(int rownum) throws IOException {
		String excelFilePath = BaseTest.getPath() + "//src//test//resources//testdata//HoboConnectTestData.xlsx";
		Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		row= sheet.getRow(rownum);
		
		String loggername= row.getCell(2).getStringCellValue();
			
		return loggername;
	}
	
	public static String getLoggerGroupNameFromExcel(int rownum) throws IOException {
		String excelFilePath = BaseTest.getPath() + "//src//test//resources//testdata//HoboConnectTestData.xlsx";
		Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		row= sheet.getRow(rownum);
		
		String loggergroupname= row.getCell(3).getStringCellValue();
			
		return loggergroupname;
	}
	
	public static String getLoggerIntervalFromExcel(int rownum) throws IOException {
		String excelFilePath = BaseTest.getPath() + "//src//test//resources//testdata//HoboConnectTestData.xlsx";
		Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		row= sheet.getRow(rownum);
		//Cell cell =  row.getCell(4);
		//String loggerinterval= String.valueOf(cell.getNumericCellValue());
		String loggerinterval= row.getCell(4).getStringCellValue();
		System.out.println(loggerinterval);
		
		return loggerinterval;
	}
	
	public static String getUsernameFromExcel(int rownum) throws IOException {
		String excelFilePath = BaseTest.getPath() + "//testdata//testdata1.xlsx";
		Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		row= sheet.getRow(rownum);
		
		String username= row.getCell(1).getStringCellValue();
			
		return username;
	}
	
	public static String getPasswordFromExcel1(int rownum) throws IOException {
		String excelFilePath = BaseTest.getPath() + "//testdata//testdata1.xlsx";
		Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		row= sheet.getRow(rownum);
		
		String password= row.getCell(2).getStringCellValue();
			
		return password;
	}
	
	/*public static String getUserNameFromExcel(int rownum) throws IOException {
		String excelFilePath = BaseTest.getPath() + "//src//test//resources//testdata//HealthPath.xlsx";
		Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Login");
		row= sheet.getRow(rownum);
		
		String Username= row.getCell(0).getStringCellValue();
			
		return Username;
	}*/
	
	public static String getValueofColumnFromExcel(String sheetname, String columnname, int rownum) throws Exception
	{
	String excelFilePath = BaseTest.getPath() + "//src//test//resources//testdata//HealthPath.xlsx";
	FileInputStream fis = new FileInputStream(excelFilePath);
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheet(sheetname);
	XSSFRow row = sheet.getRow(0);

	int col_num = -1;

	for(int i=0; i < row.getLastCellNum(); i++)
	{
	if(row.getCell(i).getStringCellValue().trim().equals(columnname))
	col_num = i;
	}

	row = sheet.getRow(1);
	XSSFCell cell = row.getCell(col_num);

	String value = cell.getStringCellValue();
	System.out.println("Value of the Excel Cell is - "+ value);

	return value;
	}
	
	public static String getPasswordFromExcel(int rownum) throws IOException {
		String excelFilePath = BaseTest.getPath() + "D:\\TempConnect\\src\\test\\resources\\testdata\\HealthPath.xlsx";
		Row row;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Login");
		row= sheet.getRow(rownum);
		
		String Username= row.getCell(1).getStringCellValue();
			
		return Username;
	}
	
	
	public static void deleteFile() {
		try {

			File file = new File(BaseTest.getPath() + "//src//test//resources//testdata//Result.xlsx");

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("File not exist.");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public static void createExcelFile() {
		try {
			String filename = BaseTest.getPath() + "//src//test//resources//testdata//Result.xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Result");
			
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			System.out.println("file created");
		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	public static void setValueInExcel(String moduleName, String status) throws IOException {
		try {
			File fileName = new File(BaseTest.getPath() + "//src//test//resources//testdata//Result.xlsx");
			
			FileInputStream ExcelFile = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(ExcelFile);
            XSSFSheet sheet = workbook.getSheet("Result");
            XSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("Module Name");
            row.createCell(1).setCellValue("Status");
            int lastRow= sheet.getLastRowNum();
            XSSFRow rowhead = sheet.createRow(lastRow+1);
            rowhead.createCell(0).setCellValue(moduleName);
            rowhead.createCell(1).setCellValue(status);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
	} catch (Exception e) {

		e.printStackTrace();

	}

	}

}
