//package utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import config.ConfigReader;
//
//public class ExcelUtility {
//
//    
//    private Workbook excelBook;
//    private Sheet testSheet;
//
//    
//    private FileInputStream inputStream;
//    private FileOutputStream outputStream;
//
//    private DataFormatter cellFormatter;
//    private String[][] excelData;
//
//    // File location
//    private String excelFilePath;
//
// 
//    
//    public ExcelUtility(String sheetName) throws IOException {
//
//        excelFilePath = ConfigReader.getExcelFilePath("read");
//        inputStream = new FileInputStream(excelFilePath);
//
//        excelBook = new XSSFWorkbook(inputStream);
//        testSheet = excelBook.getSheet(sheetName);
//    }
//
//    
//    public ExcelUtility() throws IOException {
//
//        excelFilePath = ConfigReader.getExcelFilePath("write");
//        File excelFile = new File(excelFilePath);
//
//        if (excelFile.exists()) {
//
//            inputStream = new FileInputStream(excelFile);
//            excelBook = new XSSFWorkbook(inputStream);
//
//            int existingSheetIndex = excelBook.getSheetIndex("TestData");
//            if (existingSheetIndex != -1) {
//                excelBook.removeSheetAt(existingSheetIndex);
//            }
//
//        } else {
//            excelBook = new XSSFWorkbook();
//        }
//
//        testSheet = excelBook.createSheet("TestData");
//        createHeader();
//    }
//
//
//    public void createHeader() {
//
//        Row headerRow = testSheet.createRow(0);
//
//        headerRow.createCell(0).setCellValue("S_NO");
//        headerRow.createCell(1).setCellValue("TESTCASE_NAME");
//        headerRow.createCell(2).setCellValue("STATUS");
//    }
//
//
//    public int getRowCount() {
//        return testSheet.getPhysicalNumberOfRows();
//    }
//
//    public int getColCount() {
//        return testSheet.getRow(0).getPhysicalNumberOfCells();
//    }
//
//    public void getCellData() {
//
//        cellFormatter = new DataFormatter();
//        excelData = new String[getRowCount() - 1][getColCount()];
//
//        for (int rowIndex = 1; rowIndex < getRowCount(); rowIndex++) {
//
//            Row currentRow = testSheet.getRow(rowIndex);
//
//            for (int colIndex = 0; colIndex < getColCount(); colIndex++) {
//
//                Cell currentCell = currentRow.getCell(colIndex);
//                excelData[rowIndex - 1][colIndex] =
//                        cellFormatter.formatCellValue(currentCell);
//            }
//        }
//    }
//
//    public String[][] getData() throws IOException {
//        return excelData;
//    }
//
//
//    public synchronized void write(String testName, String status) throws IOException {
//
//        int nextRowIndex = testSheet.getLastRowNum() + 1;
//        Row dataRow = testSheet.createRow(nextRowIndex);
//
//        dataRow.createCell(0).setCellValue(nextRowIndex);
//        dataRow.createCell(1).setCellValue(testName);
//
//        Cell resultCell = dataRow.createCell(2);
//        resultCell.setCellValue(status);
//
//        applyStatusColor(resultCell, status);
//
//        outputStream = new FileOutputStream(excelFilePath);
//        excelBook.write(outputStream);
//        outputStream.flush();
//    }
//
//    public void applyStatusColor(Cell cell, String status) {
//
//        CellStyle style = excelBook.createCellStyle();
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//        if (status.contains("PASS")) {
//            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//        } else if (status.contains("SKIP")) {
//            style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//        } else {
//            style.setFillForegroundColor(IndexedColors.RED.getIndex());
//        }
//
//        cell.setCellStyle(style);
//    }
//
//   
//    public void closeExcelFile() throws IOException {
//
//        if (outputStream != null) {
//            outputStream.close();
//        }
//        if (inputStream != null) {
//            inputStream.close();
//        }
//        if (excelBook != null) {
//            excelBook.close();
//        }
//    }
//}


package utils;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import config.ConfigReader;
 
public class ExcelUtility {
	private Workbook workbook;
	private Sheet sheet;
	private FileInputStream fis;
	private FileOutputStream fos;
	private DataFormatter formatter;
	private String[][] data;
	private String filePath;
	// in constructor creating objects fileinnputstream and
	// workbook variables
	// getting filepath using configReader methods and opening the workbook
 
	public ExcelUtility(String sheetName) throws IOException {
		this.filePath = ConfigReader.getExcelFilePath("read");
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
	}
 
	public ExcelUtility() throws IOException {
 
		filePath = ConfigReader.getExcelFilePath("write");
		File file = new File(filePath);
 
		if (file.exists()) {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
 
			// DELETE PREVIOUS SHEET IF EXISTS
			int sheetIndex = workbook.getSheetIndex("TestData");
			if (sheetIndex != -1) {
				workbook.removeSheetAt(sheetIndex);
			}
 
		} else {
			workbook = new XSSFWorkbook();
		}
 
		// CREATE FRESH SHEET EVERY TIME
		sheet = workbook.createSheet("TestData");
 
		// CREATE HEADER
		createHeader();
	}
 
	public void createHeader() {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("S_No.");
		row.createCell(1).setCellValue("TESTCASE_NAME");
		row.createCell(2).setCellValue("STATUS");
	}
	// return total rows in sheet
 
	public int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
 
	// return total column the passed row number
 
	public int getColCount() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}
 
	// reading the excel file and then sending the data in 2D string array
 
	public void getCellData() {
		formatter = new DataFormatter();
		data = new String[getRowCount() - 1][getColCount()];
		for (int i = 1; i < getRowCount(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < getColCount(); j++) {
				Cell cell = row.getCell(j);
				data[i - 1][j] = formatter.formatCellValue(cell);
//				System.out.print(data[i][j] + " ");
			}
		}
	}
 
	// getting data from excel after reading and then closing the files
 
	public String[][] getData() throws IOException {
		getCellData();
		closeExcelFile();
		return data;
	}
 
	public void write(String testName, String status) throws IOException {
 
		int currRowIndex = sheet.getLastRowNum() + 1;
		Row row = sheet.createRow(currRowIndex);
 
		row.createCell(0).setCellValue(currRowIndex);
		row.createCell(1).setCellValue(testName);
 
		Cell statusCell = row.createCell(2);
		statusCell.setCellValue(status);
 
		// Apply color
		applyStatusColor(statusCell, status);
 
		fos = new FileOutputStream(filePath);
		workbook.write(fos);
		fos.flush();
	}
 
	private void applyStatusColor(Cell cell, String status) {
 
		CellStyle style = workbook.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
 
		if (status.contains("PASS")) {
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		} else if (status.contains("SKIP")) {
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		} else {
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
		}
		cell.setCellStyle(style);
	}
 
	// function for closing the open files
	public void closeExcelFile() throws IOException {
		if (fos != null)
			fos.close();
		if (fis != null)
			fis.close();
		if (workbook != null)
			workbook.close();
	}
 
}
