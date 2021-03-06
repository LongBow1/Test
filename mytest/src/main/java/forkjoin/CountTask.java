package forkjoin;

import net.sf.ehcache.search.aggregator.Count;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

    private static final int THreshold = 2;
    private int start;
    private int end;
    public CountTask(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= THreshold;
        if(canCompute){
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }else {
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);

            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        CountTask task = new CountTask(1, 100);

        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }catch (ExecutionException ex){
            ex.printStackTrace();
        }
    }
}
