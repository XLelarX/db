package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

class RandomHashTable implements Hash{
    private HashMap<Integer, String> map;
    private LinkedList<Node> list;
    private static int cnt = 0;

    private static Random r = new Random();

    RandomHashTable() {
        map = new HashMap<>();
        list = new LinkedList<>();
    }

    public void add(String str) {
        int h = hash();
        if (!this.contains(h)) {
            map.put(h, str);
        }
        else {
            while (this.contains(h))
                h = rehash(h);
            map.put(h, str);
        }

    }

    private int hash() {
        return cnt++%10;
    }

    private int rehash(int h) {
        return (h + r.nextInt(Integer.MAX_VALUE - h)) % Integer.MAX_VALUE;
    }

    public void write() {
        for(Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.printf("Key: %d Item: %s\n", entry.getKey(), entry.getValue());
        }
    }

    public String search(String str) {
        for(Map.Entry<Integer, String> entry : map.entrySet()) {
            if (str.equals(entry.getValue()))
                return "Find it: Key: " + entry.getKey() + " Item: " + entry.getValue();
        }
        return "Element with Key: " + str + " is not exists";
    }

    private boolean contains(Integer i) {
        for(Map.Entry<Integer, String> entry : map.entrySet()) {
            if (i.equals(entry.getKey()))
                return true;
        }
        return false;
    }
}
