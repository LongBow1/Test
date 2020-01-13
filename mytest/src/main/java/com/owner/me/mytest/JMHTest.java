package com.owner.me.mytest;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JMHTest {

    static int testCount = 10000000;

    public static void main(String[] args) {
        testLinkList();
        testArrayList();
        testArrayListInitSize();

    }

    static void testArrayList(){
        long startTime = System.currentTimeMillis();
        List<Integer> integerList = new ArrayList<>();
        for (int i=0;i<testCount;i++){
            integerList.add(i);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    static void testArrayListInitSize(){
        long startTime = System.currentTimeMillis();
        List<Integer> integerList = new ArrayList<>(testCount);
        for (int i=0;i<testCount;i++){
            integerList.add(i);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    static void testLinkList(){
        long startTime = System.currentTimeMillis();
        List<Integer> integerList = new LinkedList<>();
        for (int i=0;i<testCount;i++){
            integerList.add(i);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
