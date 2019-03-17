package lelar.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class TableReader {

    private Map<Integer, Object[]> map = new HashMap<Integer, Object[]>();

    private Scanner scanner = null;

    private int id = 1;

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

        map.put(id++, transformToCorrectRow(stringRow));
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

    Map<Integer, Object[]> getMap() {
        return map;
    }
}