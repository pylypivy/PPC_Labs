public class MultiThreadJoin {
    public static void main(String[] args) {
        /* Main thread creates and starts thread threadA */
        Thread threadA = new Thread(() -> {
            System.out.println("Thread threadA: Starting...");
            /* Nested within threadA’s run() code is the creation and starting of threadB */
            // Thread A creates Thread B
            Thread threadB = new Thread(() -> {
                System.out.println("Thread threadB: Working...");
                try {
                    Thread.sleep(2000); // Simulate some work
                } catch (InterruptedException e) {
                    // YOUR CODE – deal with the interrupt
                    System.out.println("threadB interrupted...");
                    Thread.currentThread().interrupt();
                }
                System.out.println("threadB: Finished.");
            });
            threadB.start();
            try {
               //YOUR CODE
                threadB.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }); //end of threadA’s run() implementation
        threadA.start();
        System.out.println("Main thread: I've started threadA and my job is done!");
    }
}
