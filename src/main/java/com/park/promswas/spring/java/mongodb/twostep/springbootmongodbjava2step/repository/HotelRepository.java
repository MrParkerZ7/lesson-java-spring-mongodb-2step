package com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.repository;

import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.document.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> , QueryDslPredicateExecutor<Hotel>{
    Hotel findById(String id);

    List<Hotel> findByPricePerNightLessThan(int maxPrice);

    List<Hotel> findByPricePerNightBetween(int minPrice,int maxPrice);

    List<Hotel> findByName(String name);

    @Query("{address.city:?0}") // Custom query object inside
    List<Hotel> findByCity(String city);

    List<Hotel> findByReviewsNotNull();
}
