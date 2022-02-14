package playground.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;


public class Subscriber {

    class Sub implements org.reactivestreams.Subscriber<List<String>> {
        @Override
        public void onSubscribe(Subscription s) {
            s.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(List<String> o) {
            System.out.println(o.toString());
        }

        @Override
        public void onError(Throwable t) {
        }

        @Override
        public void onComplete() {
        }
    }


    public static void main(String[] args) {
        Sub sub = new Subscriber().new Sub();
        Flux.just("red", "white", "blue")
                .map(String::toUpperCase)
                .bufferTimeout(5, Duration.ofSeconds(5))
                .subscribe(sub);
    }
}
