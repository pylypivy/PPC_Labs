package week12exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

public class Main {
    
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mariadb://localhost:3306/labwork";
        String user = "pylypiv";
        String password = "149658";

        ArrayList<SalesPerson> salesmanList = new ArrayList<>();

        String querySalesman= "select * from SalesmanView;";

        try{
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to MariaDB established successfully!");
        	PreparedStatement stmtSales = conn.prepareStatement(querySalesman);

            ResultSet rsSales = stmtSales.executeQuery();
            //In order to create a SalesPerson object, I used the Function interface like Professor showed us in the class.
            Function<ResultSet, SalesPerson> rsSalesPerson = resultSet -> {
                try{
                    return new SalesPerson(resultSet.getString("Name"),
                    resultSet.getString("City"), 
                    resultSet.getDouble("Commission"), 
                    resultSet.getDouble("TotalAmount"));
                }catch(SQLException e){
                    throw new RuntimeException("Error mapping ResultSet to SalesPerson", e);
                }
            };

            while(rsSales.next()){
                salesmanList.add(rsSalesPerson.apply(rsSales));
            }
            
            Function<SalesPerson,Double> totalCommission = p -> p.getTotalSales() * p.getCommission();
            // For total Earnings I followed your logic in order to exercise as mine didn't need much work
            Function<SalesPerson,Double> totalEarnings = p -> p.getTotalSales() - totalCommission.apply(p);
            // I used printf to format the output so it looks more like a table
            System.out.println("\n-----------------------------------------------");
            System.out.printf("%-20s | %-15s", "Name", "Total earnings");
            System.out.println("\n-----------------------------------------------");

            salesmanList.stream().forEach( p -> System.out.printf("%-20s | %-14.2f\n", p.getName(), totalEarnings.apply(p)));
            System.out.println("\n-----------------------------------------------");
            
            System.out.printf("%-20s | %-15s", "Name", "Total commission");
            System.out.println("\n-----------------------------------------------");

            salesmanList.stream().forEach( p -> System.out.printf("%-20s | %-14.2f\n", p.getName(), totalCommission.apply(p)));
        }catch(SQLException e){
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }
