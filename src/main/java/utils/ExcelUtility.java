package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;

public class ExcelUtility {

    public static Object[][] getFilteredData(String path, String sheetName, String testCaseName) {

        List<Object[]> dataList = new ArrayList<>();
        try {
            System.out.println("Reading Excel from: " + path);
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("Excel file NOT FOUND at: " + path);
                return new Object[0][];
            }
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                String testCase = getCellValue(row.getCell(0));
                if (testCase.equalsIgnoreCase(testCaseName)) {
                    String name = getCellValue(row.getCell(1));
                    String email = getCellValue(row.getCell(2));
                    String policy = getCellValue(row.getCell(3));
                    String complaint = getCellValue(row.getCell(4));
                    String mobile = getCellValue(row.getCell(5));

                    dataList.add(new Object[]{name, email, policy, complaint, mobile});
                }
            }
            wb.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList.toArray(new Object[0][]);
    }
    private static String getCellValue(Cell cell) {
        DataFormatter formatter = new DataFormatter();
        return (cell == null) ? "" : formatter.formatCellValue(cell);
    }
}
