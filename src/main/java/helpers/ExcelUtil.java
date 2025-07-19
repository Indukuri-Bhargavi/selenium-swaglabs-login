package helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtil {
	public static Object[][] readUserData(String filePath, String sheetName) {
	    try (FileInputStream fis = new FileInputStream(filePath);
	         Workbook workbook = new XSSFWorkbook(fis)) {

	        Sheet sheet = workbook.getSheet(sheetName);
	        int rows = sheet.getLastRowNum();
	        int cols = sheet.getRow(0).getLastCellNum();

	        Object[][] data = new Object[rows][3]; // 3 = name, job, rowIndex

	        for (int i = 1; i <= rows; i++) {
	            Row row = sheet.getRow(i);
	            data[i - 1][0] = row.getCell(0).toString(); // name
	            data[i - 1][1] = row.getCell(1).toString(); // job
	            data[i - 1][2] = i;                         // actual Excel row index
	        }
	        return data;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return new Object[0][];
	    }
	}

    public static Object[][] readNegativeLoginData(String filePath, String sheetName) {
        Object[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();
            data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                }
            }
            workbook.close();
        } catch (Exception e) {
            System.out.println("❌ Excel read error: " + e.getMessage());
        }
        return data;
    }

}
