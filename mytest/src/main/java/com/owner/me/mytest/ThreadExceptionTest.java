package com.owner.me.mytest;

import tool.CustomThreadFactory;

import java.util.concurrent.*;

public class ThreadExceptionTest {
    static ExecutorService executorService = new ThreadPoolExecutor(1,1,60, TimeUnit.SECONDS,new LinkedBlockingDeque<>(1), new CustomThreadFactory("ThreadExceptionTest"));



    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            int count = 1;
            int sum = 0;
            public void run() {
                try {
                    for (;count<101;){
                        Thread.sleep(10);
                        if(count == 101){
                            return;
                        }
                        if (count == 50){
                            sum = sum + count;
                            count ++;
                            throw new RuntimeException("我是异常");
                        }
                        sum = sum + count;
                        System.out.println(Thread.currentThread().getThreadGroup().activeCount());
                        System.out.println(Thread.currentThread().getName()+"##count:"+count+",sum:"+sum);
                        count ++;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    //注意这个地方
                    System.out.println("重启一个线程，继续执行");
                    executorService.execute(this);
                }
            }
        });
    }
}
