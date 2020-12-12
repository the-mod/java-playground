package playground.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class BatchCollector<T> implements Collector<T, List<Batch<T>>, List<Batch<T>>> {

    private int batchSize;

    public BatchCollector(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public Supplier<List<Batch<T>>> supplier() {
        Supplier<List<Batch<T>>> supplier = () -> new ArrayList<Batch<T>>();
        return supplier;
    }

    @Override
    public BiConsumer<List<Batch<T>>, T> accumulator() {
        return new BiConsumer<List<Batch<T>>, T>() {
            @Override
            public void accept(List<Batch<T>> batches, T element) {
                // initially empty
                if (batches.size() == 0) {
                    batches.add(new Batch<>(batchSize));
                }
                Batch batch = batches.get(batches.size() -1 );
                if (!batch.tryAdd(element)) {
                    batch = new Batch(batchSize);
                    batch.tryAdd(element);
                    batches.add(batch);
                }
            }
        };
    }

    @Override
    public BinaryOperator<List<Batch<T>>> combiner() {
        return (ts, ots) -> {
            ts.addAll(ots);
            return ts;
        };
    }

    @Override
    public Function<List<Batch<T>>, List<Batch<T>>> finisher() {
        return batches -> batches;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
