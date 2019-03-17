package com.company;

class Node {
    private Integer key;
    private String value;


    Node(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    Integer getKey() {
        return key;
    }

    void setKey(Integer key) {
        this.key = key;
    }

    String getValue() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }
}
