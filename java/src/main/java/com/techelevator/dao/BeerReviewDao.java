package com.techelevator.dao;

import com.techelevator.model.BeerReview;

import java.util.List;

public interface BeerReviewDao {
    public void addReview(BeerReview beerReview);

    public List<BeerReview> getReviewsByBeerId(int beerId);

    public List<BeerReview> averageRatingByBeer();
    
    public List<BeerReview> getAllReviews();

    public void deleteReview(int id);
}

