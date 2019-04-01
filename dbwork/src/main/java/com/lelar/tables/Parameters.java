package com.lelar.tables;

import com.lelar.services.Service;

import javax.persistence.*;

@Entity
@Table(name = "parameters")
@IdClass(ParametersRow.class)
public class Parameters {

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "productId")
    private Product productId;

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "categoryId")
    private Category categoryId;

    @Column(name = "value")
    private long value;

    public Product getProductId() {
        return productId;
    }

    public Category getCategoryId() {
        return categoryId;
    }


    public void setProductId(long productId) {
        Service<Product> sp = new Service<Product>(Product.class);
        this.productId = sp.getId(productId);
        sp.end();
    }

    public void setCategoryId(long categoryId) {
        Service<Category> sc = new Service<Category>(Category.class);
        this.categoryId = sc.getId(categoryId);
        sc.end();
    }

//
//    public long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(long productId) {
//        this.productId = productId;
//    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

}
