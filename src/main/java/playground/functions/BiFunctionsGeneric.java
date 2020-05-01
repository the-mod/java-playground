package playground.functions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BiFunctionsGeneric {

    /**
     *
     * @param a1 argument 1
     * @param a2 argument 2
     * @param calculate the calculate function
     * @param formatter the formatting function
     * @param <A1> type of argument 1
     * @param <A2> type of argument 2
     * @param <R1> return type of calculate function
     * @param <R2> return type of formatting function
     * @return result in format of R2
     */
    public static <A1, A2, R1, R2> R2 getResult(A1 a1, A2 a2, BiFunction<A1, A2, R1> calculate, Function<R1, R2> formatter) {
            R2 result = calculate.andThen(formatter).apply(a1, a2);
            return result;
    }



    public static void main(String[] args) {
        Function<Integer, String> sumFormat = (result) -> "The sum is: " + result;
        BiFunction<String, String, Integer> addNumbersAsString = (String a1, String a2) -> Integer.valueOf(a1 + a2);

        Function<Integer, String> multiplyFormat = (result) -> "The product is: " + result;
        BiFunction<Integer, Integer, Integer> multiplyIntegers = (Integer a1, Integer a2) -> a1 * a2;

        String result1 = getResult("100", "200", addNumbersAsString, sumFormat);
        System.out.println(result1); // :D

        String result2 = getResult(100, 200, multiplyIntegers, multiplyFormat);
        System.out.println(result2);

        // w/o assigning to variables
        String result3 = getResult(100, 200, (a1, a2) -> a1 * a2, (result) -> "The product is: " + result);
        System.out.println(result3);

    }
}
