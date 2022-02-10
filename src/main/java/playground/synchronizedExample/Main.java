package playground.synchronizedExample;


public class Main {

    private Service service = new Service();

    class Service {
        private String string = "initValue";

        public void doSomething() {
            // blocks other Threads as long a Thread is in synchronized block
            synchronized (string) {
                System.out.println(System.currentTimeMillis() + " - " + Thread.currentThread().getName() + " - Init Value: " + string);
                string = Thread.currentThread().getName();
                System.out.println(System.currentTimeMillis() + " - " + Thread.currentThread().getName() + " - New Value: " + string);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + " - " + Thread.currentThread().getName() + " - Leaving sync block");
            }
        }

        public String getValue() {
            return string;
        }
    }

    class MyThread extends Thread {
        public void run() {
            System.out.println(System.currentTimeMillis() + " - " + Thread.currentThread().getName() + " - Before Execution: " + service.getValue());
            service.doSomething();
            // Give first Thread some sleep to let the second Thread change the String Value but not leaving the Sync Method
            // TODO: works correct only if Thread-0 was the first writing the String => How to ensure Thread-0 starts for Thread-1
            if (Thread.currentThread().getName().equalsIgnoreCase("Thread-0")) {
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() + " - " + Thread.currentThread().getName() + " - After Execution: " + service.getValue());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + " - " + Thread.currentThread().getName() + " - After Sleep: " + service.getValue());
        }
    }

    /**
     * Output:
     * <p>
     * 1644510083110 - Thread-0 - Before Execution: initValue
     * 1644510083110 - Thread-1 - Before Execution: initValue
     * 1644510083128 - Thread-0 - Init: initValue
     * 1644510083129 - Thread-0 - New Value: Thread-0
     * 1644510084130 - Thread-0 - Leaving sync block
     * 1644510084134 - Thread-1 - Init: Thread-0
     * 1644510084134 - Thread-1 - New Value: Thread-1
     * 1644510084297 - Thread-0 - After Execution: Thread-1         => Thread-0 reads new Value from Thread-1 even Thread-1 is still in the sync block
     * 1644510085148 - Thread-1 - Leaving sync block
     * 1644510085148 - Thread-1 - After Execution: Thread-1
     * 1644510085807 - Thread-0 - After Sleep: Thread-1
     * 1644510086648 - Thread-1 - After Sleep: Thread-1
     */

    public static void main(String[] args) {
        Main main = new Main();
        Main.MyThread t1 = main.new MyThread();
        t1.setDaemon(false);
        t1.start();

        Main.MyThread t2 = main.new MyThread();
        t2.setDaemon(false);
        t2.start();
    }
}