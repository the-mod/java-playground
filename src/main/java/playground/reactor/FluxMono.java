package playground.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FluxMono {

    private static Mono<Integer> doSomething(int i) {
        System.out.println(Thread.currentThread().getName() + " - Processing Input: " + i);
        try {
            TimeUnit.MILLISECONDS.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Mono.just(i*i);
    }

    private static void runSequential() {
        Flux.range(1, 10)
                .doOnSubscribe((unused) -> System.out.println("Got a subscriber"))
                .flatMap(i -> doSomething(i))
                .doOnNext(result -> System.out.println(Thread.currentThread().getName() + " - Done Processing: " + result))
                .doOnComplete(() -> System.out.println(Thread.currentThread().getName() + " - Done Execution"))
                .subscribe((i) -> {
                    System.out.println(Thread.currentThread().getName() + " - Got Result: " + i);
                });
    }

    private static void runParallel() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Flux.range(1, 100)
                .doOnSubscribe((unused) -> System.out.println("Got a subscriber"))
                .parallel()
                .runOn(Schedulers.fromExecutorService(executorService))
                .flatMap(i -> doSomething(i))
                .doOnNext(result -> System.out.println(Thread.currentThread().getName() + " - Done Processing: " + result))
                .doOnComplete(() -> System.out.println(Thread.currentThread().getName() + " - Done Execution"))
                .doOnTerminate(() -> {
                    System.out.println(Thread.currentThread().getName() + " - Terminating");
                })
                .sequential()
                .subscribe((i) -> {
                    System.out.println(Thread.currentThread().getName() + " - Got Result: " + i);
                }, (err) -> {
                    System.err.println("Error " + err.getMessage());
                }, () -> {
                    System.out.println(Thread.currentThread().getName() + " - Shutting down Executor Service");
                    executorService.shutdown();
                });

    }

    public static void main(String[] args) throws InterruptedException {
        runSequential();
        runParallel();
    }
}
