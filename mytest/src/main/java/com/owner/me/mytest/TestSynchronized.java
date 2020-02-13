package com.owner.me.mytest;

public class TestSynchronized {
    public void test1() {
        synchronized(this) {
            int i = 5;
            while( i-- > 0){
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try{
                    Thread.sleep(500);
                } catch (InterruptedException ie){
                }
            }
        }
    }

    public synchronized void isSyncA() {
        int i = 5;
        while( i-- > 0){
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException ie){
            }
        }
    }

    public  synchronized void isSyncB(){
        int i = 5;
        while( i-- > 0){
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            }catch (InterruptedException ie){
            }
        }
    }


    public static synchronized void cSyncA(){
        int i = 5;
        while( i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try{
                Thread.sleep(500);
            }catch (InterruptedException ie){
            }
        }
    }
    public static synchronized void cSyncB() {
        int i = 5;
        while( i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try{
                Thread.sleep(500);
            }catch (InterruptedException ie){
            }
        }
    }
    }
