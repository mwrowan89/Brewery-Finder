package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.BeerReview;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

@Component
@CrossOrigin
public class JdbcBeerReviewDao implements BeerReviewDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcBeerReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

@Override
public void addReview(BeerReview beerReview) {
        String sql = "INSERT INTO reviews (beer_id, user_id, rating, review) VALUES(?, ?, ?, ?)";
        try{
            jdbcTemplate.update(sql, beerReview.getBeerId(), beerReview.getUserId(), beerReview.getRating(), beerReview.getReview());
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public List<BeerReview> getReviewsByBeerId(int beerId) {
        List<BeerReview> beerReviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE beer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
            while(results.next()) {
                BeerReview beerReview = mapRowToReview(results);
                beerReviews.add(beerReview);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return beerReviews;
    }

    @Override
    public List<BeerReview> averageRatingByBeer() {
        List<BeerReview> averages = new ArrayList<>();
        String sql = "SELECT beer_id, AVG(rating) FROM reviews GROUP BY beer_id";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                BeerReview beerReview = new BeerReview();
                beerReview.setBeerId(results.getInt("beer_id"));
                beerReview.setRating(results.getDouble("avg"));
                averages.add((beerReview));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return averages;
    }

    @Override
    public List<BeerReview> getAllReviews() {
        List<BeerReview> beerReviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                BeerReview beerReview = mapRowToReview(results);
                beerReviews.add(beerReview);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return beerReviews;
    }

    @Override
    public void deleteReview(int id) {
        String sql = "DELETE FROM reviews WHERE review_id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
    
    private BeerReview mapRowToReview(SqlRowSet results) {
        BeerReview beerReview = new BeerReview();
        beerReview.setId(results.getInt("review_id"));
        beerReview.setBeerId(results.getInt("beer_id"));
        beerReview.setUserId(results.getInt("user_id"));
        beerReview.setRating(results.getDouble("rating"));
        beerReview.setReview(results.getString("review"));
        return beerReview;
    }
}

