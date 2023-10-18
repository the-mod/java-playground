package playground.functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Functional {

    public static void main(String[] args) {
        // void
        FunctionalInterface f = () -> {
            System.out.println("I was called");
        };
       f.doSomething();

       // now with return
       GreetingInterface g = (name) -> "Hi " + name;
       String greeting = g.greet("John Doe");
       System.out.println(greeting);


        // now I use a functional interface implementation of a existing class
        // and use Method reference to the sayHi Method
        GreetingInterface g2 = GreetingUtil::sayHi;
        String greeting2 = g2.greet("Jane Doe");
        System.out.println(greeting2);

        // looping over list of names and print it with help of method reference
        List<String> names = Arrays.asList("A", "B", "C");
        names.stream().forEach(GreetingUtil::printGreeting);
    }

}
