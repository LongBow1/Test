package com.owner.me.mytest;

public class AnonymousClass {
    /*abstract static class Person{
        public abstract void eat();
    }*/

    public static void main(String[] args) {
        new Person(){
            public void eat(){
                System.out.println("eating");
            }
        }.eat();
    }
}
