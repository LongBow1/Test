package com.owner.me.mytest;

import java.text.MessageFormat;

public class FormatTest {
    static String mstr = "{0},{2},{1},{4}";

    {
        System.out.println("test");
    }

    public static void main(String[] args) {
        System.out.println(MessageFormat.format(mstr,"00","11","22"));
    }
}
