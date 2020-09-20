package com.example.apigatewayservice.models;

public class OrdersProductVO {

    private int id;

    private String name;

    private int amountOfOrderedProducts;

    public OrdersProductVO() {
    }

    public OrdersProductVO(int id, int amountOfOrderedProducts) {
        this.id = id;
        this.amountOfOrderedProducts = amountOfOrderedProducts;
    }

    public OrdersProductVO(int id, String name, int amountOfOrderedProducts) {
        this.id = id;
        this.name = name;
        this.amountOfOrderedProducts = amountOfOrderedProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmountOfOrderedProducts() {
        return amountOfOrderedProducts;
    }

    public void setAmountOfOrderedProducts(int amountOfOrderedProducts) {
        this.amountOfOrderedProducts = amountOfOrderedProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
