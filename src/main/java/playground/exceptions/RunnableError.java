package playground.exceptions;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableError {
  private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

  public static void main(String[] args) throws Throwable {

    Future<Void> future = (Future<Void>) executorService.submit(new Runnable() {
      @Override
      public void run() {
        throw new OutOfMemoryError("Error from Runnable");
      }
    });

    try {
      Void result = future.get();
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
