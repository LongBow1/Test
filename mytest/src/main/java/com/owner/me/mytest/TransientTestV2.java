package com.owner.me.mytest;

import java.io.Serializable;

public class TransientTestV2 implements Serializable {
    private static final long serialVersionUID = 233858934995755239L;
    private String name1;
    private transient String name2;

    public TransientTestV2(String name1,String name2){
        this.name1 = name1;
        this.name2 = name2;
    }
    public String toString(){
        return String.format("TransientTest.toString(): name1=%s,name2=%s", name1,name2);
    }

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
        s.defaultWriteObject();
        s.writeObject(name2);
    }
    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        name2=String.valueOf(s.readObject());
    }
}
