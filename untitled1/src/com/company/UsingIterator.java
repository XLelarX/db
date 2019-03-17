package com.company;

import java.util.*;

class AA {
    int cs;

    AA() {
    }

    AA(int cs) {
        this.cs = cs;
    }

    @Override
    public String toString() {
        return "AA{" +
                "cs=" + cs +
                '}';
    }

}


public class UsingIterator {
    public static void main(String... args) {
        List<AA> list = new LinkedList<>(Arrays.asList(new AA(1), new AA(2), new AA(3)));
        System.out.println(list);
        ListIterator<AA> literator = list.listIterator();

//        while(literator.hasNext()) {
//            literator.previous();
//            System.out.println(literator.next());
//        }

        Iterator<AA> iterator = list.iterator();

        System.out.println(((LinkedList<AA>) list).getFirst().getClass());

        //AA cs;

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        iterator.remove();
        System.out.println(list);

    }
}
