class Counter{
    int counter = 0;
    public synchronized void increment(){
        counter++;
    }
    public int getCount(){
        return counter;
    }
}

public class SynchronizedCounter {
    public static void main(String[] args) {
        Counter sharedCounter = new Counter();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    sharedCounter.increment();
                }
            });
            threads[i].start();
        }
        for(Thread t : threads){
            try{
                t.join();
            }catch(InterruptedException e){
                System.out.println("Thread interrupted");
            }
        }
        System.out.println("Final count: " + sharedCounter.getCount());
    }
}
