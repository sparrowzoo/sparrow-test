package com.sparrow.cg;

import com.sparrow.classloader.JarClassLoader;
import com.sparrow.concurrent.SparrowThreadFactory;
import com.sparrow.cryptogram.Base64;
import com.sparrow.support.EnvironmentSupport;

import java.net.URLClassLoader;

/**
 * Created by harry on 2018/2/27.
 */
public class JarClassLoaderTest {
    public static void main(String[] args) {
        String path = EnvironmentSupport.getInstance().getApplicationSourcePath();
        String jar = path + "/target/sparrow-test.war";
        URLClassLoader classLoader = new JarClassLoader(jar);
        try {
            Class clazz= classLoader.loadClass("com.sparrow.cg.Demo");
            System.out.println(clazz.getClassLoader());
            try {
                System.out.println(clazz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Class clazz2=Demo.class;

            try {
                System.out.println(clazz2.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(clazz==clazz2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
