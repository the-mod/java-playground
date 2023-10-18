package playground.volatileExample;


public class Volatile {

    public static volatile int counter = 0;

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            System.out.println("Starting Listener");
            int local_copy = counter;
            while ( local_copy < 5){
                if( local_copy != counter){
                    System.out.println("Changed counter from other Thread. Value is now: " + counter);
                    local_copy = counter;
                }
            }
        }
    }

    static class ChangeMaker extends Thread{
        @Override
        public void run() {
            System.out.println("Starting Changer");
            int local_value = counter;
            while (counter < 5) {
                System.out.println("Incrementing Counter to " + (local_value+1));
                counter = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    public static void main(String[]args){
        new ChangeListener().start();
        new ChangeMaker().start();
    }
}
