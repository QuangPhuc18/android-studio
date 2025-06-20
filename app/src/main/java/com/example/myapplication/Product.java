package com.example.myapplication; // Thay đổi package name theo project của bạn

public class Product {
    private String id;
    private String name;
    private double price;
    private double originalPrice;
    private int imageResource;
    private float rating;
    private int soldCount;
    private String description;

    // Constructor
    public Product(String id, String name, double price, double originalPrice,
                   int imageResource, float rating, int soldCount, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.originalPrice = originalPrice;
        this.imageResource = imageResource;
        this.rating = rating;
        this.soldCount = soldCount;
        this.description = description;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public int getImageResource() {
        return imageResource;
    }

    public float getRating() {
        return rating;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Utility methods
    public String getFormattedPrice() {
        return "₫" + String.format("%,.0f", price);
    }

    public String getFormattedOriginalPrice() {
        return "₫" + String.format("%,.0f", originalPrice);
    }
}