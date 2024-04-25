package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.BreweryInfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@CrossOrigin
public class JdbcBreweryInfoDao implements BreweryInfoDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcBreweryInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BreweryInfo> getAllBreweries() {
        List<BreweryInfo> breweries = new ArrayList<>();
        String sql = "SELECT * FROM breweries;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                BreweryInfo breweryInfo = mapRowToBrewery(results);
                breweries.add(breweryInfo);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return breweries;
    }

    @Override
    public BreweryInfo getBreweryById(int id) {
        BreweryInfo breweryInfo = null;
        String sql = "SELECT * FROM breweries WHERE brewery_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                breweryInfo = mapRowToBrewery(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return breweryInfo;
    }

    @Override
    public void updateBrewery(BreweryInfo breweryInfo, int breweryId) {
        String sql = "UPDATE breweries SET name = ?, address = ?, city = ?, state = ?, hours = ?, phone = ?, history = ?, image = ? WHERE brewery_id = ?";
        try {
            jdbcTemplate.update(sql, breweryInfo.getName(), breweryInfo.getAddress(), breweryInfo.getCity(), breweryInfo.getState(), breweryInfo.getHours(), breweryInfo.getPhone(), breweryInfo.getHistory(), breweryInfo.getImg(), breweryId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }


    //TODO: Fix map to row with correct column names

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
}
