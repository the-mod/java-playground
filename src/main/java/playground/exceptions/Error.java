package playground.exceptions;

public class Error {
  private static void doIt() {
    throw new OutOfMemoryError("Error from Callable");
  }

  public static void main(String[] args) {
    System.out.println("App should be terminated by the OutOfMemoryError");
    doIt();
  }
}
