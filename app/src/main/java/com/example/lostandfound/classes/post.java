package com.example.lostandfound.classes;

import java.io.Serializable;

public class post implements Serializable {

    String ownerEmail;
    String itemName;
    String lastSeenLocation; // last seen location/ lost location stated by the owner
    String description;
    String location; // actual location of the lost item input by another user
    String dateTime;
    String UUID;

    public post(){

    }

    public post(String ownerEmail, String itemName, String lastSeenLocation, String description, String location, String dateTime, String UUID) {
        this.ownerEmail = ownerEmail;
        this.itemName = itemName;
        this.lastSeenLocation = lastSeenLocation;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.UUID = UUID;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getLastSeenLocation() {
        return lastSeenLocation;
    }

    public void setLastSeenLocation(String lastSeenLocation) {
        this.lastSeenLocation = lastSeenLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }


}
