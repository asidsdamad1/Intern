import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

public class CSVConsume {
    public static double withBigDecimal(double value, int places) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/example";
        String username = "root";
        String password = "asidsdamad1";

        String filePath = "E:\\java\\core\\5mSalesRecords1.csv";

        int batchSize = 500000;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);

//            String sqlQuery = "LOAD DATA LOCAL INFILE '" + filePath +
//                    "' INTO TABLE sales " +
//                    " FIELDS TERMINATED BY \',\' ENCLOSED BY \'\"'" +
//                    " LINES TERMINATED BY \'\\n\'" +
//                    " IGNORE 1 LINES (region, country, item_type, sale_channel, order_priority, order_date, order_id, ship_date" +
//                    " , units_sold, unit_price, unit_cost, total_revenue, total_cost, total_profit)";
//

            String sql = "insert into sales" +
                    "(region, country, item_type, sale_channel, order_priority, order_date, order_id, ship_date" +
                    ", units_sold, unit_price, unit_cost, total_revenue, total_cost, total_profit)" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate(sqlQuery);
            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            int count = 0;

            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");

                String region = data[0];
                String country = data[1];
                String itemType = data[2];
                String saleChannel = data[3];
                String orderPri = data[4];
                String orderDate = data[5];
                String orderId = data[6];
                String shipDate = data[7];
                String unitsSold = data[8];
                String unitPrice = data[9];
                String unitCost = data[10];
                String totalRevenue = data[11];
                String totalCost = data[12];
                String totalProfit = data[13];

                String[] str = orderDate.split("/");
                String orderDateFormat = str[2] + "-" + str[0] + "-" + str[1];

                String[] str1 = shipDate.split("/");
                String shipDateFormat = str1[2] + "-" + str1[0] + "-" + str1[1];
                statement.setString(1, region);
                statement.setString(2, country);
                statement.setString(3, itemType);
                statement.setString(4, saleChannel);
                statement.setString(5, orderPri);
                statement.setDate(6, Date.valueOf(orderDateFormat));
                statement.setInt(7, Integer.parseInt(orderId));
                statement.setDate(8, Date.valueOf(shipDateFormat));
                statement.setInt(9, Integer.parseInt(unitsSold));
                statement.setDouble(10, withBigDecimal(Double.parseDouble(unitPrice), 2));
                statement.setDouble(11, withBigDecimal(Double.parseDouble(unitCost), 2));
                statement.setDouble(12, withBigDecimal(Double.parseDouble(totalRevenue), 2));
                statement.setDouble(13, withBigDecimal(Double.parseDouble(totalCost), 2));
                statement.setDouble(14, withBigDecimal(Double.parseDouble(totalProfit), 2));
                statement.addBatch();
                if (count % batchSize == 0)
                    statement.executeBatch();
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Data has been inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
