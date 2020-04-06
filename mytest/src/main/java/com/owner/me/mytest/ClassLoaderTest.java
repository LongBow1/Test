package com.owner.me.mytest;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (IOException e){
                    throw new ClassNotFoundException(name);
                }
            }

        };
        Object obj = null;
        try {
            obj = myLoader.loadClass("com.owner.me.mytest.ClassLoaderTest").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(obj.getClass());
        System.out.println(Object.class.getClassLoader());
        System.out.println(obj);
        System.out.println(obj instanceof com.owner.me.mytest.ClassLoaderTest);

        ClassLoader myLoaderV2 = new ClassLoader() {
            @Override
            public Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (IOException e){
                    throw new ClassNotFoundException(name);
                }
            }

        };
        try {
            obj = myLoaderV2.loadClass("com.owner.me.mytest.ClassLoaderTest").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(obj.getClass());
        System.out.println(Object.class.getClassLoader());
        System.out.println(obj);
        System.out.println(obj instanceof com.owner.me.mytest.ClassLoaderTest);

        ClassLoader c = ClassLoaderTest.class.getClassLoader();
        ClassLoader c1= c.getParent();
        ClassLoader c2 = c1.getParent();
        System.out.println(c);
        System.out.println(c1);
        System.out.println(c2);
    }
}
