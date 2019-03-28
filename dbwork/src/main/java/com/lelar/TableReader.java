package com.lelar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class TableReader {

    private List<Object[]> list = new LinkedList<Object[]>();

    private Scanner scanner;

    TableReader(String path) throws FileNotFoundException {
        scanner = new Scanner(new File(path));

        readTableToMap();
    }

    private void readTableToMap() {
        while (scanner.hasNextLine())
            fillRow();
    }

    private void fillRow() {
        String[] stringRow = scanner.nextLine().split(";");

        list.add(transformToCorrectRow(stringRow));
    }

    private Object[] transformToCorrectRow(String[] row) {
        int quantityOfArguments = row.length;
        Object[] arguments = new Object[quantityOfArguments];

        for (int i = 0; i < quantityOfArguments; i++) {
            String cell = row[i];

            if (cell.matches("\\d+"))
                arguments[i] = Integer.parseInt(cell);
            else
                arguments[i] = cell;
        }
        return arguments;
    }

    List<Object[]> getList() {
        return list;
    }

}