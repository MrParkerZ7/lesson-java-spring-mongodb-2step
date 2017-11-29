package com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.controller;

import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.document.Hotel;
import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.document.Review;
import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.repository.HotelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/all")
    public List<Hotel> getAll() {
        List<Hotel> hotels = this.hotelRepository.findAll();
        return hotels;
    }

    @PutMapping // insert
    public void insert(@RequestBody Hotel hotel) {
        this.hotelRepository.insert(hotel);
    }

    @PostMapping // insert or update
    public void update(@RequestBody Hotel hotel) {
        this.hotelRepository.save(hotel);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        this.hotelRepository.delete(id);
    }

    /**
     * Filtering
     * find by specify information
     */

    @GetMapping("/findOne/{id}")
    public Hotel getById(@PathVariable("id") String id) {
        Hotel hotel = this.hotelRepository.findById(id);
        return hotel;
    }

    @GetMapping("/lessPrice/{maxPrice}")
    public List<Hotel> getByPricePerNightLessThan(@PathVariable("maxPrice") int maxPrice) {
        List<Hotel> hotels = this.hotelRepository.findByPricePerNightLessThan(maxPrice);
        return hotels;
    }

    @GetMapping("/rangePrice/{minPrice}/{maxPrice}") 
    public List<Hotel> getByMinMaxPrice(@PathVariable("minPrice") int minPrice, @PathVariable("maxPrice") int maxPrice) {
        List<Hotel> hotels = this.hotelRepository.findByPricePerNightBetween(minPrice, maxPrice);
        return hotels;
    }

    @GetMapping("/findName/{name}")
    public List<Hotel> getByName(@PathVariable("name") String name) {
        List<Hotel> hotels = this.hotelRepository.findByName(name);
        return hotels;
    }
}
