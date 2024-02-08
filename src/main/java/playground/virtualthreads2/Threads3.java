package playground.virtualthreads2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Threads3 {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("starting on " + Thread.currentThread().getName());
    List<Runnable> tasks = IntStream.rangeClosed(0, 1000).boxed()
            .map(i -> {
              Runnable runnable = () -> {
                try {
                  Thread.sleep(2000);
                } catch (InterruptedException e) {
                  throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " - Done with: " + i);
              };
              return runnable;
            }).toList();

    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    List<? extends Future<?>> futures = tasks.stream().map(task -> executorService.submit(task)).toList();
    CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
    System.out.println("Finite");
  }
}
