package com.example.lostandfound.classes;

public class lostItem {
    String type;
    String location;
    String description;
    String claimerID;

    public lostItem(String type, String location, String description, String claimerID) {
        this.type = type;
        this.location = location;
        this.description = description;
        this.claimerID = claimerID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClaimerID() {
        return claimerID;
    }

    public void setClaimerID(String claimerID) {
        this.claimerID = claimerID;
    }


}
