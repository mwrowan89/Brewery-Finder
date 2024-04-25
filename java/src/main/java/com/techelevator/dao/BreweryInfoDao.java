package com.techelevator.dao;

import com.techelevator.model.BreweryInfo;

import java.util.List;

public interface BreweryInfoDao {

    List<BreweryInfo> getAllBreweries();

    BreweryInfo getBreweryById(int id);

    public void updateBrewery(BreweryInfo breweryInfo, int breweryId);
}