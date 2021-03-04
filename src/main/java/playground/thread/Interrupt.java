package playground.thread;

public class Interrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread("test-thread") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": I am running...");

                while (!isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + ": still running...");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        interrupt();
                        System.out.println(Thread.currentThread().getName() + ": interrupted while sleeping");
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": Thread is done");
            }
        };

        t.start();
        System.out.println(Thread.currentThread().getName() + ": Going to sleep");
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + ": Going to send interrupt");
        t.interrupt();
    }
}

