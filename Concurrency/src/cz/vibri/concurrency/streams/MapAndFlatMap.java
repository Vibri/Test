package cz.vibri.concurrency.streams;

import java.util.*;
import java.util.stream.Collectors;

public class MapAndFlatMap {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Herman Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));

        // map() and flatMap() are similar to selecting a column SQL
        // number of characters in every word
        // map() - transform the original values
        List<String> words = Arrays.asList("Adam", "Ana", "Daniel");
        List<Integer> length = words.stream().map(String::length).collect(Collectors.toList());
        length.stream().forEach(System.out::println);

        //create a list containing the square values
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        nums.stream().map(n -> n*n).collect(Collectors.toList()).forEach(System.out::println);

        //flatMap - mapping each array not with a stream but with the
        // contents of that stream
        // [[1,3,5],[5,13]] -> [1,3,5,5,13]
        // "hello" "shell" - get all the unique characters (h,e,l,o,s)
        String[] array = {"hello", "shell"};
        List<String> unique = Arrays.stream(array).map(w -> w.split(""))
                .flatMap(Arrays::stream).distinct().collect(Collectors.toList());

        unique.stream().forEach(System.out::println);
    }
}
