import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Main class that processes the data retrieved from the database.
 */
public class Week13_lab {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/employee";
        String user = "pylypiv";
        String password = "149658";

        List<Employee> employeesList=new ArrayList<Employee>();

        String queryEmployee="select * from employeesTable";

        try{
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to MariaDB established successfully!");
        	PreparedStatement stmtEmployee = conn.prepareStatement(queryEmployee);
        	ResultSet rsEmpl = stmtEmployee.executeQuery();

			Function<ResultSet, Employee> rsEmployeeSet = resultSet -> {
                try {
                     return new Employee(resultSet.getInt("ID"),
                     resultSet.getString("NAME"),
                     resultSet.getDouble("SALARY"));
                } catch (SQLException e) {
                     throw new RuntimeException("Error mapping ResultSet to Customer", e);
				}
			};

			while(rsEmpl.next()){
				employeesList.add(rsEmployeeSet.apply(rsEmpl));
			}
            // Print the list of all employees
            Consumer<Employee> printEmployee = e -> System.out.println(e);
            //employeesList.forEach(printEmployee);
            //Predicate to filter employees with salary greater than 50000
            Predicate<Employee> higherSalary = e -> e.getSalary() > 50000;
            List<Employee> highEarners = employeesList.stream().filter(higherSalary).collect(Collectors.toList());
            //Print the list of highEarners
            highEarners.forEach(printEmployee);
            //Applying 15% tax reduction to highEarners
            Function<Employee,Employee> highTaxReduction = e -> new Employee(e.getId(), e.getName(), e.getSalary() * 0.85);
            //Applying 10% tax reduction to lowEarners
            Function<Employee,Employee> lowTaxReduction = e -> new Employee(e.getId(), e.getName(), e.getSalary() * 0.90);
            //Formating the salary to 2 decimal palces
            Function<Employee,String> formatSalary = e -> String.format("$%.2f", e.getSalary());
            //Print the list of highEarners with tax reduction and formatted salary
            List<Employee> highEarners2 = employeesList.stream().filter(higherSalary).map(highTaxReduction).collect(Collectors.toList());
            highEarners2.stream().map(formatSalary).forEach(System.out::println);
            //Partitioning employees based on salary greater/equal/less than 50000 and applying tax reduction accordingly
            Map<Boolean, List<Employee>> partitionedEmployees = employeesList.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 50000));
            List<Employee> formattedSalaries = Stream.concat(partitionedEmployees.get(true).stream().map(highTaxReduction), 
                                                            partitionedEmployees.get(false).stream().map(lowTaxReduction))
                                                            .collect(Collectors.toList());
            formattedSalaries.forEach(printEmployee);
            conn.close();
        }catch(SQLException e){
            System.out.println("Connection failed!");
        }
    }
}
