package cz.vibri.concurrency.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShortCircuitingAndLoopFusion {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Herman Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));

        // finding two books with more than 500 pages
        // short circuiting and loop fusion
        // filter() and map() are different operations, they
        // are merged into the same pass(loop fusion)
        // short-circuiting: some operations don't need to process th whole
        // stream to produce a result
        // here we are looking for just 2 items - so the algorithm
        // terminates after finding 2 items!!!
        List<String> titles2 = books.stream()
                .filter(p -> {
                    System.out.println("Filtering " + p.getTitle() + " pages " + p.getPages());
                    return p.getPages() > 500;
                })
                .map(b -> {
                    System.out.println("Mapping " + b.getTitle());
                    return b.getTitle();
                })
                .limit(2)
                .collect(Collectors.toList());
    }
}
