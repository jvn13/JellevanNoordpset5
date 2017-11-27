package com.example.jelle.jellevannoord_pset5;

/**
 * Created by jelle on 27-11-2017.
 */

public class Dish {

    private String category;
    private String description;
    private int price;
    private String image;
    private int id;
    private String name;

    public Dish(String name) {
        this.name = name;
    }

    public Dish(int id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public Dish(String category, String description, int price, String image, int id, String name) {
        this.category = category;
        this. description = description;
        this.price = price;
        this.image = image;
        this.id = id;
        this.name = name;
    }

    public String getDescription() {
        return description;
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
