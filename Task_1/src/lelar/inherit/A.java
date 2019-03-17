package lelar.inherit;

public class A extends B {

    A(int i) {
        System.out.println("A constructor");
    }

    public static void main(String[] args) {
        A a = new A(10);
    }
}
