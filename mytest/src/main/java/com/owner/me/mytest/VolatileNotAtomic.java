package com.owner.me.mytest;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.LongAdder;

public class VolatileNotAtomic {
    private static volatile long count = 0;
    private static final int NUMBER = 1000000;

    public static void main(String[] args) {

        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

        LongAdder longAdder = new LongAdder();
        longAdder.add(1);
        SubtractThread subtractThread = new SubtractThread();
        subtractThread.start();
        for(int i=0;i<NUMBER;i++){
            count++;
            System.out.println(Thread.currentThread().getName()+":"+i);
        }

        while (subtractThread.isAlive()){}
        System.out.println(count);

    }

    private static class SubtractThread extends Thread{

        @Override
        public void run(){
            for (int i=0;i < NUMBER;i++){
                count--;
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
