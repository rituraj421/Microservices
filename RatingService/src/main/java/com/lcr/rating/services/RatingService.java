package com.lcr.rating.services;

import java.util.List;

import com.lcr.rating.entities.Rating;

public interface RatingService {

    // creating 4 methods 
    // create rating
    Rating create(Rating rating);

    // get all ratings
    List<Rating> getRatings();

    // get all by userId
    List<Rating> getRatingByUserId(String userId);

    // get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
