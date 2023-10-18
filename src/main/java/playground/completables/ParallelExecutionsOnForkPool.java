package playground.completables;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ParallelExecutionsOnForkPool {

    public static void executeOnPool() throws ExecutionException, InterruptedException {
        // run by a CPU with 12 Threads => 3 Iterations needed
        List<Integer> input = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30);

        StopWatch swPool = new StopWatch();
        swPool.start();
        System.out.println("Starting to create Futures: " +  Thread.currentThread().getName());
        List<CompletableFuture<String>> tasks = input
                .parallelStream()
                .peek((c) -> {
                    System.out.println("Now I am processed by: " +  Thread.currentThread().getName());
                })
                .map(entry -> CompletableFuture.supplyAsync(() -> {
                    System.out.println("I am Task #"+entry+" executed by: " +  Thread.currentThread().getName());
                    try {
                        Thread.sleep(30000);
                        System.out.println("Done with Task #"+entry+" executed by: " +  Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "I am task #" + entry;
                })).collect(Collectors.toList());

        CompletableFuture<Void> allFuturesResult = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[tasks.size()]));
        CompletableFuture<List<String>> results = allFuturesResult.thenApplyAsync(__ ->
                tasks.stream().
                peek((s) -> {
                    System.out.println("Collected by: " +  Thread.currentThread().getName());
                })
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));

        System.out.println("Waiting for Futures: " +  Thread.currentThread().getName());
        List<String> strings = results.get();

        System.out.println(strings);
        swPool.stop();
        System.out.println("Done in " + swPool.getTime() + "ms " +  Thread.currentThread().getName());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ParallelExecutionsOnForkPool.executeOnPool();
    }
}
