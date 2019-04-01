package com.lelar.tables;

import java.io.Serializable;

public class ParametersRow implements Serializable {
    private Product productId;
    private Category categoryId;


    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    //private long productId;
    //private long categoryId;

//    public long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(long productId) {
//        this.productId = productId;
//    }

//    public long getCategoryId() {
//        return categoryId;
//    }

//    public void setCategoryId(long categoryId) {
//        this.categoryId = categoryId;
//    }
}
