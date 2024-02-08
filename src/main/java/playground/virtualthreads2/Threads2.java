package playground.virtualthreads2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Threads2 {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("starting on " + Thread.currentThread().getName());
    List<Callable<String>> tasks = IntStream.rangeClosed(0, 1000).boxed()
            .map(i -> {
              Callable<String> callable = () -> {
                try {
                  Thread.sleep(2000);
                } catch (InterruptedException e) {
                  throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " - Done with: " + i);
                return "TEST";
              };
              return callable;
            }).toList();

    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    executorService.invokeAll(tasks);
    System.out.println("Finite");
  }
}
