package com.company;

import java.util.ArrayList;
import java.util.List;

class A2 {

}


class A21 extends A2 {

}

interface A3 {

}


class SocialA<T extends A2 & A3> {

}


public class Edges {

    public static void main(String... args) {
        String s = "asd";
        System.out.println(s.toUpperCase());
    }

    List<A2> list = new ArrayList<>();
}
