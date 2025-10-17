package in.mysite.hotel.services;

import in.mysite.hotel.entities.Hotel;
import in.mysite.hotel.exceptions.ResourceNotFoundException;
import in.mysite.hotel.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public Hotel newHotel(Hotel hotel) {
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        return hotelRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with id '%s' not found"));
    }
}
