package playground.collector;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PairCollector {
    public static void main(String[] args) {
      List<Pair<String, Object>> pairs = new ArrayList<>();

      // Add some pairs to the list
      pairs.add(Pair.of("key1", "value1"));
      pairs.add(Pair.of("key1", "value2"));
      pairs.add(Pair.of("key2", "value3"));

      Map<String, List<Object>> map = pairs.stream()
              .collect(Collectors.groupingBy(Pair::getLeft, Collectors.mapping(Pair::getRight, Collectors.toList())));

      System.out.println(map);
    }
}
