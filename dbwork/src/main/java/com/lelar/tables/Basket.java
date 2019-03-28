package com.lelar.tables;

import javax.persistence.*;

@Entity
@Table(name = "basket")


public class Basket {
    @Id

    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@OneToOne(optional = true, mappedBy = "id")
    // @Column(name = "id", updatable = false)

    //private Client clientId;
}
