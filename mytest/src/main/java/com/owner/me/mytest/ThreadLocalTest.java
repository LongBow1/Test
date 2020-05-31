package com.owner.me.mytest;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

public class ThreadLocalTest {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    ThreadLocal local = new ThreadLocal();
    Thread t = Thread.currentThread();
    Class<? extends Thread> clz = t.getClass();
    Field field;

    {
        try {
            field = clz.getDeclaredField("threadLocals");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
