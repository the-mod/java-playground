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
            if (Thread.currentThread().getName().equalsIgnoreCase("first")) {
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
     * 1644768054110 - first - Before Execution: initValue
     * 1644768054110 - second - Before Execution: initValue
     * 1644768054130 - first - Init Value: initValue
     * 1644768054130 - first - New Value: first
     * 1644768055131 - first - Leaving sync block
     * 1644768055135 - second - Init Value: first
     * 1644768055135 - second - New Value: second
     * 1644768055298 - first - After Execution: second  => Thread-0 reads new Value from Thread-1 even Thread-1 is still in the sync block
     * 1644768056145 - second - Leaving sync block
     * 1644768056145 - second - After Execution: second
     * 1644768056806 - first - After Sleep: second
     * 1644768057656 - second - After Sleep: second
     */

    public static void main(String[] args) {
        Main main = new Main();
        Main.MyThread t1 = main.new MyThread();
        t1.setDaemon(false);
        t1.setName("first");
        t1.start();

        Main.MyThread t2 = main.new MyThread();
        t2.setDaemon(false);
        t2.setName("second");
        t2.start();
    }
}