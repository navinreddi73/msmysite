package in.mysite.rating.repositories;

import in.mysite.rating.entities.Ratting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepo extends MongoRepository<Ratting, String> {

    //custom finder methods
    List<Ratting> findByUserId(String userId);
    List<Ratting> findByHotelId(String hotelId);
}
