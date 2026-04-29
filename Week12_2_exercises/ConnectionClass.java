import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * ConnectionClass has methods to
 * connect to a database - labwork, and retrieve the salesMan data.
 * To make it easier I created the SalesPerson view that
 * has salesman's name and the total commission(totalAmount_sold * commission_rate).
 */
public class ConnectionClass {
    private String url = "jdbc:mariadb://localhost:3306/labwork";
    private String user = "pylypiv";
    private String password = "149658";

    // Created a method to fetch the data
    public ArrayList<SalesPerson> getSalesmanList() {
        ArrayList<SalesPerson> salesmanList = new ArrayList<>();
        String querySalesman = "select * from SalesPerson;";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement stmtSales = conn.prepareStatement(querySalesman);
            ResultSet rsSales = stmtSales.executeQuery();

            Function<ResultSet, SalesPerson> rsSalesPerson = resultSet -> {
                try {
                    return new SalesPerson(resultSet.getString("Name"),
                            resultSet.getDouble("TotalCommission"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            };

            while (rsSales.next()) {
                salesmanList.add(rsSalesPerson.apply(rsSales));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesmanList;
    }
}
