package com.example.estatemap;

public class Apartment {
    private  double price;
    private  String imageURL;
    public Apartment(){

    }

    public Apartment(double price, String imageURL) {
        this.price = price;
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }
}
