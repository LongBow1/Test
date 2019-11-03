package com.owner.me.mytest;

import java.io.UnsupportedEncodingException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;

public class ReferenceTestDemo {

    static String testStr = "漫画编程";
    public static void main(String[] args){

        String strongReference = new String("strong reference");
        SoftReference<String> softReference = new SoftReference<>(new String("soft reference"));
        WeakReference<String> weakReference = new WeakReference<>(new String("weak reference"));
        PhantomReference<String> phantomReference = new PhantomReference<>(new String("phantom reference"), new ReferenceQueue<>());


        MethodAreaStaticProperties s = new MethodAreaStaticProperties("test");
        s.m = new MethodAreaStaticProperties("para");

        try {
            System.out.println(strongReference);
            System.out.println(softReference);
            System.out.println(weakReference.get());
            System.out.println(phantomReference.get());
            System.gc();
            System.out.println(strongReference);
            System.out.println(softReference.get());
            System.out.println(weakReference.get());
            System.out.println(phantomReference.get());

            System.out.println(s);
            System.out.println(s.m);
            s = null;
            System.out.println(s);
            System.out.println(s.m);

            System.out.println(new String(testStr.getBytes(Charset.forName("GBK")),"GBK"));
            //System.out.printf(new String(testStr.getBytes(Charset.forName("GBK")),"GBK18030"));
            System.out.println(new String(testStr.getBytes(Charset.forName("GBK")),"utf8"));
            System.out.println(new String(testStr.getBytes(Charset.forName("GBK")),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("hello ");
    }
}
