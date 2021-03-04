package playground.roundrobin;

import java.util.Arrays;
import java.util.List;

public class SynchronizedResource {

    private List<String> strings = Arrays.asList("One", "Two", "Three");
    private int counter = 0;

    public synchronized String getNumber() {
        int position = counter % strings.size() ;
        counter += +1;
        return strings.get(position);
    }
}
