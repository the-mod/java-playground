package playground.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

    private static class C {
        private int count;

        public C (int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }
    }

    private static class B {
        private int count;

        public B (int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }
    }

    private static class A {
        private int count;

        public A (int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }
    }


    public static class Runner {

        public C createC(int counter) {
            return new C(counter);
        }

        public B mapToB(A input) {
            return new B(input.getCount());
        }

        public Stream<C> flatMapBToC(B input) {
            return IntStream.range(0, input.getCount())
                    .mapToObj(this::createC);
        }

        public Stream<C> flatMapBToCWithNull(B input) {
            return IntStream.range(0, input.getCount())
                    .mapToObj(i -> i == 0 ? null : new C(i));
        }

        public void run1() {
            List<A> input = Arrays.asList(new A(1), new A(2), new A(3), new A(4), new A(5));
            List<C> output = input
                    .parallelStream()
                    .map(this::mapToB)
                    .flatMap(this::flatMapBToC)
                    .collect(Collectors.toList());

            System.out.println("Run 1 prints:");
            output.stream().forEach(entry -> System.out.println(entry.getCount()));
            System.out.println("=============");
        }

        public void run2() {
            List<A> input = Arrays.asList(new A(1), new A(2), new A(3), new A(4), new A(5));
            List<C> output = input
                    .parallelStream()
                    .map(this::mapToB)
                    .flatMap(this::flatMapBToCWithNull)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            System.out.println("Run 2 prints:");
            output.stream().forEach(entry -> System.out.println(entry.getCount()));
            System.out.println("=============");
        }
    }

    public static void main(String[] args) {
        new Runner().run1();
        new Runner().run2();
    }
}
