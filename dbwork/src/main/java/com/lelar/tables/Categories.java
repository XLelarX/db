package com.lelar.tables;

import javax.persistence.*;

@Entity
@Table(name = "categories")

public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    public Categories(String name) {
        this.name = name;
    }

    public Categories() {
    }

    public String getName() {
        return name;
    }

    public void setName(String fullname) {
        this.name = fullname;
    }


}
