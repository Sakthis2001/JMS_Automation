
package utils;




import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static Object[][] ReadExcelData(String filePath, int sheetNumber) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(sheetNumber);

        int rowCount = sheet.getLastRowNum();
        int columnCount =sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    data[i][j] = "";  // Assign empty string for null cell
                } else {
                    try {
                        // Handle different cell types
                        switch (cell.getCellType()) {
                            case BLANK:
                                data[i][j] = "";
                                break;
                            case STRING:
                                data[i][j] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                data[i][j] = NumberToTextConverter.toText(cell.getNumericCellValue());
                                //data[i][j] = String.valueOf(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                //data[i][j] = String.valueOf(cell.getBooleanCellValue());
                                data[i][j] = cell.getBooleanCellValue();
                                break;
                            case FORMULA:
                                data[i][j] = cell.getCellFormula();
                                break;
                            default:
                                data[i][j] = "";
                        }
                    } catch (Exception e) {
                        data[i][j] = "";  // Handle any exception by assigning empty string
                    }
                }
            }

        }
        workbook.close();
        fileInputStream.close();
        return data;
    }



}
