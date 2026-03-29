package cz.vibri.concurrency.streams;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamsWithStrings {
    public static void main(String[] args) {
        String[] names = {"Adam", "Daniel", "Martha", "Kevin", "Ben", "Joe", "Brad", "Susan"};

        Stream.of(names).filter(x -> x.startsWith("B")).sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
}
