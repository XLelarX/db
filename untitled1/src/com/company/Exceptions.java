package com.company;

class MyException extends Exception {
}

class MyException2 extends Exception {
}

class MyException3 extends MyException {
}


public class Exceptions {
//    public static void main(String... args) throws MyException, MyException2 {
//        try {
//            try {
//                throw new MyException();
//            } finally {
//                throw new MyException2();
//            }
//        } catch (MyException2 e) {
//            System.out.println(e);
//        }
//
//    }

    public static void main(String... args) {


//        try {
//            throw new MyException();
//        } finally {
//            return;
//        }


        try{
            throw new MyException3();
        }catch (MyException e) {
            System.out.println("MyException3");
        }
//        catch (MyException3 e) {
//            System.out.println("MyException");
//        }


    }

}

