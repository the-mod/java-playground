package playground.generics;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GenericClass s1 = new StringClass("Hello");
        GenericClass i1 = new IntegerClass(1);

        List<GenericClass> list = new ArrayList<>();
        list.add(s1);
        list.add(i1);

        for (GenericClass g : list) {
            System.out.println(g.getValue());
        }
    }
}
