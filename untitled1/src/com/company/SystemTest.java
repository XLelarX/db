package com.company;

import java.util.*;

public class SystemTest {
    public static void main(String... args) {
        Integer[] arr = {1,2,3,4,5,6,7,8,9,0};

        List<Integer> list = new ArrayList<>(Arrays.asList(arr));

        Collections.shuffle(list);
        System.out.println(list);
        System.out.println(Arrays.toString(arr));

//        for(Map.Entry entry: System.getenv().entrySet()){
//            System.out.println(entry.getValue());
//        }
    }
}
