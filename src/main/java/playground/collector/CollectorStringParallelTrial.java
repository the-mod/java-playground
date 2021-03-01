package playground.collector;

import java.util.Arrays;
import java.util.List;

public class CollectorStringParallelTrial {

    public static void main(String[] args) {
        int batchSize = 25;
        BatchCollector<String> batchCollector = new BatchCollector(batchSize);
        // 100 words
        String inputString = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr," +
                " sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat," +
                " sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum." +
                " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet." +
                " Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor" +
                " invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam" +
                " et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem" +
                " ipsum dolor sit amet";

        String[] split = inputString.split(" ");
        List<String> strings = Arrays.asList(split);

        List<Batch<String>> collect = strings.parallelStream().collect(batchCollector);

        for (int i=0; i < collect.size(); i++) {
            Batch<String> batch = collect.get(i);
            System.out.println("Result Batch #" + i + " with size " + batch.getSize());
            for(String element: batch.getAllElements()) {
                System.out.println("    - " + element);
            }
        }
    }
}
