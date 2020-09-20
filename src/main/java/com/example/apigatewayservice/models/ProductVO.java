package com.example.apigatewayservice.models;


public class ProductVO {

    private int id;

    private String name;

    private int price;

    private double mass;

    private double alcohol;

    private String picture;

    private int amount;

    public ProductVO() {
    }

    public ProductVO(int id) {
        this.id = id;
    }

    public ProductVO(int id, String name, int price, double mass, double alcohol, String picture, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mass = mass;
        this.alcohol = alcohol;
        this.picture = picture;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
