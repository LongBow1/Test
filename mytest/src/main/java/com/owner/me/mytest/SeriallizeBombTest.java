package com.owner.me.mytest;

import com.sun.corba.se.spi.ior.ObjectKey;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SeriallizeBombTest {

    final static BombObject a = new BombObject();

    public static void main(String[] args) {

        System.out.println(a);
        String filePath = "serializeBombTest.txt";
        BombObject bombObject = new BombObject();
        //saveObject(bombObject,filePath);
        //Object bombObjectV2 = readObject(filePath);
        System.out.println(bombObject);
    }

    static class BombObject implements Serializable{
        private Set<Object> root;
        public BombObject(){
            root = new HashSet<>();
            Set<Object> s1 = new HashSet<>();
            Set<Object> s2 = new HashSet<>();
            System.out.println(s1 == s2);
            System.out.println(s1.equals(s2));
            System.out.println(s1.hashCode() == s2.hashCode());
            System.out.println(new HashSet<String>().hashCode() == new HashSet<Integer>().hashCode());
            root.add(s1);
            root.add(s2);
            for (int i =0; i< 1 ;i++){
                Set<Object> ts1 = new HashSet<>();
                Set<Object> ts2 = new HashSet<>();
                ts1.add("bomb");
                s1.add(ts1);
                s1.add(ts2);
                s2.add(ts1);
                s2.add(ts2);
                s1 = ts1;
                s2 = ts2;
            }
            s1.add("bomb1");
            s1.add("bomb2");
            s2.add("bomb1");
            s2.add("bomb2");
            System.out.println("reading...");
        }
    }

    static void saveObject(Object object, String filePath){
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            System.out.println("saving...");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
                objectOutputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    static Object readObject(String filePath){
        Object object = null;
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            System.out.println("reading...");
            return object;
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {

                fileInputStream.close();
                objectInputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return object;

    }
}
