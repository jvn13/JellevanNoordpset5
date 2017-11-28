package com.example.jelle.jellevannoord_pset5;

public class Dish {

    private String category;
    private String description;
    private int price;
    private String image;
    private int id;
    private String name;

    public Dish(int id, int price, String name, String image) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
