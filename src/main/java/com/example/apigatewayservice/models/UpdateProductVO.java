package com.example.apigatewayservice.models;

public class UpdateProductVO {

    private int user;

    private int product;

    private int amountOfNewProducts;

    public UpdateProductVO() {
    }

    public UpdateProductVO(int user, int product, int amountOfNewProducts) {
        this.user = user;
        this.product = product;
        this.amountOfNewProducts = amountOfNewProducts;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getAmountOfNewProducts() {
        return amountOfNewProducts;
    }

    public void setAmountOfNewProducts(int amountOfNewProducts) {
        this.amountOfNewProducts = amountOfNewProducts;
    }
}
