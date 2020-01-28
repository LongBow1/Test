package com.owner.me.mytest;

public class SynchronizedMethodClass {
    public synchronized void start() throws InterruptedException{
        Thread.sleep(100);
        System.out.println("i am running for ten ms");
    }
}
