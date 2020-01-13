package com.owner.me.mytest;

public class FinallyTest {
    public static void main(String[] args) {
        System.out.println(tryCatchFinallyTest());
    }

    static int tryCatchFinallyTest(){
        try {
            System.out.println("try block");
            return 1;

        }catch (Exception ex){
            System.out.println("exception");
            return 2;
        }finally {
            System.out.println("finally");
            //return 3;
        }
    }
}
