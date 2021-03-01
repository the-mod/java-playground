package playground.completables;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exceptionally2 {

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
        .thenAccept(s -> System.out.println(s))
        .whenComplete((s, e) -> {
            // e != null
            if (e != null) {
                System.out.println("whenComplete saw an exception: " + e);
            } else {
                System.out.println(s);
            }
        })
        .exceptionally(ex -> {
            System.out.println("let them complete normally");
            return null;
        })
        .whenComplete((s, e) -> {
            // now s and e are null again
            if (e != null) {
                System.out.println("whenComplete saw an exception: " + e);
            } else {
                System.out.println(s);
            }
        });

        System.out.println("Waiting for Future: " +  Thread.currentThread().getName());

        future.get();

        System.out.println("Done: " +  Thread.currentThread().getName());

    }
}
