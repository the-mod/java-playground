package playground.exceptions;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableError {
  private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

  public static void main(String[] args) {

    Future<Void> future = executorService.submit(new Callable<Void>() {
      @Override
      public Void call() {
        throw new OutOfMemoryError("Error from Callable");
      }
    });

    try {
      future.get();
    } catch (ExecutionException e) {
      Throwable originalThrowable = e.getCause();
      originalThrowable.printStackTrace();
    } catch (InterruptedException e) {
      // handle interruption
    }

    executorService.shutdown();
    System.out.println("App was not terminated by the OutOfMemoryError");
  }
}
