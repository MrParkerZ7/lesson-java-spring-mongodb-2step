package com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.controller;

import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.document.Hotel;
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

    @DeleteMapping("/{id}") // Somthing wrong with delete
    public void delete(@PathVariable("id") String id) {
        this.hotelRepository.delete(id);
    }
}
