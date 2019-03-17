package com.company;


//{CompileTimeError}
public class Main {
    static void print(String... args) {
        for (Object element : args)
            System.out.println(element);
        System.out.println(args.getClass());
    }

    public static void main(String... args) {
        for (Money element : Money.values()) {
//            System.out.println(element + "  " + element.ordinal());
            switch (element){
                case EUR:
                    System.out.println("Евро");
                    break;
                case GBP:
                    System.out.println("Фунт");
                    break;
                case JPY:
                    System.out.println("Йена");
                    break;
                case RUB:
                    System.out.println("Рубль");
                    break;
                case USD:
                    System.out.println("Доллар");
            }
        }
    }
}
