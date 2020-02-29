package com.owner.me.mytest;

public class StopThreadTest {

    private volatile static boolean stopFlag = false;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopFlag){
                    i++;
                }
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopFlag = true;

    }
}
