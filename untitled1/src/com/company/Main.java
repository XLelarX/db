package com.company;

class A{
    public static String app(String s) {
        return s + " suck";
    }
    public static String add(String s) {
        return app(s);
    }
}

interface D{
    int d = 11;

    int getD();
}

abstract class B{
    static int a = 10;

    abstract int gain2();

    static int gain(){
        return a;
    }
}

class C extends B{

    @Override
    int gain2() {
        return 0;
    }
}


public final class Main implements D{



    int gain2(){
        return 2;
    }

    static final String x = "10";

    public static String add(String s){
        return s + " fuck";
    }

    public static void main(String... args) {
        System.out.println(D.d);
//        {
//            B a = new Main();
//            System.out.println(gain() + "\n");
//            System.out.println(x);
//        }
        //String s = GHH.add("ass");
        //System.out.println(s);
        int i = 0;
        int[]a = new int[]{};
        //Depth[] d = new Depth[10];
        //System.out.println(d[1].g);

//        System.out.println(s);

    }

    @Override
    public int getD() {
        return d;
    }
}
