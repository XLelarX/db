package com.company;

interface IntA {
    int a = 10;

    First getFirst();
}

class Second {
    private int fieldSecond = 2;

    private class B {
        private int ads = 10;

        Second getSecond() {
            return Second.this;
        }

        @Override
        public String toString() {
            return "B{" +
                    "ads=" + ads +
                    '}';
        }
    }

    B getB() {
        return new B();
    }

    @Override
    public String toString() {
        return "Second{" +
                "fieldSecond=" + fieldSecond +
                '}';
    }
}


public class First {

    private int fieldFirst = 1;


    private class A implements IntA {
        private int fieldA = 10;

        @Override
        public First getFirst() {
            return First.this;
        }
    }

    public First ship() {
        return new A().getFirst();
    }


    public static void main(String... args) {
        final int asd = 10;
        {
            First first = new First();
            First.A firstA = first.new A();
            System.out.println(firstA.fieldA);
        }

        {
            Second second = new Second();
            System.out.println(second.getB());
        }

        {
            Third third = new Third();
            System.out.println(third.getAnonymous());
        }

        {
            Third third = new Third();
            System.out.println(third.getAnonymousByInterface().getFirst().fieldFirst);
        }

        {
            Fourth fourth = new Fourth();
            Fourth.C fourthC = new Fourth.C();
        }

        {
            Fifth.InFifth fifthImp = new Fifth.InFifth();
            System.out.println(fifthImp.string);
        }

        {
            Sixth.ImpSixth impSixth = new Sixth.ImpSixth();
            impSixth.print("SIX");
        }

        {
            Seventh seventh = new Seventh();
            Seventh.A seventhA = seventh.new A();
            Seventh.A.B seventhB = seventhA.new B();
            seventhB.f();

            Seventh.A.B seventhAB = new Seventh().new A().new B();
            seventhAB.f();
        }


        {
            Super sub = new Sub();
            System.out.println(sub.getInt());
            System.out.println(((Sub) sub).getA());
        }

    }
}

class Third {
    int getAnonymous() {
        return new Second() {
            int i = 10;

            int getI() {
                return i;
            }

            @Override
            public String toString() {
                return "$classname{" +
                        "i=" + getI() +
                        '}';
            }
        }.getI();
    }

    IntA getAnonymousByInterface() {
        return new IntA() {
            @Override
            public First getFirst() {
                return new First();
            }
        };
    }
}

class Super {
    private int i = 1;

    int getInt() {
        return i;
    }
}

abstract class Super2 {
}

class Sub extends Super {
    int i = 2;
    int a = 10;


    int getA() {
        return a;
    }

    @Override
    int getInt() {
        return i;
    }


    class Realize extends Super2{

    }

    Super2 makeSuper2() {
        return new Super2() {
        };
    }
}

class Fourth {

    private static int fourthField = 4;

    static class C {
        C() {
            fourthField = getFourthField();
        }
    }

    public static int getFourthField() {
        return fourthField;
    }
}

interface Fifth {
    void print(String s);

    class InFifth {
        String string = "InFifth";
    }
}

interface Sixth {
    void print(String s);

    class ImpSixth implements Sixth {
        @Override
        public void print(String s) {
            System.out.println(s);
        }
    }
}

class Seventh {
    private void h() {
        System.out.println("h()");
    }

    class A {
        private void g() {
            System.out.println("g()");
        }

        class B {
            void f() {
                h();
                g();
                System.out.println("f()\n");
            }
        }
    }
}