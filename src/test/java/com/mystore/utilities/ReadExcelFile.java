package com.mystore.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public static FileInputStream inputStream;
	public static HSSFWorkbook workBook;
	public static HSSFSheet excelSheet;
	public static HSSFRow row;
	public static HSSFCell cell;


	public static String getCellValue(String fileName , String sheetName , int rowNo , int cellNo) throws IOException {

		inputStream = new FileInputStream(fileName);
		workBook = new HSSFWorkbook(inputStream);
		//excelSheet = workBook.getSheet(sheetName);
		excelSheet = workBook.getSheetAt(0);
		cell = workBook.getSheet(sheetName).getRow(rowNo).getCell(cellNo);

		workBook.close();

		return cell.getStringCellValue();


	}

	public static int getRowCount(String fileName , String sheetName) throws IOException {

		inputStream = new FileInputStream(fileName);
		workBook = new HSSFWorkbook(inputStream);
		excelSheet = workBook.getSheet(sheetName);

		//get total no of rows
		int ttlRows = excelSheet.getLastRowNum()+1;

		workBook.close();

		return ttlRows;
	}

	public static int getColCount(String fileName , String sheetName) throws IOException {

		inputStream = new FileInputStream(fileName);
		workBook = new HSSFWorkbook(inputStream);
		excelSheet = workBook.getSheet(sheetName);

		//get total no of rows
		int ttlCells = excelSheet.getRow(0).getLastCellNum();

		workBook.close();

		return ttlCells;
	}
}
