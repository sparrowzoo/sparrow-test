package com.sparrow.jdk.refer;

import java.util.ArrayList;
import java.util.List;

public class OomTest {
    public static void main(String[] args) {
        List<byte[]> byteArray = new ArrayList<>();
        while (true) {
//            try {
                byte bytes[] = new byte[1024 * 1024 * 1024];
                byteArray.add(bytes);
//            } catch (OutOfMemoryError e) {
//                e.printStackTrace();
//            }
            System.out.println("hi");
        }
    }
}
