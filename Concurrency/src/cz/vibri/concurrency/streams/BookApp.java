package cz.vibri.concurrency.streams;

import java.util.*;
import java.util.stream.Collectors;

public class BookApp {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Herman Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));

        List<String> result = books.stream()
                .filter(b -> b.getType() == Type.NOVEL)
                .sorted(Comparator.comparing(Book::getAuthor))
                .map(Book::getAuthor)
                .collect(Collectors.toList());
//                .forEach(System.out::println);

        result.stream().forEach(System.out::println);

        System.out.println(Arrays.toString(result.toArray()));

        // grouping by type
        Map<Type, List<Book>> booksByType = books.stream().collect(Collectors.groupingBy(Book::getType));

        booksByType.entrySet().stream().forEach(e -> System.out.println(e));

        // finding 2  books with more than 500 pages (number of pages)
        List<String> longestBooks = books.stream()
                .filter(p -> p.getPages() > 500)
                .map(Book::getTitle).limit(2)
                .collect(Collectors.toList());

        longestBooks.stream().forEach(System.out::println);

        System.out.println("exercise");
        List<Book> twoWordsTitle = books.stream()
                .filter(b -> b.getTitle().split(" ").length == 2)
                .collect(Collectors.toList());

        twoWordsTitle.stream().forEach(System.out::println);

        // external iteration (collections)
        List<String> titles = new ArrayList<>();

        for (Book book : books) {
            titles.add(book.getTitle());
        }

        Iterator<Book> iterator = books.iterator();

        // inherently sequential
        // no parallelization
        while(iterator.hasNext()) {
            titles.add(iterator.next().getTitle());
        }

        // stream API - internal iteration
        // paralle quite easily
        List<String> titles2 = books.stream().map(Book::getTitle).collect(Collectors.toList());

        titles2.forEach(System.out::println);
    }
}
