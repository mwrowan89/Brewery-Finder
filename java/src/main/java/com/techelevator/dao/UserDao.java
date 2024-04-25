package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);

    List<Integer> getFavoriteBreweries(int id);

    void addFavoriteBrewery(FavoriteBrewery favoriteBrewery);

    void removeFavoriteBrewery(FavoriteBrewery favoriteBrewery);
    void updateFavBreweryNotes(FavoriteBrewery favoriteBrewery);
    List<FavoriteBrewery> getAllFavBreweries();

    void deleteUser(int id);

}
