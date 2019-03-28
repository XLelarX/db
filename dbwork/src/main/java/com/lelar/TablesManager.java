package com.lelar;

//import jdk.javadoc.internal.doclets.formats.html.markup.Table;

import com.lelar.services.*;
import com.lelar.tables.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class TablesManager {
    private static final String PATH = "C:\\Users\\Lelar\\Desktop\\Java\\dbwork\\src\\main\\resources";
    private final static String FILE_FORMAT = "\\w+.csv";

    private static ArrayList<String> tableNames = new ArrayList<String>();

    public static EntityManager em = Persistence.createEntityManagerFactory("postgres").createEntityManager();

    private static ApplicationContext context = new AnnotationConfigApplicationContext("com.lelar.tables");

    public TablesManager() {
    }

    static void importTables() {
        File directory = new File(PATH);

        try {
            for (File file : directory.listFiles()) {
                String fileName = file.getName();

                String name = fileName.replace(".csv", "");

                if (fileName.matches(FILE_FORMAT)) {

                    // new TableReader(PATH + "\\" + fileName).getList();


                    if (name.equals("Clients")) {
                        ClientService clientService = new ClientService();

                        Client client = new Client();
                        for (Object[] row : new TableReader(PATH + "\\" + fileName).getList()) {
                            for (Object cell : row) {
                                client.setFullName(cell.toString());
                                clientService.add(client);
                            }
                        }
                        clientService.end();
                    }

                    else if (name.equals("Products")) {
                        ProductService basketService = new ProductService();


                        Product product = new Product();
                        for (Object[] row : new TableReader(PATH + "\\" + fileName).getList()) {
                            for (Object cell : row) {
                                product.setName(cell.toString());
                                basketService.add(product);
                            }
                        }
                        basketService.end();
                    }

//                    else if (name.equals("Basket")) {
//                        BasketService basketService = new BasketService();
//
//
//                        Basket basket = new Basket();
//                        for (Object[] row : new TableReader(PATH + "\\" + fileName).getList()) {
//                            for (Object cell : row) {
//
//                               // basket.setFullName(cell.toString());
//                                basketService.add(basket);
//                            }
//                        }
//                        basketService.end();
//                    }

//                    else if (name.equals("Categories")) {
//                        CategoriesService clientService = new CategoriesService();
//
//                        Categories categories = new Categories();
//                        for (Object[] row : new TableReader(PATH + "\\" + fileName).getList()) {
//                            for (Object cell : row) {
//                                categories.setName(cell.toString());
//                                clientService.add(categories);
//                            }
//                        }
//                    }


                    System.out.println(name);
                    new TableReader(PATH + "\\" + fileName).getList();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getTableNames() {
        return tableNames;
    }

}
