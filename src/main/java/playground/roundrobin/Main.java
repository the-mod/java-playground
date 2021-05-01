package playground.roundrobin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedResource synchronizedResource = new SynchronizedResource();

        List<String> resultsSynchronized = Collections.synchronizedList(new ArrayList());
        List<Thread> threadsSynchronized = new ArrayList<>();

        System.out.println("Running synchronized access");
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread("test-thread-"+i) {
                @Override
                public void run() {
                    String result = synchronizedResource.getNumber();
                    resultsSynchronized.add(result);
                    System.out.println("[" + System.currentTimeMillis() + "] - "  + Thread.currentThread().getName() + ": " + result);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadsSynchronized.add(t);
        }

        for (Thread t: threadsSynchronized) {
            t.start();
        }

        for (Thread t: threadsSynchronized) {
            // waiting for all threads to finish
            t.join();
        }

        System.out.println("Got " + resultsSynchronized.size() + " Entries");
        resultsSynchronized
                .stream()
                .collect(Collectors.groupingBy(w -> w))
                .forEach((key, list) -> {
                    System.out.println(key + ": " + list.size());
                });

        Thread.sleep(1000);

        AtomicResource atomicResource = new AtomicResource();
        List<String> resultsAtomic = Collections.synchronizedList(new ArrayList());
        List<Thread> threadsAtomic = new ArrayList<>();

        System.out.println("Running AtomicInt Example");
        for (int i=0; i < 20; i++) {
            Thread t = new Thread("test-thread-"+i) {
                @Override
                public void run() {
                    String result = atomicResource.getNumber();
                    resultsAtomic.add(result);
                    System.out.println("[" + System.currentTimeMillis() + "] - "  + Thread.currentThread().getName() + ": " + result);
                }
            };
            threadsAtomic.add(t);
        }

        for (Thread t: threadsAtomic) {
            t.start();
        }

        for (Thread t: threadsAtomic) {
            // waiting for all threads to finish
            t.join();
        }

        System.out.println("Got " + resultsAtomic.size() + " Entries");
        resultsAtomic
                .stream()
                .collect(Collectors.groupingBy(w -> w))
                .forEach((key, list) -> {
                    System.out.println(key + ": " + list.size());
                });
    }
}
