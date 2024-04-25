package com.techelevator.model;

public class BeerInfo {
    public int beerId;
    public int breweryId;
    public String beerName;
    public String beerType;
    public double beerAbv;
    public double beerIbu;
    public String beerDescription;
    public String beerImage;
    public boolean show;

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    public void setBeerImage(String beerImage) {
        this.beerImage = beerImage;
    }

    public String getBeerImage() {
        return beerImage;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerDescription(String beerDescription) {
        this.beerDescription = beerDescription;
    }

    public String getBeerDescription() {
        return beerDescription;
    }

    public void setBreweryId(int brewery) {
        this.breweryId = brewery;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }
    
    public void setBeerType(String beerType) {
        this.beerType = beerType;
    }

    public void setBeerAbv(double beerAbv) {
        this.beerAbv = beerAbv;
    }

    public void setBeerIbu(double beerIbu) {
        this.beerIbu = beerIbu;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public String getBeerName() {
        return beerName;
    }

    public String getBeerType() {
        return beerType;
    }

    public double getBeerAbv() {
        return beerAbv;
    }

    public double getBeerIbu() {
        return beerIbu;
    }
}
