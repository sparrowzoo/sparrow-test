package com.sparrow.jdk.volatilekey;

import com.sparrow.tracer.Tracer;

/**
 * Created by TCLDUSER on 2018/4/9.
 */
public class User {
    private int age = 10;
    private int no=100;
    private byte[] bytes;
    private Tracer tracer;
    public User(int age){
        this.age=age;
    }
    public User(int age, byte[] bytes) {
        this.bytes = bytes;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Tracer getTracer() {
        return tracer;
    }

    public void setTracer(Tracer tracer) {
        this.tracer = tracer;
    }
}
