package forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {
    static final ForkJoinPool pool = new ForkJoinPool(4);

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }

        list.parallelStream().forEach(item -> System.out.println(Thread.currentThread().getName() + " "+ item));

        pool.submit(() -> list.parallelStream().forEach(item -> System.out.println(Thread.currentThread().getName() + " "+ item))).join();
        System.out.println("main is over");
    }
}
