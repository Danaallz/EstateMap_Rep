package com.example.estatemap;

public class Apartment {
    private  double price;
    private  String imageURL;
    private int rooms;
    private String location;
    private double area;
    private double age;
    private String type;
    public Apartment(){

    }

    public Apartment( String imageURL) {
        //this.price = price;
        this.imageURL = imageURL;
    }
    public Apartment(double price, String imageURL, int rooms, String location, double area, int age, String type) {
        this.price = price;
        this.imageURL = imageURL;
        this.rooms = rooms;
        this.location = location;
        this.area = area;
        this.age = age;
        this.type = type;
    }

    public int getRooms() {
        return rooms;
    }

    public String getLocation() {
        return location;
    }

    public double getArea() {
        return area;
    }

    public double getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;

    }

}
