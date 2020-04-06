package com.owner.me.mytest;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FinalTest {
    static {
        number = 1;
        System.out.println("number");
    }
    private static int number=-1;
    private int nu = 2;
    static {
        number = 100;
        String s = "sfds";
        s.toLowerCase();

        System.out.println(number);
    }



    public static void main(String[] args) {
        System.out.println(number);
        List<? extends Number> l = new ArrayList<Integer>();
        //l.add(0);

        /*JSONObject.toJSONString()
        JSONArray.toJSONString()*/
    }

    void test(){
        System.out.println(nu);
    }
}
