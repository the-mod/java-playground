package playground.virtualthreads2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Threads2Exception {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("starting on " + Thread.currentThread().getName());
    List<Callable<String>> tasks = IntStream.rangeClosed(0, 1000).boxed()
            .map(i -> {
              Callable<String> callable = () -> {
                try {
                  System.out.println(Thread.currentThread() + " - waiting...: " + i);
                  Thread.sleep(2000);
                  throw new RuntimeException("TEST");
                } catch (InterruptedException e) {
                  throw new RuntimeException(e);
                }
              };
              return callable;
            }).toList();

    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    executorService.invokeAll(tasks);
    System.out.println("Finite");
  }
}
