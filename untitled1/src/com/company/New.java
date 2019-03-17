package com.company;


public class New {
    public static void main(String... args) throws Exception {
        New n = new New();
        n.method1();
    }

    public void method1() throws Exception {
        method2();
    }

    void method2() throws Exception {
        throw new Exception();
    }
}
