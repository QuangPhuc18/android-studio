package com.example.myapplication;

import java.text.NumberFormat;
import java.util.Locale;

public class Product {
    private String id;
    private String name;
    private double price;
    private double originalPrice;
    private int imageResource;
    private float rating;
    private int soldCount;
    private String description;

    // ✅ Constructor đầy đủ thông tin (dùng ở ProductDetailActivity)
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

    // ✅ Constructor đơn giản (dùng khi chỉ lưu giỏ hàng)
    public Product(String name, double price, int imageResource) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
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

    // Utility methods
    public String getFormattedPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(price);
    }

    public String getFormattedOriginalPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(originalPrice);
    }
}
