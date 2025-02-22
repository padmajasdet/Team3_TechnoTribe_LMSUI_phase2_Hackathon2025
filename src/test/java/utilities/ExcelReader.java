package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static List<Map<String, String>> getExcelData(String filePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            int columnCount = headerRow.getLastCellNum();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1 (skip headers)
                Row row = sheet.getRow(i);
                Map<String, String> dataMap = new HashMap<>();

                for (int j = 0; j < columnCount; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell valueCell = row.getCell(j);
                    String header = headerCell.getStringCellValue();
                    String value = (valueCell == null) ? "" : valueCell.toString();
                    dataMap.put(header, value);
                    
                }
                dataList.add(dataMap);
               
                
            }
            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        return dataList;
    }

    public static Map<String, String> getTestData(String filePath, String sheetName, String testCase) {
        List<Map<String, String>> dataList = getExcelData(filePath, sheetName);
        for (Map<String, String> data : dataList) {
            if (data.get("testcase").equalsIgnoreCase(testCase)) {
                return data;
            }
        }
        return null; // If test case not found
    }

    public static void updateTestData(String filePath, String sheetName, String testCase, String columnName, String newValue) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            // Find the column index
            Row headerRow = sheet.getRow(0);
            int columnIndex = -1;
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    columnIndex = cell.getColumnIndex();
                    break;
                }
            }

            // Find the test case row
            int rowIndex = -1;
            for (Row row : sheet) {
                Cell firstCell = row.getCell(0);
                if (firstCell != null && firstCell.getStringCellValue().equalsIgnoreCase(testCase)) {
                    rowIndex = row.getRowNum();
                    break;
                }
            }

            // Update the value in the Excel file
            if (rowIndex != -1 && columnIndex != -1) {
                Row row = sheet.getRow(rowIndex);
                Cell cell = row.getCell(columnIndex);
                if (cell == null) {
                    cell = row.createCell(columnIndex);
                }
                cell.setCellValue(newValue);
            }

            // Write changes back to the file
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            workbook.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}