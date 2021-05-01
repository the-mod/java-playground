package playground.reactor;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class FirstFlux {

    public static Consumer<Integer> printConsumer() {
        return (i -> System.out.println(i.toString()));
    }

    public static Consumer<Throwable> errorConsumer() {
        return (e -> System.out.println("Error happened: " + e));
    }

    public static Runnable completeConsumer() {
        return (() -> System.out.println("Stream is complete"));
    }

    public static void main(String[] args) {
        Integer[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Flux<Integer> flux = Flux.fromArray(input);
        flux.map(i -> i * 2).subscribe(printConsumer(),errorConsumer(), completeConsumer());
    }
}

