class sumThread implements Runnable{
    String name;

    public String getName(){
        return name;
    }
    public void run(){
        int sum = 0;
        for(int i = 1; i <= 100; i++){
            sum += i;
        }
        System.out.println(name + " calculated the sum: " + sum);
    }

    public sumThread(String name){
        this.name = name;
    }
}

public class ImplementationThread {
    public static void main(String[] args){
        sumThread t1 = new sumThread("Thread 1");
        Thread thread1 = new Thread(t1);
        thread1.start();
        try{
            thread1.join();
        }catch(InterruptedException e){

            System.out.println("Main thread interrupted");
        }

        System.out.println("Thread 1 is done! Main is exiting...");
    }
}
