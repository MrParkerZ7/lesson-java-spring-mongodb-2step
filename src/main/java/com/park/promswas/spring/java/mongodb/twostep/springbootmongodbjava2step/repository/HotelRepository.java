package com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.repository;

import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.document.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,String> {
}
