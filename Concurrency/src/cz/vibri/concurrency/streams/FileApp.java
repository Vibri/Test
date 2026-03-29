package cz.vibri.concurrency.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileApp {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Projekty\\GitHub\\Vibri\\Udemy\\Concurrency\\src\\cz\\vibri\\concurrency\\streams\\names";

        Stream<String> namesStream = Files.lines(Path.of(path));
        List<String> names = namesStream.filter(x -> x.startsWith("A")).toList();

        names.forEach(System.out::println);
    }
}
