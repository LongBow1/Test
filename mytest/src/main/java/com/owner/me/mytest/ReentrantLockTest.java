package com.owner.me.mytest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args){
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
        synchronized (Lock.class){

        }
        LockSupport lockSupport = (LockSupport) LockSupport.getBlocker(Thread.currentThread());
    }
}
