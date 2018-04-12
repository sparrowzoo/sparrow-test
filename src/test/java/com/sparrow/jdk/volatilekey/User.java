package com.sparrow.jdk.volatilekey;

/**
 * Created by TCLDUSER on 2018/4/9.
 */
public class User {
    private int age=10;

    public User(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
