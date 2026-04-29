import java.io.PrintWriter;
import java.util.List;

/**
 * WriteToFile class writes data to a CSV file after the processing.
 */
public class WriteToFile {
    public void saveToCSV(List<SalesPerson> data, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println("Name,TotalCommission"); // Header
            for (SalesPerson s : data) {
                writer.printf("%s,%.2f%n",
                        s.getName(), s.getTotalCommission());
            }
            System.out.println("Data successfully written to " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
