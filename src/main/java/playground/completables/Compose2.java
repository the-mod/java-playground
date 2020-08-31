package playground.completables;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Compose2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BasicThreadFactory factory = new BasicThreadFactory.Builder()
                .namingPattern("my-pool-%d")
                .daemon(true)
                .priority(Thread.MAX_PRIORITY)
                .build();

        ExecutorService pool = Executors.newFixedThreadPool(2, factory);

        boolean result = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Running long computational Task on Thread: " + Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println("Done with long computational Task: " + Thread.currentThread().getName());
                return "Test finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }, pool).thenCompose((s) ->
                CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println("Doing also long running stuff: " + Thread.currentThread().getName());
                        Thread.sleep(2000);
                        System.out.println("Done with it: " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return false;
                    }
                    System.out.println("Received Result from previous stage: " + s);
                    // check result of previous stage
                    return s != null ? true : false;
                }, pool)).get();

        System.out.println("Result is: " + result);

        System.out.println("Done: " + Thread.currentThread().getName());

    }
}
