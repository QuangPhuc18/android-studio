package com.example.myapplication;

import java.text.NumberFormat;
import java.util.Locale;

public class Product {
    private final String id;
    private final String name;
    private final double price;
    private final double originalPrice;
    private final int imageResource;
    private final float rating;
    private final int soldCount;
    private final String description;

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

    // Utility methods for formatted price
    public String getFormattedPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(price);
    }

    public String getFormattedOriginalPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(originalPrice);
    }
}
