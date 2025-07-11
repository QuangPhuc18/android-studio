package com.example.myapplication;

public class Product {
    private String name;
    private double price;
    private String imageUrl;
    private String description;
    private float rating;

    private String category;

    // ✅ Constructor dùng cho API (đầy đủ thông tin)
    public Product(String name, double price, String imageUrl, String description, float rating) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.rating = rating;

    }

    // ✅ Constructor dùng cho giỏ hàng (chỉ cần name, price, imageUrl)
    public Product(String name, double price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = "";     // gán mặc định nếu không có
        this.rating = 0.0f;        // gán mặc định nếu không có
    }

    // ✅ Getter
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }


}
