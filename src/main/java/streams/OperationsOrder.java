package streams;

import java.util.stream.Stream;

public class OperationsOrder {

    public static void main(String[] args) {

        System.out.println("--- Example 1 ---");

        Stream.of(1, 2, 3, 4)
            .map(s -> {
                System.out.println("mapping1: " + s);
                return s;
            })
            .filter(s -> {
                System.out.println("filter1: " + s);
                return s < 3;
            })
            .forEach(s -> System.out.println("foreach1: " + s));

        System.out.println("--- Example 2 ---");

        Stream.of(1, 2, 3, 4)
            .filter(s -> {
                System.out.println("filter2: " + s);
                return s < 3;
            })
            .map(s -> {
                System.out.println("mapping2: " + s);
                return s;
            })
            .forEach(s -> System.out.println("foreach2: " + s));
    }
}