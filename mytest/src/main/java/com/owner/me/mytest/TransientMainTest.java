package com.owner.me.mytest;

import java.io.*;

public class TransientMainTest {
    public static void main(String[] args) {
        new String();
        String name = "normal name";
        String transientName = "transient name";
        TransientTest transientTest = new TransientTest(name,transientName);
        String filePath = "D:TransientTest.txt";
        String name1="常规属性",name2="transient修饰的属性";
        TransientTest test = new TransientTest(name1, name2);
        TransientTestV2 transientTestV2 = new TransientTestV2(name1,name2);
        System.out.println("序列化前："+transientTestV2.toString());
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        try {
            outStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outStream.writeObject(transientTestV2);
            inStream = new ObjectInputStream(new FileInputStream(filePath));
            TransientTestV2 readObject = (TransientTestV2)inStream.readObject();
            System.out.println("序列化后："+readObject.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("serializable:"+transientTest.toString());
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        try{
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            objectOutputStream.writeObject(test);
            objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
            TransientTest transientTest1 = (TransientTest) objectInputStream.readObject();
            System.out.println("after serializable:"+transientTest1.toString());
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static class TransientTest implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private transient String transName;

        public TransientTest(String name, String transName){
            this.name = name;
            this.transName = transName;
        }

        @Override
        public String toString() {

            return String.format("TransientTest.toString(): name1=%s,name2=%s", name, transName);

        }

    }
}
