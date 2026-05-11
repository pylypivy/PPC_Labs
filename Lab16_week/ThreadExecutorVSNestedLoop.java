package org.example;
import java.util.concurrent.*;

public class ThreadExecutorVSNestedLoop {
    public static void main(String[] args) throws Exception {
        int[] nValues = {1000, 5000, 10000};

        for (int n : nValues) {
            int[] a = new int[n];
            int[][] b = new int[n][n];
            // Fill arrays with random data here...
            for(int i=0; i<n; i++){
               a[i] = (int) (Math.random() * 100) + 1;
            }

            for(int j =0; j<n;j++){
                for(int i=0; i<n; i++){
                    b[j][j] = (int) (Math.random() * 100) + 1;
                }
            }
            // Sequential Execution
            long start = System.currentTimeMillis();
            long[] resultsSeq = new long[n];
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    resultsSeq[j] += (long) a[i] * b[j][i];
                }
            }
            System.out.println("n=" + n + " Sequential: " + (System.currentTimeMillis() - start) + "ms");

            // Thread Pool Execution
            int cores = Runtime.getRuntime().availableProcessors();
            ExecutorService pool = Executors.newFixedThreadPool(cores);
            long startPool = System.currentTimeMillis();

            for (int j = 0; j < n; j++) {
                final int row = j;
                pool.execute(() -> {
                    long sum = 0;
                    for (int i = 0; i < n; i++) {
                        sum += (long) a[i] * b[row][i];
                    }
                });
            }
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.HOURS);
            System.out.println("n=" + n + " Thread Pool: " + (System.currentTimeMillis() - startPool) + "ms");
        }
    }
}
