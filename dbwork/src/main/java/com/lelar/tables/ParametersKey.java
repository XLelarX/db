package com.lelar.tables;

import java.io.Serializable;

public class ParametersKey implements Serializable {
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
}
