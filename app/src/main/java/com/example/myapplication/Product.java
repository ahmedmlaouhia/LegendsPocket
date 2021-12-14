package com.example.myapplication;

public class Product {
    private String name ;
    private float price ;
    private String imageUrl;
    private String description;

    public Product(){

    }
    public Product(String id,String name,Float price,String imageUrl){
        this.name=name;
        this.price=price;
        this.imageUrl=imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
