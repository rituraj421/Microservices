package com.lcr.hotel.service.services;

import java.util.List;

import com.lcr.hotel.service.entities.Hotel;

public interface HotelService {

    Hotel create(Hotel hotel);

    // get all
    List<Hotel> getAll();

    // get single
    Hotel get(String id);
}
