package playground.reactorBackpressure;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class App {

    public static void main(String[] args) {
        //Flux.interval(Duration.ofMillis(1))
        Flux.range(1, Integer.MAX_VALUE)
                .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    int counter;

                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("onSubscribe");
                        this.s = s;
                        System.out.println(Thread.currentThread().getName() + ": Requesting 2 emissions");
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer i) {
                        System.out.println("onNext " + i);
                        counter++;
                        if (counter % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + ": Requesting 2 emissions");
                            s.request(2);
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.err.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }
}
