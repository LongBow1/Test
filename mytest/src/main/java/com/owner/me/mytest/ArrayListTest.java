package com.owner.me.mytest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArrayListTest {

    static Integer bb2 = 20;
    static synchronized void test(){

    }

    public static void main(String[] args){
        int a = 10;
        System.out.println(a >> 1);

        Integer aa = 10;
        System.out.println(aa);

        synchronized (aa){

        }


        Arrays.asList();
        List<String> arr1 = new ArrayList<>();
        arr1.add("1");
        arr1.add("2");
        List<String> arr2 = new ArrayList<>();
        arr2.add("2");
        arr2.add("1");
        String[] arr1arr = new String[2];
        arr1.toArray(arr1arr);
        System.out.println(arr1arr);
        for (String s : arr1){
            if(s.equalsIgnoreCase("1")){
                arr1.remove(s);
            }
        }
        System.out.println(arr1);


        for (String s : arr2){
            if(s.equalsIgnoreCase("1")){
                arr2.remove(s);
            }
        }
        System.out.println(arr2);
    }
}
