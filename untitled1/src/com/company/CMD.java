package com.company;

import java.util.Formatter;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class ExRunnnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.format("%d ", i);
            Thread.yield();
        }
        System.out.println("\n");
    }
}


public class CMD {

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
//        {
//            System.out.println(new CMD());
//
//            int d = 10;
//
//            System.out.printf("%15.1s", "asd");
//
//            String s = "asd";
//
//            System.out.println();
//            System.out.println("asddsa".matches("(asd|dsa)(asd|dsa)"));
//
//            String string = "asdasdasddsadsadsa";
//
//            Pattern pattern = Pattern.compile(string);
//            System.out.println(pattern);
//            Matcher matcher = pattern.matcher(string);
//        }
        {
            ExRunnnable exRunnnable = new ExRunnnable();
            exRunnnable.run();
        }


        {
            for (int i = 0; i < 3; i++)
                new Thread(new ExRunnnable()).start();
        }


        {
            ExecutorService executorService = Executors.newCachedThreadPool();
            for (int i = 0; i < 3; i++)
                executorService.execute(new ExRunnnable());
            executorService.shutdown();
        }

        {

        }
//        ExecutorService executorService = Executors.newCachedThreadPool();


        //System.out.println(matcher.find();

//        String mango = "mango";
//        String mango2 = "mango2";
//
//
//
//
//        String s = "abc" + mango + "def" + 47;
//        System.out.println(s);
    }
}
