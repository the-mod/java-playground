package playground.collector;

import java.util.Arrays;
import java.util.List;

public class CollectorIntegerTrial {

    public static void main(String[] args) {
        int batchSize = 5;
        BatchCollector<Integer> batchCollector = new BatchCollector(batchSize);
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
        List<Batch<Integer>> collect = input.stream().map(i -> i * i).collect(batchCollector);

        for (int i=0; i < collect.size(); i++) {
            System.out.println("Result Batch #" + i);
            Batch<Integer> batch = collect.get(i);
            for(Integer element: batch.getAllElements()) {
                System.out.println("    - " + element);
            }
        }
    }
}
