package com.owner.me.mytest;

public class InnerClassTest {

    private int anInt;

    private static int bInt = 100;

    public static void main(String[] args){
        //anonymity test
        System.out.println(bInt);

        System.out.println(StaticInnerClass.b);

        InnerClassTest test = new InnerClassTest();
        test.getInnerBellow(new InnerClassTest() {
            @Override
            public void bellow() {
                System.out.println("inner bellow");
            }
        });
    }

    public static class StaticInnerClass{
        static int b = 3;
        int a = bInt;
    }

    class InnerClassV1{
        private int a;

    }

    public void bellow(){
        System.out.println("main bellow");
    }

    private void getInnerBellow(InnerClassTest innerClassTest){
        innerClassTest.bellow();
    }
}
