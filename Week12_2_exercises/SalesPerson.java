/**
 * SalesPerson object.
 */
public class SalesPerson {
    String name;
    double totalCommission;

    public SalesPerson(String name, double totalCommission) {
        this.name = name;
        this.totalCommission = totalCommission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(double totalCommission) {
        this.totalCommission = totalCommission;
    }

    @Override
    public String toString() {
        return "\n---------------\nSalesPerson: " + name +
                " | Total Commission: " + totalCommission +"$";
    }

}
