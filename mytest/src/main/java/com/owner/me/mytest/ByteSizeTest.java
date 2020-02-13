package com.owner.me.mytest;

import org.omg.CORBA.Object;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ByteSizeTest {

    static int getByteSize(List<?> datas){
        int byteSize = 0;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(datas);
            objectOutputStream.close();
            byteSize = byteArrayOutputStream.size();
            System.out.println("getByteSize for List: "+byteSize + " bytes");
            byteArrayOutputStream.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return byteSize;
    }

    static int getByteSize(Object object){
        int byteSize = 0;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            byteSize = byteArrayOutputStream.size();
            System.out.println("getByteSize for object:"+byteSize+" bytes");
            byteArrayOutputStream.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return byteSize;
    }

    public static void main(String[] args){
        Integer n = 2000;
        List<Integer> integerList = new LinkedList<>();
        List<Integer> arrayList = new ArrayList<>();
        for(int i=0;i<n;i++){
            integerList.add(i);
            arrayList.add(i);
        }
        getByteSize(integerList);
        getByteSize(arrayList);
        //getByteSize();
    }
}
