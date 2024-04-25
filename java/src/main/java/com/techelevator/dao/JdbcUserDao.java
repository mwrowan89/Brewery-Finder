package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.exception.DaoException;
import com.techelevator.model.BreweryInfo;
import com.techelevator.model.FavoriteBrewery;
import com.techelevator.model.RegisterUserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

import javax.print.DocFlavor;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT user_id, username, password_hash, role FROM users WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                user = mapRowToUser(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, role FROM users";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                User user = mapRowToUser(results);
                users.add(user);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        User user = null;
        String sql = "SELECT user_id, username, password_hash, role FROM users WHERE username = LOWER(TRIM(?));";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
            if (rowSet.next()) {
                user = mapRowToUser(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public User createUser(RegisterUserDto user) {
        User newUser = null;
        String insertUserSql = "INSERT INTO users (username, password_hash, role) values (LOWER(TRIM(?)), ?, ?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(user.getPassword());
        String ssRole = user.getRole().toUpperCase().startsWith("ROLE_") ? user.getRole().toUpperCase() : "ROLE_" + user.getRole().toUpperCase();
        try {
            int newUserId = jdbcTemplate.queryForObject(insertUserSql, int.class, user.getUsername(), password_hash, ssRole);
            newUser = getUserById(newUserId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newUser;
    }
    @Override
    public List<Integer> getFavoriteBreweries(int id) {
        List<Integer> favoriteBreweries = new ArrayList<>();
        String sql = "SELECT brewery_id FROM favorite_breweries WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                favoriteBreweries.add(results.getInt("brewery_id"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return favoriteBreweries;
    }

    @Override
    public void addFavoriteBrewery(FavoriteBrewery favoriteBrewery) {
        String sql = "INSERT INTO favorite_breweries (user_id, brewery_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, favoriteBrewery.userId, favoriteBrewery.breweryId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    } 

    @Override
    public void removeFavoriteBrewery(FavoriteBrewery favoriteBrewery) {
        String sql = "DELETE FROM favorite_breweries WHERE user_id = ? AND brewery_id = ?";
        try {
            jdbcTemplate.update(sql, favoriteBrewery.userId, favoriteBrewery.breweryId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
    @Override
    public void updateFavBreweryNotes(FavoriteBrewery favoriteBrewery) {
        String sql = "UPDATE favorite_breweries SET notes = ? WHERE user_id = ? AND brewery_id = ?";
        try {
            jdbcTemplate.update(sql, favoriteBrewery.getNotes(), favoriteBrewery.getUserId(), favoriteBrewery.getBreweryId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public List<FavoriteBrewery> getAllFavBreweries() {
        List<FavoriteBrewery> notes = new ArrayList<>();
        String sql = "SELECT * from favorite_breweries";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                notes.add(mapRowToFavBrewery(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return notes;
    }
    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM favorite_breweries WHERE user_id = ?; DELETE FROM reviews WHERE user_id = ?; DELETE FROM users WHERE user_id = ?;";
        try {
            jdbcTemplate.update(sql, id, id, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    private FavoriteBrewery mapRowToFavBrewery(SqlRowSet results) {
        FavoriteBrewery favoriteBrewery = new FavoriteBrewery();
        favoriteBrewery.setBreweryId(results.getInt("brewery_id"));
        favoriteBrewery.setUserId(results.getInt("user_id"));
        favoriteBrewery.setNotes(results.getString("notes"));
        return favoriteBrewery;
    }
    private BreweryInfo mapRowToBrewery(SqlRowSet results) {
        BreweryInfo breweryInfo = new BreweryInfo();
        breweryInfo.setId(results.getInt("brewery_id"));
        breweryInfo.setName(results.getString("name"));
        breweryInfo.setPhone(results.getString("phone"));
        breweryInfo.setAddress(results.getString("address"));
        breweryInfo.setZipCode(results.getString("zip_code"));
        breweryInfo.setState(results.getString("state"));
        breweryInfo.setCity(results.getString("city"));
        breweryInfo.setHistory(results.getString("history"));
        breweryInfo.setHours(results.getString("hours"));
        breweryInfo.setImg(results.getString("image"));
        breweryInfo.setWebSite(results.getString("website_url"));
        breweryInfo.setRating(results.getDouble("rating"));
        return breweryInfo;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }
}
