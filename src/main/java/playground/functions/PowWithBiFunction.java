package playground.functions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PowWithBiFunction {

    public static <R> void printResultToChannel(int a, int b,  BiFunction<Integer, Integer, Double> func, Function<Double, R> outputChannel) {
        // chaining functions
        R output = func.andThen(outputChannel).apply(a, b);
        System.out.println(output);
    }

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Double> pow = (x1, x2) -> Math.pow(x1, x2);
        Double result = pow.apply(2, 4);

        // should print 16
        System.out.println(result);

        //============================
        // should print "The Result is: 16"
        // passing the formatting function and the calculating function
        Function<Double, String> format = (r) -> "The Result is: " + String.valueOf(r);
        printResultToChannel(2, 4, pow, format);

    }
}
