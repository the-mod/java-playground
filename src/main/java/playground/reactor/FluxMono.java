package playground.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FluxMono {

    private static Mono<Integer> doSomething(int i) {
        System.out.println(Thread.currentThread().getName() + " - Processing " + i);
        try {
            TimeUnit.MILLISECONDS.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Mono.just(i);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Flux.range(1, 10)
                .doOnSubscribe((unused) -> System.out.println("Got a subscriber"))
                //.subscribeOn(Schedulers.newParallel("custom", 10))
                .subscribeOn(Schedulers.fromExecutorService(executorService), true)
                .flatMap(i -> doSomething(i))
                .doOnComplete(() -> System.out.println("The End is near"))
                .doOnNext(result -> System.out.println(Thread.currentThread().getName() + " - Done with " + result))
                .doOnComplete(() -> System.out.println("Done Execution"))
                .blockLast();

        System.out.println("Shutting down");
        executorService.shutdown();
    }
}
