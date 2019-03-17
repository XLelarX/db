package lelar.company;

import java.sql.*;

public class Main {


    public static void main(String[] args) throws SQLException {
        Connector.initConnection();

        TablesManager.importTables();

        //System.out.println(TablesManager.getTable(1));

        int i = 2;
        TableManager tableManager = new TableManager(i);

        tableManager.createTable();

        //tableManager.destroyTable();
//        for (File e : listFiles) {
//            String fileName = e.getName();
//
//            if (fileName.matches(FILE_FORMAT)) {
//                Connector.initConnection();
//                System.out.println(e.getAbsolutePath() + "  " + fileName);
//
        // TableManager clientsTableManager = new TableManager(PATH + "\\Details.csv");

//                //clientsTableManager.destroyTable();
//
//                String argument1 = "id";
//                String argument2 = "fullname";
//
//                clientsTableManager.createTable(argument1, argument2);
//
//                clientsTableManager.fillTable(argument1, argument2);
//
//                clientsTableManager.outputTable(argument1, argument2);
//            }
//        }

    }
}
