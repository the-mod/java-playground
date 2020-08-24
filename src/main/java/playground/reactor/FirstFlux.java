package playground.reactor;

import reactor.core.publisher.Flux;

public class FirstFlux {

    public static void main(String[] args) {
        Integer[] input = {1,2,3,4,5,6,7,8,9,10};
        Flux<Integer> flux = Flux.fromArray(input);
        flux
        .map(i -> i*2)
        .subscribe(i -> System.out.println(i.toString()));
    }
}

