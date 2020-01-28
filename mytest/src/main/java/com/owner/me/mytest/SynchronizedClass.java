package com.owner.me.mytest;

public class SynchronizedClass {

    public void started() throws InterruptedException{
        Thread.sleep(100);
        synchronized (this){
            System.out.println("i am running for ten ms");
        }
    }
}
