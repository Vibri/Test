package cz.vibri.concurrency.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise1 {
    public static void main(String[] args) {
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(4, 5);

        List<List<Integer>> pairs = nums1.stream()
                .flatMap(i -> nums2.stream().map(j -> Arrays.asList(i, j)))
                .collect(Collectors.toList());

        System.out.println(Arrays.toString(pairs.toArray()));

    }
}
