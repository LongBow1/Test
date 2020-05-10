package com.owner.me.mytest;

public class StringIntern {
    public static void main(String[] args) {
        String str1 = new StringBuilder("computer").append(" software ").toString();
        System.out.println(str1.intern() == str1);

        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("jav").append("a").toString();
        System.out.println(str2.intern() == str2);
    }
}
