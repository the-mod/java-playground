package playground.completables;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exceptionally {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BasicThreadFactory factory =  new BasicThreadFactory.Builder()
                .namingPattern("my-pool-%d")
                .daemon(true)
                .priority(Thread.MAX_PRIORITY)
                .build();

        ExecutorService pool = Executors.newFixedThreadPool(2, factory);

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Running long computational Task on Thread: " +  Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println("oho, something went wrong: " +  Thread.currentThread().getName());
                throw new IllegalStateException("TEST");
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }, pool)
        .exceptionally((ex) -> {
            System.out.println("here at exceptionally an exception occurred: " + ex);
            return "This comes from exceptionally";
        })
        .thenAccept(s -> System.out.println(s))
        .whenComplete((s, e) -> {
            // prints nothing, s and e are null cause exceptionally handled the error and returned a normal finished stage
            // see exceptionally2.java
            if (e != null) {
                System.err.println("exception occurred: " + e);
            } else {
                System.out.println(s);
            }
        });

        System.out.println("Waiting for Future: " +  Thread.currentThread().getName());

        future.get();

        System.out.println("Done: " +  Thread.currentThread().getName());

    }
}
