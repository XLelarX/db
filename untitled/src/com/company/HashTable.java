package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class HashTable implements Hash{
    private HashMap<Integer, String> map;
    private LinkedList<Node> list;
    private int cnt = 0;


    HashTable() {
        map = new HashMap<>();
        list = new LinkedList<>();
    }

    public void add(String str) {
        int h = hash();
        if (!this.contains(h)) {
            map.put(h, str);
        }
        else {
            list.add(new Node(h, str));
        }

    }

    private int hash() {
        return cnt++%10;
    }

    public void write() {
        for(Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.printf("Key: %d Item: %s\n", entry.getKey(), entry.getValue());
        }

        System.out.println();

        for(Node element : list) {
            System.out.printf("Key: %d Item: %s\n", element.getKey(), element.getValue());
        }
    }

    public String search(String str) {
        for(Map.Entry<Integer, String> entry : map.entrySet()) {
            if (str.equals(entry.getValue()))
                return "Find it: Key: " + entry.getKey() + " Item: " + entry.getValue();
        }
        for(Node element : list) {
            if (str.equals(element.getValue()))
                return "Find it: Key: " + element.getKey() + " Item: " + element.getValue();
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