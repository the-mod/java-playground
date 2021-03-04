package playground.roundrobin;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedResource synchronizedResource = new SynchronizedResource();
        AtomicResource atomicResource = new AtomicResource();

        System.out.println("Running synchronized access");
        for (int i=0; i<21; i++) {
            Thread t = new Thread("test-thread-"+i) {
                @Override
                public void run() {
                    System.out.println("[" + System.currentTimeMillis() + "] - "  + Thread.currentThread().getName() + ": " + synchronizedResource.getNumber());
                }
            };
            t.start();
        }

        Thread.sleep(1000);

        System.out.println("Running AtomicInt Example");
        for (int i=0; i<21; i++) {
            Thread t = new Thread("test-thread-"+i) {
                @Override
                public void run() {
                    System.out.println("[" + System.currentTimeMillis() + "] - "  + Thread.currentThread().getName() + ": " + atomicResource.getNumber());
                }
            };
            t.start();
        }
    }
}
