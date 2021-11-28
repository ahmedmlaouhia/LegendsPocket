package com.example.myapplication;

public class User {

    private String name;
    private String email;
    private String phone;

    public User(String name,String email,String phone){
        this.name=name;
        this.email=email;
        this.phone=phone;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }

}
