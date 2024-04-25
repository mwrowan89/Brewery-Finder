package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.techelevator.exception.DaoException;
import com.techelevator.model.BeerInfo;

@Component
@CrossOrigin
public class JdbcBeerInfoDao implements BeerInfoDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcBeerInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BeerInfo> getBeerListByBreweryId(int id) {
        List<BeerInfo> beerInfo = new ArrayList<>();
        String sql = "SELECT * FROM beers WHERE brewery_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                BeerInfo beer = mapRowToBeer(results);
                beerInfo.add(beer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return beerInfo;
    }

    @Override
    public List<BeerInfo> getAllBeers() {
        List<BeerInfo> beers = new ArrayList<>();
        String sql = "SELECT * FROM beers;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                BeerInfo beerInfo = mapRowToBeer(results);
                beers.add(beerInfo);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return beers;
    }

    @Override
    public void addBeer(BeerInfo beerInfo) {
        String sql = "INSERT INTO beers (name, type, abv, description, brewery_id) VALUES (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, beerInfo.beerName, beerInfo.beerType, beerInfo.beerAbv, beerInfo.beerDescription, beerInfo.breweryId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override


    public BeerInfo getBeerById(int id) {
        BeerInfo beerInfo = new BeerInfo();
        String sql = "SELECT * FROM beers WHERE beer_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                beerInfo = mapRowToBeer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return beerInfo;
    }

    public void updateBeer(BeerInfo beerInfo, int beerInfoId) {
        String sql = "UPDATE beers SET name = ?, type= ?, abv= ?, description = ? WHERE beer_id = ?;";
        try{
            jdbcTemplate.update(sql, beerInfo.getBeerName(), beerInfo.getBeerType(), beerInfo.getBeerAbv(), beerInfo.getBeerDescription(), beerInfoId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        };
    }

    @Override
    public BeerInfo hideBeer(int id) {
        String sql = "UPDATE beers SET show = false WHERE beer_id = ?;";
        try {
            jdbcTemplate.update(sql, id);
            return getBeerById(id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public BeerInfo showBeer(int id) {
        String sql = "UPDATE beers SET show = true WHERE beer_id = ?;";
        try {
            jdbcTemplate.update(sql, id);
            return getBeerById(id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private BeerInfo mapRowToBeer(SqlRowSet results) {
        BeerInfo beerInfo = new BeerInfo();
        beerInfo.setBeerId(results.getInt("beer_id"));
        beerInfo.setBeerName(results.getString("name"));
        beerInfo.setBreweryId(results.getInt("brewery_id"));
        beerInfo.setBeerType(results.getString("type"));
        beerInfo.setBeerAbv(results.getDouble("abv"));
        beerInfo.setBeerIbu(results.getDouble("ibu"));
        beerInfo.setBeerDescription(results.getString("description"));
        beerInfo.setBeerImage(results.getString("image"));
        beerInfo.setShow(results.getBoolean("show"));
        return beerInfo;
    }

}
