package com.owner.me.mytest;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args){
        ReentrantLock reentrantLock = new ReentrantLock();
        LockSupport lockSupport = (LockSupport) LockSupport.getBlocker(Thread.currentThread());
    }
}
