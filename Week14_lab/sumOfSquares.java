import java.util.Random;
class sumOfSquaresThread implements Runnable{
    int [] arr;
    int start;
    int end;
    long partialSum;

    public sumOfSquaresThread(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end= end;
    }

    public void run(){
        partialSum = sumOfSquares.sum(arr,start,end);
    }

    public long getPartialSum() {
        return partialSum;
    }
}
public class sumOfSquares {
    public static void main(String[]args) throws InterruptedException{
        if (args.length < 1) {
            System.out.println("Please provide a value for n: java sumOfSquares <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int[] arr = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(100) + 1;
        }

        long startTime = System.nanoTime();
        long total = sum(arr,0,n);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime)/1_000_000.0;
        System.out.println("To calculate the sum of squares using loop (sum = "+total+", n = "
                           +n+"), took: "+duration+"ms");
        sumOfSquaresThread t1 = new sumOfSquaresThread(arr,0,n/2);
        Thread thread1 = new Thread(t1);
        sumOfSquaresThread t2 = new sumOfSquaresThread(arr,n/2,n);
        Thread thread2 = new Thread(t2);
        long startTime2 = System.nanoTime();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        long total2 = t1.getPartialSum()+t2.getPartialSum();
        long endTime2 = System.nanoTime();
        double duration2 = (endTime2 - startTime2)/1_000_000.0;
        System.out.println("To calculate the sum of squares using two threads (sum = "+total2+", n = "
                +n+"), took: "+duration2+"ms");
    }

    public static long sum( int [] arr, int start, int end){
        long total =0;
        for(int i= start; i<end; i++){
            total +=(long) arr[i]*arr[i];
        }
        return total;
    }
}
