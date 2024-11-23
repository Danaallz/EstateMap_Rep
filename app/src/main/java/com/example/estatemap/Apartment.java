package com.example.estatemap;

public class Apartment {
    private  double price;
    private  String imageURL;
    private int rooms;
    private String location;
    private double area;
    private double age;
    private String type;
    private double rate;
    private String Classification;


    public Apartment(String imageURL, double price, String location, double rate,String Classification) {
        this.imageURL = imageURL;
        this.price = price;
        this.location = location;
        this.rate = rate; // Initialize rate
        this.Classification=Classification;
    }

    public Apartment( String imageURL) {
        this.price = price;
        this.imageURL = imageURL;
    }
    public Apartment(String imageURL, double price, String location, double rate) {
        this.imageURL = imageURL;
        this.price = price;
        this.location = location;
        this.rate = rate; // Initialize rate
        this.Classification=Classification;
    }

    public Apartment(double price, String imageURL, int rooms, String location, double area, int age, String type, String Classification) {
        this.price = price;
        this.imageURL = imageURL;
        this.rooms = rooms;
        this.location = location;
        this.area = area;
        this.age = age;
        this.type = type;
        this.Classification = Classification;

    }

    public Apartment(Double price, String imageURL, String classification) {
        this.price = price;
        this.imageURL = imageURL;
        this.Classification = Classification;
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

    public double getRate() {

        return rate;
    }

    public String getClassification() {
        return Classification;
    }

}
