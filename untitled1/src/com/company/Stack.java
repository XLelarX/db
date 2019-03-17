package com.company;

import java.util.*;

public class Stack<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T element) {
        list.addFirst(element);
    }

    public T pop() {
        return list.getFirst();
    }

//    @Override
//    public String toString() {
//        return "Stack{" +
//                "list=" + list +
//                '}';
//    }

    public static void main(String... args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        System.out.println(stack.pop());
        stack.push(2);
        System.out.println(stack.pop());

    }
}
