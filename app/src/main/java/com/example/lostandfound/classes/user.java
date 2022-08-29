package com.example.lostandfound.classes;

public class user {

    String id;
    String gmail;
    String name;

    public user(){

    }
    public user(String id, String gmail, String name) {
        this.id = id;
        this.gmail = gmail;
        this.name = name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }


}
