package com.lelar.tables;

import javax.persistence.*;

@Entity
@Table(name = "clients")

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "fullname")
    private String fullName;

    public Client(String fullName) {
        this.fullName = fullName;
    }

    public Client() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullname) {
        this.fullName = fullname;
    }

   // @OneToOne(optional = true, cascade = CascadeType.ALL)
   // private Basket basket;
}