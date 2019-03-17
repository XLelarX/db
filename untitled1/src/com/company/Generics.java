package com.company;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class H {
    public String h;

    public H() {
        System.out.println("H()");
    }
}

class G {
    public int g;
}


class Generic<A, B> {
    private A a;
    private B b;

    Generic(A a, B b) {
        this.a = a;
        this.b = b;
    }

    Generic() {
    }

    @Override
    public String toString() {
        return "Generic{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public void setA(A a) {
        this.a = a;
    }

    public void setB(B b) {
        this.b = b;
    }
}

class Generic2<A, B, C> extends Generic<A, B> {
    private C c;

    @Override
    public String toString() {
        return "Generic2{" +
                "c=" + c +
                '}';
    }
}


class P<T> {
    private T t;

    P(T t) {
        this.t = t;
        System.out.println("P() " + t.getClass().getSimpleName());
    }

}


public class Generics {
    public static void main(String... args) {
        H h = new H();
        Generic<H, G> g = new Generic2<H, G, H>();
        System.out.println(Arrays.toString(g.getClass().getTypeParameters()));

        List<H> lH = new LinkedList<>();
        P<H> p = new P<>(h);


//        Generic<Integer, String> g = new Generic<>(123, "asd");
//        g.setA(1);
//        g.setB("a");
//        System.out.println(g.toString());
    }
}
