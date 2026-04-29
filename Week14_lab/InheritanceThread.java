class sumThread2 extends Thread{
    String name;

    public sumThread2(String name){
        this.name=name;
    }
    public void run(){
        int sum = 0;
        for(int i = 1; i <= 100; i++){
            sum += i;
        }
        System.out.println(name + " calculated the sum: " + sum);
    }
}

public class InheritanceThread {
    public static void main(String[]args){
        sumThread2 t1 = new sumThread2("Thread 1");
        t1.start();
        try{
            t1.join();
        }catch(InterruptedException e){
            System.out.println("Main thread interrupted");
        }
        System.out.println("Thread 1 is done! Main is exiting...");
    }
}
