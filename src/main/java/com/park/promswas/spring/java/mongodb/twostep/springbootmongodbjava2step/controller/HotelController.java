package com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.controller;

import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.document.Hotel;
import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.document.QHotel;
import com.park.promswas.spring.java.mongodb.twostep.springbootmongodbjava2step.repository.HotelRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * Common Restful by default
     */

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
     * Query filtering
     * find by specify information
     */

    @GetMapping("/findId/{id}")
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

    @GetMapping("/findCity/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city) {
        List<Hotel> hotels = this.hotelRepository.findByCity(city);
        return hotels;
    }

    @GetMapping("/findReview")
    public List<Hotel> getByReviwNotNull() {
        List<Hotel> hotels = this.hotelRepository.findByReviewsNotNull();
        return hotels;
    }

    /**
     * Custom query by using library querydsl-mongodb
     * require to add dependencies and plugin and extends repository
     */

    @GetMapping("/findCountry/{country}") // Custom by using class query
    public List<Hotel> getByCountry(@PathVariable("country") String country) {
        // create query class.
        QHotel qHotel = new QHotel("hotel");

        // using query class we can create the filters.
        BooleanExpression filterByCountry = qHotel.address.country.eq(country);

        // we can then pass the filters to the findAll() method.
        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByCountry);
        return hotels;
    }

    @GetMapping("/findRatePrice/{minRating}/{maxPrice}")
    public List<Hotel> getByRatePrice(@PathVariable("minRating") int minRating, @PathVariable("maxPrice") int maxPrice) {
        QHotel qHotel = new QHotel("hotel");

        BooleanExpression filterByRateGreater = qHotel.reviews.any().rating.gt(minRating);
        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);

        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRateGreater));
        return hotels;
    }

    @GetMapping("/findNullReview")
    public List<Hotel> getByNullReview() {
        QHotel qHotel = new QHotel("hotel");
        BooleanExpression filterByNullReview = qHotel.reviews.isEmpty();
        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByNullReview);
        return hotels;
    }

}
