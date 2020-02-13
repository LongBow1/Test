package com.owner.me.mytest;

public class VolatileTest {
    public static volatile int race = 0;
    public static void increase(){
        race++;
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args){
        int ii =0;

        System.out.println(ii++);
        System.out.println(ii);

        Thread[] threads = new Thread[THREAD_COUNT];
        for(int i=0;i<THREAD_COUNT;i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i< 10000;i++){
                        increase();
                        //System.out.println(race);
                    }
                }
            });
            threads[i].start();
        }
        /*while (Thread.activeCount() > 1){
            System.out.println(Thread.activeCount());
            Thread.yield();
        }*/
        System.out.println(race);
    }
 }
