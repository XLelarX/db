package com.lelar.tables;

import javax.persistence.*;

@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name")
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
