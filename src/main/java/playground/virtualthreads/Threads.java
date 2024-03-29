package playground.virtualthreads;

public class Threads {

    public static void main(String[] args) throws InterruptedException {

        Thread.startVirtualThread(() -> {
            System.out.println("Hello World");
        });

        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Index: " + i);
            }
        };
        Thread vThread = Thread.ofVirtual().start(runnable);
        vThread.join();
    }
}
