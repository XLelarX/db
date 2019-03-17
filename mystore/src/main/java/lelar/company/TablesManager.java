package lelar.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class TablesManager {
    private static final String PATH = "C:\\Users\\Lelar\\Desktop\\Java\\mystore\\src\\main\\resources";
    private final static String FILE_FORMAT = "\\w+.csv";

    private static ArrayList<String> tableNames = new ArrayList<String>();

    private static List<Map<Integer, Object[]>> tableList = new LinkedList<Map<Integer, Object[]>>();

    static void importTables() {
        File directory = new File(PATH);

        try {
            for (File e : directory.listFiles()) {
                String fileName = e.getName();

                tableNames.add(fileName.replace(".csv",""));

                if (fileName.matches(FILE_FORMAT)) {
                    tableList.add(new TableReader(PATH + "\\" + fileName).getMap());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Map<Integer, Object[]> getTable(int index) {
        return tableList.get(index);
    }

    public static ArrayList<String> getTableNames() {
        return tableNames;
    }
}
