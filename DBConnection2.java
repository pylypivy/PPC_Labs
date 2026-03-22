package dbconnection2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection2 {

	public static void main(String[] args) {
		String url = "jdbc:mariadb://localhost:3306/labwork";
        String user = "pylypiv";
        String password = "149658";
        /**
         * An ArrayList to keep the record of my sales.
         */
        ArrayList <Sales> salesList=new ArrayList<>(); 
        /**
         * The actual query to retrieve the data from the database.
         */
        String query = "select o.order_no as orderNumber, c.customer_name as customerName, "
        		+ "c.city as customerCity, s.name as salesmanName, o.purchase_amt as amount, "
        		+ "(o.purchase_amt * s.commission) as commissionAmount from orders o join customer c on o.customer_id=c.customer_id "
        		+ "join salesman s on c.salesman_id=s.salesman_id order by orderNumber;";
        try {
        	Connection conn = DriverManager.getConnection(url, user, password);
        	System.out.println("Connection to MariaDB established successfully!");
        	PreparedStatement stmt = conn.prepareStatement(query);
        	ResultSet rs = stmt.executeQuery();
        	/**
        	 * Loop to go through each entry and organize the data to create an Object.
        	 */
        	while (rs.next()) {
        		int orderNumber=rs.getInt("orderNumber");
        	    String customerName = rs.getString("customerName");
        	    String customerCity = rs.getString("customerCity");
        	    String salesmanName = rs.getString("salesmanName");
        	    double amount = rs.getDouble("amount");
        	    double commission = rs.getDouble("commissionAmount");
        	    /**
        	     * Creating the Sales object from each database entry.
        	     */
        	    salesList.add(new Sales(orderNumber,customerName, customerCity, salesmanName, amount, commission));
        	}

        	for (Sales s : salesList) {
        	    System.out.println(s); 
        	}
        }catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

 }
}
