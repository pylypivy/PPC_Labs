package dbconnection2;
/**
 * The Sales class that represents a single database entry.
 */
public class Sales {
	int orderNumber;
	String customerName;
	String customerCity;
	String salesmanName;
	double amount;
	double commissionAmount;
	/**
	 * Constructor to initialize a Sales object with data from database.
	 * @param orderNumber
	 * @param customerName
	 * @param customerCity
	 * @param salesmanName
	 * @param amount
	 * @param commissionAmount
	 */
	public Sales(int orderNumber, String customerName, String customerCity, 
			String salesmanName, double amount, double commissionAmount) {
		this.orderNumber=orderNumber;
		this.customerName=customerName;
		this.customerCity=customerCity;
		this.salesmanName=salesmanName;
		this.amount=amount;
		this.commissionAmount=commissionAmount;
	}
	
	public String toString() {
		return "-------------Order number:" + orderNumber + "\n| Customer: " + customerName + " \n| Customer's city: " 
	    + customerCity + "\n| Salesman: " + salesmanName + " \n| Amount spend: $" 
				+ amount + " \n| Commission: $" + commissionAmount;
	}
}
