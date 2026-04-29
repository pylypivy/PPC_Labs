import java.util.List;

/**
 * Main class starts 3 threads with different tasks in a nested way.
 */
public class Main {
    public static void main(String[] args) {
        // Thread 1 retrieves data from the database
        Thread fetchThread = new Thread(() -> {
            System.out.println("Thread 1: Starting database retrieval...");
            ConnectionClass db = new ConnectionClass();
            List<SalesPerson> data = db.getSalesmanList();
            System.out.println("Thread 1: Retrieved " + data.size() + " records.");

            // Thread 2 processes the data fetched in Thread1
            Thread processThread = new Thread(() -> {
                System.out.println("Thread 2 (Process): Starting data processing...");
                Process processor = new Process();
                List<SalesPerson> processedData = processor.processData(data);

                // Thread 3 writes the processed data into the csv file
                Thread writeThread = new Thread(() -> {
                    System.out.println("Thread 3 (Write): Writing to CSV...");
                    WriteToFile writer = new WriteToFile();
                    writer.saveToCSV(processedData, "output.csv");
                });

                writeThread.start();
                try {
                    writeThread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Thread 2 (Process): Done.");
            });

            processThread.start();
            try {
                processThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Thread 1 (Fetch): Done.");
        });

        fetchThread.start();

        try {
            fetchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main: Data is successfully written to the file!");
    }
}
