package com.example.estatemap;


public class Property {
    private String name;
    private double price;
    private String type;

    public Property() {
        // Default constructor required for calls to DataSnapshot.getValue(Property.class)
    }

    public Property(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}