package in.mysite.hotel.controllers;

import in.mysite.hotel.entities.Hotel;
import in.mysite.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //new hotel creation
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.newHotel(hotel));
    }

    //get single
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
        return ResponseEntity.ok(hotelService.getHotelById(hotelId));
    }
    //Get all hotel info
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }
}
