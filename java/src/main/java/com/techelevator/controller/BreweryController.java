package com.techelevator.controller;

import com.techelevator.dao.BeerInfoDao;
import com.techelevator.dao.BeerReviewDao;
import com.techelevator.dao.BreweryInfoDao;
import com.techelevator.model.BeerInfo;
import com.techelevator.model.BeerReview;
import com.techelevator.model.BreweryInfo;
import com.techelevator.model.FavoriteBrewery;
import com.techelevator.dao.UserDao;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BreweryController {

    private BreweryInfoDao breweryInfoDao;
    private BeerInfoDao beerInfoDao;
    private UserDao userDao;
    private BeerReviewDao beerReviewDao;

    public BreweryController(BreweryInfoDao breweryInfoDao, UserDao userDao, BeerInfoDao beerInfoDao, BeerReviewDao beerReviewDao){
        this.breweryInfoDao = breweryInfoDao;
        this.userDao = userDao;
        this.beerInfoDao = beerInfoDao;
        this.beerReviewDao = beerReviewDao;
    }

    @RequestMapping (path = "/", method = RequestMethod.GET)
    public List<BreweryInfo> getAllBreweries() {
        return breweryInfoDao.getAllBreweries();
    }

    @RequestMapping (path = "/brewery/{id}", method = RequestMethod.GET)
    public BreweryInfo getBreweryById(@PathVariable int id) {
        BreweryInfo breweryInfo = breweryInfoDao.getBreweryById(id);
        return breweryInfo;
    }
    @RequestMapping (path = "/favorite/add", method = RequestMethod.POST)
    public void addFavoriteBrewery(@RequestBody FavoriteBrewery favoriteBrewery) {
        userDao.addFavoriteBrewery(favoriteBrewery);
    }

    @RequestMapping (path = "/favorite/delete", method = RequestMethod.DELETE)
    public void removeFavoriteBrewery(@RequestBody FavoriteBrewery favoriteBrewery) {
        userDao.removeFavoriteBrewery(favoriteBrewery);
    }
    @RequestMapping (path = "/beers/{id}", method = RequestMethod.GET)
    public List<BeerInfo> getBeerListByBreweryId(@PathVariable int id) {
        return beerInfoDao.getBeerListByBreweryId(id);
    }

    @RequestMapping (path = "/beers", method = RequestMethod.GET)
    public List<BeerInfo> getAllBeers() {
        return beerInfoDao.getAllBeers();
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping (path = "/add/beers", method = RequestMethod.POST)
    public void addBeer(@RequestBody BeerInfo beerInfo) {
        beerInfoDao.addBeer(beerInfo);
    }
    @RequestMapping(path = "/beer/{id}/update", method = RequestMethod.PUT)
    public void updateBeer(@RequestBody BeerInfo beerInfo, @PathVariable int id) {
        beerInfoDao.updateBeer(beerInfo, id);
    }
    @RequestMapping (path = "/favoriteList/{id}", method = RequestMethod.GET)
    public List<Integer> getFavoriteBreweries(@PathVariable int id) {
        return userDao.getFavoriteBreweries(id);
    }
    @RequestMapping (path = "/beer/{id}", method = RequestMethod.GET)
    public BeerInfo getBeerById(@PathVariable int id) {
        return beerInfoDao.getBeerById(id);
    }
    @RequestMapping (path = "/add/review", method = RequestMethod.POST)
    public void addReview(@RequestBody BeerReview beerReview) {
        beerReviewDao.addReview(beerReview);
    }
    @RequestMapping (path = "/beer/{id}/reviews", method = RequestMethod.GET)
    public List<BeerReview> getReviewsByBeerId(@PathVariable int id) {
        return beerReviewDao.getReviewsByBeerId(id);
    }
    @RequestMapping (path = "/removeBeer/{id}", method = RequestMethod.PUT)
    public BeerInfo updateBeerVisibility(@PathVariable int id) {
        return beerInfoDao.hideBeer(id);
    }
    @RequestMapping (path = "/showBeer/{id}", method = RequestMethod.PUT)
    public BeerInfo showBeer(@PathVariable int id) {
        return beerInfoDao.showBeer(id);
    }
    @RequestMapping (path = "/update/favoritebrewery", method = RequestMethod.PUT)
    public void updateFavBreweryNotes(@RequestBody FavoriteBrewery favoriteBrewery) {
        userDao.updateFavBreweryNotes(favoriteBrewery);
    }

    @RequestMapping (path = "/favoritebrewery", method = RequestMethod.GET)
    public List<FavoriteBrewery> getNotesByBreweryId() {
        return userDao.getAllFavBreweries();
    }
    @RequestMapping (path = "/beers/average", method = RequestMethod.GET)
    public List<BeerReview> averageRatingByBeer() {
        return beerReviewDao.averageRatingByBeer();
    }

    @RequestMapping (path = "/brewery/{id}/update", method = RequestMethod.PUT)
    public void updateBrewery(@RequestBody BreweryInfo breweryInfo, @PathVariable int id) {
        breweryInfoDao.updateBrewery(breweryInfo, id);
    }
    @RequestMapping (path = "/admin/userList/reviews", method = RequestMethod.GET)
    public List<BeerReview> getReviewsByUserId() {
        return beerReviewDao.getAllReviews();
    }
    @RequestMapping(path = "/admin/userList/deleteReview/{id}", method = RequestMethod.DELETE)
    public void deleteReview(@PathVariable int id) {
        beerReviewDao.deleteReview(id);
    }
}
