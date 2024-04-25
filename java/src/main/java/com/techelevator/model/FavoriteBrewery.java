package com.techelevator.model;

public class FavoriteBrewery {
    public int userId;
    public int breweryId;

    public String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public FavoriteBrewery() {
    }
    public FavoriteBrewery(int userId, int breweryId, String notes) {
        this.userId = userId;
        this.breweryId = breweryId;
        this.notes = notes;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }
}
