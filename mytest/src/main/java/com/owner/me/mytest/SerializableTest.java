package com.owner.me.mytest;

import java.io.*;

public class SerializableTest{
    static class ObjectTest implements Serializable{
        private String name;
        private Integer id;
        private static Integer number = 1;
        private transient String tempSeria;

        public static Integer getNumber() {
            return number;
        }

        public static void setNumber(Integer number) {
            ObjectTest.number = number;
        }

        public String getTempSeria() {
            return tempSeria;
        }

        public void setTempSeria(String tempSeria) {
            this.tempSeria = tempSeria;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "ObjectTest{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", tempSeria='" + tempSeria + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        ObjectTest objectTest = new ObjectTest();
        objectTest.setId(111);
        objectTest.setName("testv1");
        objectTest.setTempSeria("serializable");
        System.out.println(objectTest.toString());
        System.out.println(ObjectTest.getNumber());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("serializable.txt"));
            objectOutputStream.writeObject(objectTest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serializable.txt"));
            ObjectTest o2 = (ObjectTest) objectInputStream.readObject();
            System.out.println(o2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
