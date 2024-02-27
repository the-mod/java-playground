package playground.virtualthreads2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Threads2Exception2 {

  public static void main(String[] args) throws Throwable {
    System.out.println("starting on " + Thread.currentThread().getName());
    List<Callable<String>> tasks = IntStream.rangeClosed(0, 1000).boxed()
            .map(i -> (Callable<String>)() -> {
                System.out.println(Thread.currentThread() + " - waiting...: " + i);
                Thread.sleep(2000);
                throw new OutOfMemoryError("TEST");
            }).toList();

    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    for (Future<String> stringFuture : executorService.invokeAll(tasks)) {
      try {
        stringFuture.get();
      } catch (ExecutionException e) {
        System.out.println("Exception: " + e);
        System.out.println("Cause: " + e.getCause());
        throw e.getCause();
      }
    }
    System.out.println("Finite");
  }
}
