package com.owner.me.mytest;

import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSynchronizedThis {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);

        final SynchronizedClass sc = new SynchronizedClass();

        for (int i=0;i<3;i++){
            Runnable runnable = () -> {
                try {
                    cdOrder.await();;
                    sc.started();
                    cdAnswer.countDown();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            };
            executorService.execute(runnable);
        }

        try {
            Thread.sleep((long)Math.random()*10000);
            System.out.println("thread "+ Thread.currentThread().getName() + " publish execute order.");
            long beginTime = System.currentTimeMillis();
            System.out.println("thread "+Thread.currentThread().getName()+" order already send, waiting for result.");
            cdAnswer.await();
            System.out.println("thread "+Thread.currentThread().getName()+" already received all execute result, total time cost is:"+ (System.currentTimeMillis()-beginTime));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        executorService.shutdown();

    }

}
