package playground.roundrobin;

import java.util.Arrays;
import java.util.List;

public class SynchronizedResource {

    private List<String> strings = Arrays.asList("One", "Two", "Three");
    private int counter = 0;
    private int boundary = strings.size() - 1;

    public synchronized String getNumber() {
        int position = counter % strings.size() ;
        // reset counter
        counter = counter == boundary ? 0 : counter + 1;
        return strings.get(position);
    }
}
