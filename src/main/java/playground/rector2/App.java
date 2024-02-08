package playground.rector2;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.function.Consumer;

public class App {

    public static void main(String[] args) {
        Observer<String> observer = new Observer<>() {
            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        };

        // Producer should produce timestamps
        // Consumer should consume this timestamps
        // Observer should get every timestamp which was produced

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
