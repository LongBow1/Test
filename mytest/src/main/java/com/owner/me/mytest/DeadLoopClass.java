package com.owner.me.mytest;

import org.omg.PortableServer.THREAD_POLICY_ID;

public class DeadLoopClass {
    static {
        if(true){
            System.out.println(Thread.currentThread() + " init DeadLoopClass");
            while (true){

            }

        }

    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                DeadLoopClass deadLoopClass = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " end");
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
