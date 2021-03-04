package playground.roundrobin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicResource {

        private List<String> strings = Arrays.asList("One", "Two", "Three");
        private AtomicInteger counter = new AtomicInteger(0);

        public synchronized String getNumber() {
            int position = counter.getAndIncrement() % strings.size() ;
            return strings.get(position);
        }
    }


