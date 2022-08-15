package ru.shikhov.product;

public class Product {
    private int id;
    private String title;
    private int cost;

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }
}
