package cz.vibri.concurrency.streams;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GroupByApp {

    public static void main(String[] args) {

        var people = List.of(
                new Person("Adam", 34, "FINANCE"),
                new Person("Kevin", 12, "IT"),
                new Person("Daniel", 77, "HR"),
                new Person("Ana", 56, "IT"),
                new Person("Joe", 31, "FINANCE")
        );

        var result = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment,
                        Collectors.summarizingInt(Person::getAge)));

        var result2 = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment));

        var result3 = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment,
                        Collectors.mapping(Person::getName, Collectors.toList())));

        var result4 = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment,
                        Collectors.mapping(Person::getName, Collectors.joining(" @ "))));

        var result5 = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment,
                        TreeMap::new,
                        Collectors.mapping(Person::getName, Collectors.joining(" @ "))));

        System.out.println(result);
        System.out.println("----");
        System.out.println(result2);
        System.out.println("----");
        System.out.println(result3);
        System.out.println("----");
        System.out.println(result4);
        System.out.println("----");
        System.out.println(result5);
        System.out.println("----");
    }
}
