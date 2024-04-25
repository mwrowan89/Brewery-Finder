package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.BeerInfo;

public interface BeerInfoDao {
    List<BeerInfo> getAllBeers();

    List<BeerInfo> getBeerListByBreweryId(int id);

    void updateBeer(BeerInfo beerInfo, int beerInfoId);

    BeerInfo getBeerById(int id);

    void addBeer(BeerInfo beerInfo);


    BeerInfo hideBeer(int id);
    BeerInfo showBeer(int id);
}
