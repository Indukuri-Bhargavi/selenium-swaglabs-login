package helpers;

import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class ExcelWriter {

    public static void writeResult(String filePath, String sheetName, int rowIndex, int colIndex, String value) {
        if (value == null || value.trim().isEmpty()) return; // avoid writing blank result

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return;

            Row row = sheet.getRow(rowIndex);
            if (row == null) return;  // Prevent writing to non-existent rows

            Cell cell = row.getCell(colIndex);
            if (cell == null) {
                cell = row.createCell(colIndex);
            }

            cell.setCellValue(value);

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
