
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelExporter {
    public static void main(String[] args) {
        ExcelExporter exporter = new ExcelExporter();
        exporter.export("sales");
    }
    


    public void export(String table) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/example";
        String username = "root";
        String password = "asidsdamad1";

        String  excelFilePath = "salesRecordExport.xlsx";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);

            String sql = "select * from ".concat(table);

            Statement stmt =  connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet =  workbook.createSheet(table);

            writeHeaderLine(result, sheet);

            writeDataLines(result, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();

            stmt.close();

            connection.commit();
            connection.close();
            System.out.println("Data has been exported successfully.");
        } catch (Exception exception) {
            System.out.println("Database error: ");
            exception.printStackTrace();
        }
    }

    private void writeHeaderLine(ResultSet result, XSSFSheet sheet)  throws SQLException  {
        //write  header  line  containing  column names
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns  = metaData.getColumnCount();

        Row headerRow = sheet.createRow(0);

        //exclude the  first  column which is the ID field
        for(int i = 2; i <=  numberOfColumns; i++) {
            String columnName = metaData.getColumnName(i);
            Cell headerCell = headerRow.createCell(i-2);
            headerCell.setCellValue(columnName);
        }
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet)
            throws SQLException {
        ResultSetMetaData metaData =  result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        int rowCount = 1;

        while(result.next()) {
            Row row = sheet.createRow(rowCount++);

            for(int i = 2; i <= numberOfColumns; i++) {
                Object valueObject = result.getObject(i);

                Cell cell = row.createCell(i-2);

                if(valueObject instanceof Boolean)
                    cell.setCellValue((Boolean) valueObject);
                else if(valueObject instanceof Double)
                    cell.setCellValue((double) valueObject);
                else if(valueObject instanceof Float)
                    cell.setCellValue((float) valueObject);
                else if(valueObject instanceof Integer)
                    cell.setCellValue((int) valueObject);
                else if(valueObject instanceof BigDecimal)
                    cell.setCellValue(((BigDecimal) valueObject).toEngineeringString());
                else if(valueObject instanceof Date) {
                    cell.setCellValue((Date) valueObject);
                    forrmatDateCell(workbook, cell);
                } else cell.setCellValue((String) valueObject);

            }
        }
    }

    private void forrmatDateCell(XSSFWorkbook workbook, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("MM/dd/yyyy"));
        cell.setCellStyle(cellStyle);
    }
}
