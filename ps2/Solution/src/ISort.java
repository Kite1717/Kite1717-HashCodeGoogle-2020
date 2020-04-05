import java.util.*;
import java.util.stream.Collectors;

public interface ISort {

     static Map<Integer, Double> sortByValueReverseOrder(final Map<Integer, Double> wordCounts) {
        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<Integer, Double>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}
