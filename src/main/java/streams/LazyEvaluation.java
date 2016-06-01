package streams;

import java.util.stream.Stream;

public class LazyEvaluation {

    public static void main(String[] args) {

        Stream.of(1, 2, 3, 4, 5)
            .filter(s -> {
                System.out.println("filter1: " + s);
                return true;
            });
        // it didn't print anything as lazy evaluation is used - the stream
        // will be evaluated only if the last operation in the chain is terminating

        Stream.of(1, 2, 3, 4, 5)
            .filter(s -> {
                System.out.println("filter2: " + s);
                return true;
            })
            .forEach(s -> System.out.println("foreach: " + s));
        // now after adding the terminating operation the filtering was done

        Stream.of(1, 2, 3, 4, 5)
            .map(s -> {
                System.out.println("mapping: " + s);
                return s;
            })
            .anyMatch(s -> s == 3);
        // only first 3 elements were mapped thanks to lazy evaluation
    }

}
