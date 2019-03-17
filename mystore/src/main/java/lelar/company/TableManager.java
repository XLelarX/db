package lelar.company;

import java.sql.*;
import java.util.Map;
import java.util.Scanner;

class TableManager {
    private String tableName;
    private Map<Integer, Object[]> table;
    private static Scanner scanner = new Scanner(System.in);

    private String[] nameList;

    TableManager(int index) {
        this.table = TablesManager.getTable(index);
        this.tableName = TablesManager.getTableNames().get(index);

        int quantityOfArguments = table.get(1).length + 1;

        nameList = new String[quantityOfArguments];

        for (int i = 0; i < quantityOfArguments; i++) {
            System.out.print(String.format("Enter name for column №%d = ", i + 1));
            nameList[i] = scanner.next();
        }
    }

    void fillTable(String firstArgument, String secondArgument) throws SQLException {
        for (int i = 1; i <= table.size(); i++) {
            Connector.getStatement().execute(String.format("INSERT INTO %s(%s, %s) VALUES (%d,'%s')", tableName, firstArgument, secondArgument, i, table.get(i)));
        }
    }

    void outputTable(String firstArgument, String secondArgument) throws SQLException {
        ResultSet resultSet = Connector.getStatement().executeQuery(String.format("SELECT * FROM %s", tableName));
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("fullname");
            System.out.format("%s = %d; %s = %s \n", firstArgument, id, secondArgument, name);
        }
    }


    void createTable() throws SQLException {

        StringBuilder request = new StringBuilder(String.format("CREATE TABLE IF NOT EXISTS %s(%s integer PRIMARY KEY, ", tableName, nameList[0]));

        for (int i = 1; i < nameList.length; i++) {
            request.append(nameList[i]);

            if (table.get(1)[i - 1].toString().matches("\\d+")) {
                request.append(" integer");
            } else {
                request.append(" text");
            }

            if (i != nameList.length - 1) {
                request.append(", ");
            }

        }
        request.append(");");


        System.out.println(request);

        Connector.getStatement().execute(request.toString());
    }

    void destroyTable() {
        try {
            Connector.getStatement().execute(String.format("DROP TABLE %s", tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
