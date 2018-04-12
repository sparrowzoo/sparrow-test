package com.sparrow.jdk.volatilekey;


/*
 * 使用汇编语言来验证volatile
 *
 * @author harry
 * @since 2016/12/17
 *
 * @params java -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:-Inline -XX:CompileCommand=print *AssemblyTest.test
 *
 */
public class Test {
    static User user=new User(100);

    public static void main(String[] args) {
        test();
    }

    public static void test() {
       user.setAge(user.getAge()+1);
    }
}
