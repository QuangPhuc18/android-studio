package com.example.myapplication; // Thay đổi package name theo project của bạn

public class Category {
    private String id;
    private String name;
    private int imageResource;

    // Constructor
    public Category(String id, String name, int imageResource) {
        this.id = id;
        this.name = name;
        this.imageResource = imageResource;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}