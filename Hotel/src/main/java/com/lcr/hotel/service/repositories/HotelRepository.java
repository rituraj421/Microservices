package com.lcr.hotel.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcr.hotel.service.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
