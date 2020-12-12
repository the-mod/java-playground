package playground.shutdown;

public class ShutdownHook {

    static class Hook extends Thread {
        public void run() {
            System.out.println("Hi, I am the Shutdown hook");
        }
    }

    public static void main(String[] args) {
        Runtime current = Runtime.getRuntime();
        current.addShutdownHook(new Hook());

        for (int i = 1; i <= 10000; i++) {
            System.out.println("Counter is: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Find PID with
    // jps -ml
    // kill <PID> on Linux
    // taskkill /PID <PID> /F on Windows
}
