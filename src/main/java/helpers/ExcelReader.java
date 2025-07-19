package helpers;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

    public static List<Map<String, String>> getData(String path, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) return dataList;

            int totalCols = headerRow.getLastCellNum();
            int totalRows = sheet.getLastRowNum();

            for (int i = 1; i <= totalRows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> map = new HashMap<>();

                for (int j = 0; j < totalCols; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell valueCell = row.getCell(j);

                    String header = headerCell != null ? headerCell.getStringCellValue().trim() : "Column" + j;
                    String value = valueCell != null ? valueCell.toString().trim() : "";

                    map.put(header, value);
                }

                // Skip rows where all fields are empty
                if (map.values().stream().allMatch(String::isEmpty)) continue;

                dataList.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
