package playground.thread;

public class StartRun {

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> System.out.println("Running in: " + Thread.currentThread().getName()));

    t1.run(); // run executes on the Caller Thread
    t1.start(); // start executes on a new Thread with a new call stack for run method/runnable
  }
}
