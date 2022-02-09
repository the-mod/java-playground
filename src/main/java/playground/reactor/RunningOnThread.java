package playground.reactor;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class RunningOnThread {

    class MyThread extends Thread {
        public void run() {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread running on " + Thread.currentThread().getName());

            Disposable disposable = Flux.fromStream(IntStream.range(0, 100).boxed())
                    .doOnNext(v -> System.out.println("1. Got " + v + " on " + Thread.currentThread().getName()))
                    .publishOn(Schedulers.boundedElastic())
                    .doOnNext(v -> System.out.println("2. Got " + v + " on " + Thread.currentThread().getName()))
                    //.subscribeOn(Schedulers.parallel())
                    .subscribe(n -> System.out.println("3. Got " + n + " on " + Thread.currentThread().getName()));

            System.out.println("Is done? " + disposable.isDisposed());
        }
    }

    public static void main(String[] args) {
        MyThread t = new RunningOnThread().new MyThread();
        t.setDaemon(false);
        t.start();
    }
}
