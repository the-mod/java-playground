package playground.virtualthreads2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Threads2Exception {

  public static void main(String[] args) {
    System.out.println("starting on " + Thread.currentThread().getName());
    List<Callable<String>> tasks = IntStream.rangeClosed(0, 1000).boxed()
            .map(i -> (Callable<String>)() -> {
                System.out.println(Thread.currentThread() + " - waiting...: " + i);
                Thread.sleep(2000);
                throw new OutOfMemoryError("TEST");
            }).toList();

    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    try {
      executorService.invokeAll(tasks);
      System.out.println("Nothing happend, but it should");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
