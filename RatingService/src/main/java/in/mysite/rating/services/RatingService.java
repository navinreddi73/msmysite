package in.mysite.rating.services;

import in.mysite.rating.entities.Ratting;

import java.util.List;

public interface RatingService {

    //create ratting
    Ratting createRating(Ratting rating);

    //get all ratings
    List<Ratting> getAllRatings();

    //get all ratings by userid
    List<Ratting> getRatingsByUserId(String userId);

    //get all ratings by Hotel id
    List<Ratting> getRatingsByHotelId(String hotelId);
}
