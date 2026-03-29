package cz.vibri.concurrency.streams;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamApp {

    public static void main(String[] args) {
        int[] nums = {1,5,3,-2,9,12};

//        int sum = 0;
//
//        for(int i=0; i<nums.length;i++) {
//            System.out.println(nums[i]);
//            sum = sum + nums[i];
//        }
//
//        System.out.println(sum);

        // lambda expression
        // :: operator or method reference
        Arrays.stream(nums).forEach(System.out::println);

        int sum = Arrays.stream(nums).sum();
        System.out.println(sum);

        IntStream.range(0,5).filter(x -> x%2 !=0).forEach(x -> System.out.print(x + " "));
    }
}
