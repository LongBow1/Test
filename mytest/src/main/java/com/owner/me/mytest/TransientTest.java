package com.owner.me.mytest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TransientTest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private transient String transName;

    public TransientTest(String name, String transName){
        this.name = name;
        this.transName = transName;
    }

    @Override
    public String toString() {
        return "TransientTest{" +
                "name='" + name + '\'' +
                ", transName='" + transName + '\'' +
                '}';
    }

    private void writeObject(ObjectOutputStream s) throws IOException{
        s.defaultWriteObject();
        s.writeObject(transName);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        transName = String.valueOf(s.readObject());
    }

}
