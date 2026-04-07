package week12exercise;

public class SalesPerson {
    String name;
    String city;
    double commission;
    double totalSales;

    public SalesPerson(String name, String city, double commission, double totalSales) {
        this.name = name;
        this.city = city;
        this.commission = commission;
        this.totalSales = totalSales;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    @Override
    public String toString() {
        return "\n---------------\nSalesPerson: " + name + "| City: " + city + "| Total Sales: " + totalSales +
        "$ | Commission: " + commission +"%";
    }
}
