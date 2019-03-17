package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class O {



    private static void Init() {
        Text.Reset();
        if (!Text.ok)
            Error.Message(Text.message);
        Scan.init();
    }

    private static void Done() {
        Text.Close();
    }


    public static void main(String[] args) {
       // System.out.println(.e23);
       // System.out.println(Integer.MAX_VALUE + "     " + Long.MAX_VALUE);
       // System.out.println("Компилятор языка Java");
        //showoutFiles(convertHumanReadableFormatToRegex(args[0]));
        showoutFiles();

    }


    private static String convertHumanReadableFormatToRegex(String arg) {
        return arg.replaceAll("\\.", "\\*")
                .replaceAll("^\\*", "\\.");
    }

    private static void showoutFiles() {
       // File dir = new File(".");
       // File[] files = dir.listFiles((dir1, name) -> name.matches(fileMask));
        //assert files != null;
        //for (File file : files) {
            //File dir = new File("C:\\Users\\Lelar\\Desktop\\Java\\untitled1\\t.txt");
           // System.out.println(file.getName());
            Location.path = "C:\\Users\\Lelar\\Desktop\\Java\\untitled1\\t.txt";
            O.Init();
            Pars.Compile();
            O.Done();
       // }
    }
}
