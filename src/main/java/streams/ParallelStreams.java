package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelStreams {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println("--- Example 1 ---");
        numbers.stream()
            .map(s -> Thread.currentThread().getName())
            .forEach(System.out::println);

        System.out.println("--- Example 2 ---");
        numbers.parallelStream()
            .map(s -> Thread.currentThread().getName())
            .forEach(System.out::println);

        System.out.println("--- Example 3 ---");
        List<String> threadsUsed = numbers.parallelStream()
            .map(s -> Thread.currentThread().getName())
            .collect(Collectors.toList());

        threadsUsed.forEach(System.out::println);

    }

}
