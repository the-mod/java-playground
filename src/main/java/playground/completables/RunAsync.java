package playground.completables;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

public class RunAsync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BasicThreadFactory factory =  new BasicThreadFactory.Builder()
                .namingPattern("my-pool-%d")
                .daemon(true)
                .priority(Thread.MAX_PRIORITY)
                .build();

        ExecutorService pool = Executors.newFixedThreadPool(2, factory);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Running long computational Task on Thread: " +  Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println("Done running long computational : " +  Thread.currentThread().getName());
                return "Hello";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }, pool);
        System.out.println("Waiting for Future: " +  Thread.currentThread().getName());
        String result = future.
                thenApplyAsync((s) -> {
                    System.out.println("thenApplyAsync: " +  Thread.currentThread().getName());
                    return s + ", whats up?";
                })
                .get();
        System.out.println("Result: " +  result);
        System.out.println("Done: " +  Thread.currentThread().getName());

    }
}
