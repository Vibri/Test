package cz.vibri.concurrency.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CollectionsAndStreams {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Adam");
        names.add("Ana");
        names.add("Kevin");

        Stream<String> nameStream = names.stream();

        nameStream.forEach(System.out::println);
    }
}
