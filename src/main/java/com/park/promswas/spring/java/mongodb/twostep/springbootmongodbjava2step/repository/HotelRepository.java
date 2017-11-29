package com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.repository;

import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.document.Hotel;
import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {
    Hotel findById(String id);

    List<Hotel> findByPricePerNightLessThan(int maxPrice);

    List<Hotel> findByPricePerNightBetween(int minPrice,int maxPrice);

    List<Hotel> findByName(String name);
}
