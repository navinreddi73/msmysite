package in.mysite.hotel.services;

import in.mysite.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel newHotel(Hotel hotel);
    //get all hotels info
    List<Hotel> getAllHotels();
    // get single hotel info
    Hotel getHotelById(String id);
}
