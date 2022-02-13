package playground.rector2;

import rx.Observer;

import java.util.function.Consumer;

public class App {

    public static void main(String[] args) {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        };

        observer.onNext("test");

        Producer p = new Producer();

        Consumer<String> c = (s -> {
            System.out.println("Received: " + s + " on " + Thread.currentThread().getName());
            observer.onNext(s);
        });
        System.out.println("Here is: " + Thread.currentThread().getName());
        p.subscribe(c);
        System.out.println("Here is again: " + Thread.currentThread().getName());
    }
}
