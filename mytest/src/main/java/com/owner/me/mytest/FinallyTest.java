package com.owner.me.mytest;

public class FinallyTest {
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

        System.out.println(tryCatchFinallyTest());
    }

    static int tryCatchFinallyTest(){
        try {
            System.out.println("try block");
            return 1;

        }catch (Exception ex){
            System.out.println("exception");
            return 2;
        }finally {
            System.out.println("finally");
            return 3;
        }
    }
}
