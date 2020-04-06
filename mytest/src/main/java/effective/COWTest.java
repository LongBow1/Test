package effective;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class COWTest {
    static int count = 200000;
    public static void main(String[] args) {
        cowTestV1();
        arrayTest();
    }

    private static void arrayTest() {
        long startTime = System.nanoTime();
        List<Long> test = new ArrayList();
        for(int i=0;i<count;i++){
            test.add(System.nanoTime());
        }
        System.out.println("arrayTest: "+ (System.nanoTime() - startTime));
    }

    private static void cowTestV1() {
        long startTime = System.nanoTime();
        List<Long> test = new CopyOnWriteArrayList<>();
        for(int i=0;i<count;i++){
            test.add(System.nanoTime());
        }
        System.out.println("cowTestV1: "+ (System.nanoTime() - startTime));
    }
}
