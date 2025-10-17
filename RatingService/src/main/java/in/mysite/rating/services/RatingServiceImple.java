package in.mysite.rating.services;

import in.mysite.rating.entities.Ratting;
import in.mysite.rating.repositories.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImple implements RatingService {

    @Autowired
    private RatingRepo ratingRepo;

    @Override
    public Ratting createRating(Ratting rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public List<Ratting> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Ratting> getRatingsByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Ratting> getRatingsByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
