package playground.virtualthreads2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Threads {

  public static void main(String[] args) throws InterruptedException {
    List<Runnable> tasks = IntStream.rangeClosed(0, 100).boxed()
            .map(i -> {
              Runnable callable = () -> {
                try {
                  Thread.sleep(2000);
                } catch (InterruptedException e) {
                  throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " - Done with: " + i);
              };
              return callable;
            }).toList();

    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    tasks.forEach(executorService::execute);
    // main thread is not blocked
    //Thread.sleep(10000);
  }
}
