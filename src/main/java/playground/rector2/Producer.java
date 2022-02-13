package playground.rector2;

import java.util.function.Consumer;

public class Producer {

    private Consumer<String> con;

    class ProducerThread extends Thread {
        public void run() {
            System.out.println("Running on " + Thread.currentThread().getName());
            // run forever ;-)
            while (true) {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String now = String.valueOf(System.currentTimeMillis());
                con.accept(now);
                System.out.println("Published " + now);
            }
        }
    }

    public void subscribe(Consumer<String> consumer) {
        con = consumer;
        ProducerThread t = new ProducerThread();
        t.setName("Producer-Thread");
        t.setDaemon(false);
        t.start();
    }


}
