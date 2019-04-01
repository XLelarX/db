package com.lelar.tables;

import com.lelar.services.Service;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "clientId")
    private Client clientId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "productId")
    private Product productId;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProductId(long productId) {
        Service<Product> sp = new Service<Product>(Product.class);
        this.productId = sp.getId(productId);
        sp.end();
    }

    public void setClientId(long clientId) {
        Service<Client> sp = new Service<Client>(Client.class);
        this.clientId = sp.getId(clientId);
        sp.end();
    }


    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}