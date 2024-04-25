package com.techelevator.model;

public class BeerReview {
    public int id;
    public int beerId;
    public int userId;
    public double rating;
    public String review;
    public BeerReview(){};
    public BeerReview (int id, int beerId, int userId, int rating, String review) {
        this.id = id;
        this.beerId = beerId;
        this.userId = userId;
        this.rating = rating;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
