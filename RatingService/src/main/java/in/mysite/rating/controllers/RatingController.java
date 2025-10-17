package in.mysite.rating.controllers;

import in.mysite.rating.entities.Ratting;
import in.mysite.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    
    @Autowired
    private RatingService ratingService;

    //create ratting
    @PostMapping
    public ResponseEntity<Ratting> createRating(@RequestBody Ratting rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    //get all ratings
    @GetMapping
    public ResponseEntity<List<Ratting>> getAllRatingsByUserId() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    //get all ratings by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ratting>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

    //get all ratings by hotel id
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Ratting>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingsByHotelId(hotelId));
    }
}
