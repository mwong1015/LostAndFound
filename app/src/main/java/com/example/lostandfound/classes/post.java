package com.example.lostandfound.classes;

public class post {

    String ownerID;
    String lastSeenLocation; // last seen location/ lost location stated by the owner
    String description;
    String location; // actual location of the lost item input by another user

    public post(String ownerID, String lastSeenLocation, String description, String location) {
        this.ownerID = ownerID;
        this.lastSeenLocation = lastSeenLocation;
        this.description = description;
        this.location = location;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
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


}
