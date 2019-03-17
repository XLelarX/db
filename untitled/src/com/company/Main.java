package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static Hash hashTable;

    public static void main(String[] args) {

        lable1:
        while (true) {
            System.out.println("1.Рехэширование с использованием псевдослучайных чисел\n2.Простой список");
            System.out.print("Enter number of command :");
            switch (in.nextInt()) {
                case 1:
                    hashTable = new RandomHashTable();
                    break lable1;
                case 2:
                    hashTable = new HashTable();
                    break lable1;
            }
        }

        try (FileReader reader = new FileReader("Data.txt")) {
            BufferedReader br = new BufferedReader(reader);
            String str = br.readLine();
            while (str != null) {
                hashTable.add(str);
                System.out.println(str);
                str = br.readLine();
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

        lable2:
        while (true) {
            System.out.println("1.Вывести таблицу\n2.Поиск введенного элемента\n3.Выход");
            System.out.print("Enter number of command :");

            switch (in.nextInt()) {
                case 1:
                    hashTable.write();
                    break;
                case 2:
                    System.out.println("Enter identificator:");
                    System.out.println(hashTable.search(in.next()));
                    break;
                case 3:
                    break lable2;
            }
        }

    }
}
