package com.lelar;

import com.lelar.services.Service;
import com.lelar.tables.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class TablesManager {
    private static final String PATH = "C:\\Users\\Lelar\\Desktop\\Java\\dbwork\\src\\main\\resources";
    private final static String FILE_FORMAT = "\\w+.csv";

    private static ArrayList<String> tableNames = new ArrayList<String>();

    // public static EntityManager em = Persistence.createEntityManagerFactory("postgres").createEntityManager();

    private static ApplicationContext context = new AnnotationConfigApplicationContext("com.lelar.tables");

    public TablesManager() {
    }

    static void importTables() {
        try {
            String fileName = "Clients.csv";
            {
                Service<Client> clientService = new Service<Client>(Client.class);

                Client client = new Client();

                for (Object[] row : getRows(fileName)) {
                    client.setId((Integer) row[0]);
                    client.setName(row[1].toString());
                    client.setSurname(row[2].toString());
                    client.setPatronymic(row[3].toString());
                    clientService.add(client);
                }

                clientService.end();
            }
            fileName = "Products.csv";
            {
                Service<Product> productService = new Service<Product>(Product.class);

                Product product = new Product();

                for (Object[] row : getRows(fileName)) {
                    product.setId((Integer) row[0]);
                    product.setName(row[1].toString());
                    productService.add(product);
                }

                productService.end();
            }
            fileName = "Baskets.csv";
            {
                Service<Basket> basketService = new Service<Basket>(Basket.class);

                Basket basket = new Basket();

                for (Object[] row : getRows(fileName)) {
                    basket.setClientId((Integer) row[0]);
                    basket.setProductId((Integer) row[1]);
                    basketService.add(basket);
                }

                basketService.end();
            }
            fileName = "Categories.csv";
            {
                Service<Category> categoryService = new Service<Category>(Category.class);

                Category category = new Category();

                for (Object[] row : getRows(fileName)) {
                    category.setId((Integer) row[0]);
                    category.setName(row[1].toString());
                    categoryService.add(category);
                }

                categoryService.end();
            }
            fileName = "Parameters.csv";
            {
                Service<Parameters> parametersService = new Service<Parameters>(Parameters.class);

                Parameters parameters = new Parameters();

                for (Object[] row : getRows(fileName)) {
                    parameters.setProductId((Integer) row[0]);
                    parameters.setCategoryId((Integer) row[1]);
                    parameters.setValue((Integer) row[2]);
                    parametersService.add(parameters);
                }

                parametersService.end();
            }
            fileName = "Price.csv";
            {
                Service<Price> priceService = new Service<Price>(Price.class);

                Price price = new Price();

                for (Object[] row : getRows(fileName)) {
                    price.setId((Integer) row[0]);
                    price.setProductId((Integer) row[1]);
                    price.setDate(row[2].toString());
                    price.setCost((Integer) row[3]);
                    priceService.add(price);
                }

                priceService.end();
            }
            fileName = "Orders.csv";
            {
                Service<Order> priceService = new Service<Order>(Order.class);

                Order price = new Order();

                for (Object[] row : getRows(fileName)) {
                    price.setId((Integer) row[0]);
                    price.setClientId((Integer) row[1]);
                    price.setProductId((Integer) row[2]);
                    price.setDate(row[3].toString());
                    priceService.add(price);
                }

                priceService.end();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Object[]> getRows(String fileName) throws FileNotFoundException {
        return new TableReader(PATH + "\\" + fileName).getList();
    }

    public static ArrayList<String> getTableNames() {
        return tableNames;
    }

}
