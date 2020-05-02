package playground.functional;

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
        // and I a Method reference to the sayHi Method
        GreetingInterface g2 = GreetingUtil::sayHi;
        String greeting2 = g.greet("Jane Doe");
        System.out.println(greeting2);
    }

}
