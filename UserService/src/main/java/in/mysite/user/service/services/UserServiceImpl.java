package in.mysite.user.service.services;

import in.mysite.user.service.entities.Hotel;
import in.mysite.user.service.entities.Ratting;
import in.mysite.user.service.entities.User;
import in.mysite.user.service.exceptions.ResourceNotFoundException;
import in.mysite.user.service.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @Override
//    public User getUserById(String id) {
//        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
//
//        // Fetch ratings of the user from Rating Service
//        Ratting[] ratingOfUser = restTemplate.getForObject(
//                "http://RATINGSERVICE/ratings/user/" + user.getUserId(),
//                Ratting[].class
//        );
//        logger.info("{}",ratingOfUser);
//
//        List<Ratting> rattingList = Arrays.asList(ratingOfUser);
//
//        // Map each rating to include hotel details from Hotel Service
//        List<Ratting> ratingsWithHotel = rattingList.stream().map(rating -> {
//            // Call Hotel Service for hotel details
//            ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity(
//                    "http://HOTELSERVICE/hotels/" + rating.getHotelId(),
//                    Hotel.class
//            );
//            Hotel hotel = hotelResponse.getBody();
//            logger.info("Response Status Code : {}",hotel);
//            rating.setHotel(hotel); // assuming Ratting class has `private Hotel hotel;`
//
//            //api call to hotel service to get the hotel
//            //set the hotel for rating
//            //return the rating
//            return rating;
//        }).collect(Collectors.toList());
//
//        // Set enriched ratings in the user object
//        user.setRatings(ratingsWithHotel);
//        return user;
//    }

//    @Override
//    public User getUserById(String id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
//
//        logger.info("User found: {}", user.getUserId());
//
//        // ✅ Get ratings of user from Rating Service
//        Ratting[] ratingOfUser = restTemplate.getForObject(
//                "http://RATINGSERVICE/ratings/user/" + user.getUserId(),
//                Ratting[].class
//        );
//
//        if (ratingOfUser == null) {
//            logger.warn("No ratings found for user {}", user.getUserId());
//            user.setRatings(new ArrayList<>());
//            return user;
//        }
//
//        logger.info("Ratings fetched: {}", (Object) ratingOfUser);
//
//        List<Ratting> rattingList = Arrays.asList(ratingOfUser);
//
//        // ✅ For each rating, get hotel details
//        List<Ratting> ratingsWithHotel = rattingList.stream().map(rating -> {
//            try {
//                logger.info("Fetching hotel for hotelId: {}", rating.getHotelId());
//                ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity(
//                        "http://HOTELSERVICE/hotels/" + rating.getHotelId(),
//                        Hotel.class
//                );
//
//                Hotel hotel = hotelResponse.getBody();
//                rating.setHotel(hotel);
//                return rating;
//            } catch (Exception e) {
//                logger.error("Error fetching hotel for ratingId {}: {}", rating.getRatingId(), e.getMessage());
//                return rating; // return without hotel if fails
//            }
//        }).collect(Collectors.toList());
//
//        user.setRatings(ratingsWithHotel);
//        return user;
//    }

//    @Override
//    public User getUserById(String id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
//
//        logger.info("User found: {}", user.getUserId());
//
//        try {
//            Ratting[] ratingOfUser = restTemplate.getForObject(
//                    "http://localhost:8082/ratings/user/" + user.getUserId(),
//                    Ratting[].class
//            );
//            logger.info("Ratings fetched: {}", (Object) ratingOfUser);
//
//            List<Ratting> ratingsWithHotel = new ArrayList<>();
//            if (ratingOfUser != null) {
//                for (Ratting rating : ratingOfUser) {
//                    try {
//                        ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity(
//                                "http://localhost:8083/hotels/" + rating.getHotelId(),
//                                Hotel.class
//                        );
//                        rating.setHotel(hotelResponse.getBody());
//                    } catch (Exception e) {
//                        logger.error("Hotel fetch failed for hotelId {}: {}", rating.getHotelId(), e.getMessage());
//                    }
//                    ratingsWithHotel.add(rating);
//                }
//            }
//            user.setRatings(ratingsWithHotel);
//        } catch (Exception e) {
//            logger.error("Rating fetch failed: {}", e.getMessage());
//            user.setRatings(new ArrayList<>());
//        }
//
//        return user;
//    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        logger.info("User found: {}", user.getUserId());

        try {
            // Fetch ratings for this user from Ratings service
            Ratting[] ratingOfUser = restTemplate.getForObject(
                    "http://localhost:8083/ratings/user/" + user.getUserId(),
                    Ratting[].class
            );

            List<Ratting> ratingsWithHotel = new ArrayList<>();
            if (ratingOfUser != null) {
                for (Ratting rating : ratingOfUser) {
                    try {
                        // Fetch hotel for each rating
                        ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity(
                                "http://localhost:8082/hotels/" + rating.getHotelId(),
                                Hotel.class
                        );
                        rating.setHotel(hotelResponse.getBody());
                    } catch (Exception e) {
                        logger.error("Hotel fetch failed for hotelId {}: {}", rating.getHotelId(), e.getMessage());
                    }
                    ratingsWithHotel.add(rating);
                }
            }
            user.setRatings(ratingsWithHotel);
        } catch (Exception e) {
            logger.error("Rating fetch failed: {}", e.getMessage());
            user.setRatings(new ArrayList<>());
        }

        return user;
    }

    @Override
    public User updateUserById(String id, User newUserData) {
        User oldUserData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
//        if (oldUserData != null) {
//            return userRepository.save(newUserData);
//        }
//        else{
//            throw new ResourceNotFoundException("User with id " + id + " not found");
//        }
        // Update only fields that should change
        oldUserData.setName(newUserData.getName());
        oldUserData.setEmail(newUserData.getEmail());
        oldUserData.setPassword(newUserData.getPassword());

        return userRepository.save(oldUserData);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
}
