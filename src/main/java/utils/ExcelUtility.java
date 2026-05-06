package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.ConfigReader;

public class ExcelUtility {

    
    private Workbook excelBook;
    private Sheet testSheet;

    
    private FileInputStream inputStream;
    private FileOutputStream outputStream;

    private DataFormatter cellFormatter;
    private String[][] excelData;

    // File location
    private String excelFilePath;

 
    
    public ExcelUtility(String sheetName) throws IOException {

        excelFilePath = ConfigReader.getExcelFilePath("read");
        inputStream = new FileInputStream(excelFilePath);

        excelBook = new XSSFWorkbook(inputStream);
        testSheet = excelBook.getSheet(sheetName);
    }

    
    public ExcelUtility() throws IOException {

        excelFilePath = ConfigReader.getExcelFilePath("write");
        File excelFile = new File(excelFilePath);

        if (excelFile.exists()) {

            inputStream = new FileInputStream(excelFile);
            excelBook = new XSSFWorkbook(inputStream);

            int existingSheetIndex = excelBook.getSheetIndex("TestData");
            if (existingSheetIndex != -1) {
                excelBook.removeSheetAt(existingSheetIndex);
            }

        } else {
            excelBook = new XSSFWorkbook();
        }

        testSheet = excelBook.createSheet("TestData");
        createHeader();
    }


    public void createHeader() {

        Row headerRow = testSheet.createRow(0);

        headerRow.createCell(0).setCellValue("S_NO");
        headerRow.createCell(1).setCellValue("TESTCASE_NAME");
        headerRow.createCell(2).setCellValue("STATUS");
    }


    public int getRowCount() {
        return testSheet.getPhysicalNumberOfRows();
    }

    public int getColCount() {
        return testSheet.getRow(0).getPhysicalNumberOfCells();
    }

    public void getCellData() {

        cellFormatter = new DataFormatter();
        excelData = new String[getRowCount() - 1][getColCount()];

        for (int rowIndex = 1; rowIndex < getRowCount(); rowIndex++) {

            Row currentRow = testSheet.getRow(rowIndex);

            for (int colIndex = 0; colIndex < getColCount(); colIndex++) {

                Cell currentCell = currentRow.getCell(colIndex);
                excelData[rowIndex - 1][colIndex] =
                        cellFormatter.formatCellValue(currentCell);
            }
        }
    }

    public String[][] getData() throws IOException {
        return excelData;
    }


    public synchronized void write(String testName, String status) throws IOException {

        int nextRowIndex = testSheet.getLastRowNum() + 1;
        Row dataRow = testSheet.createRow(nextRowIndex);

        dataRow.createCell(0).setCellValue(nextRowIndex);
        dataRow.createCell(1).setCellValue(testName);

        Cell resultCell = dataRow.createCell(2);
        resultCell.setCellValue(status);

        applyStatusColor(resultCell, status);

        outputStream = new FileOutputStream(excelFilePath);
        excelBook.write(outputStream);
        outputStream.flush();
    }

    public void applyStatusColor(Cell cell, String status) {

        CellStyle style = excelBook.createCellStyle();
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

   
    public void closeExcelFile() throws IOException {

        if (outputStream != null) {
            outputStream.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (excelBook != null) {
            excelBook.close();
        }
    }
}