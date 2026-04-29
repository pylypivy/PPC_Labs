import java.util.List;

/**
 * I created the Process class to simulate some work by filtering the data
 * and picking only those people whose commission rate is greater than 10%.
 */
public class Process {
    public List<SalesPerson> processData(List<SalesPerson> input) {
        System.out.println("Processing data...");
        return input.stream()
                .filter(s -> s.getTotalCommission() > 0.10)
                .toList();
    }
}
