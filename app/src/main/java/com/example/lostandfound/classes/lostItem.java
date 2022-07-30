package com.example.lostandfound.classes;

import java.io.Serializable;

public class lostItem implements Serializable{
    String type;
    String location;
    String description;
    String UUID;
    String claimerID;
    String dateTime;

    public lostItem() {

    }
    public lostItem(String type, String location, String description, String UUID, String claimerID, String dateTime) {
        this.type = type;
        this.location = location;
        this.description = description;
        this.UUID = UUID;
        this.claimerID = claimerID;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

}
