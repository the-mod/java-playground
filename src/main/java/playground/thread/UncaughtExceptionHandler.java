package playground.thread;

public class UncaughtExceptionHandler {
  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      throw new RuntimeException("Ignored by the JVM");
    });
    t1.setName("Thread t1");

    Thread.UncaughtExceptionHandler uncaughtExceptionHandler =
            (thread, ex) -> System.out.println(thread.getName() + " - Uncaught exception: " + ex);

    t1.setUncaughtExceptionHandler(uncaughtExceptionHandler);
    t1.start();

    System.out.println("Thread t1 is alive: " + t1.isAlive());

    Thread t2 = new Thread();
    t2.setName("Thread t2");

    System.out.println("Thread t2 is alive: " + t2.isAlive());

    t2.start();
  }
}
