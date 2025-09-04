package com.lcr.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcr.user.service.entities.Hotel;
import com.lcr.user.service.entities.Rating;
import com.lcr.user.service.entities.User;
import com.lcr.user.service.exceptions.ResourceNotFoundException;
import com.lcr.user.service.repositories.UserRepository;
import com.lcr.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // for service calling , implementation, (like from user to rating)
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
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    // get single user
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with giveb id is not found on server: " + userId));

                // fetch rating of the above user from rating service
    
                // http://localhost:8083/ratings/users/0d5bdb25-2b02-488b-a48b-1e48bb3066ab

        // @SuppressWarnings("unchecked")
        Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), Rating[].class);
        
        logger.info("{}",ratingsOfUser);

       List<Rating> ratings =  Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating->{

            // http://localhost:8082/hotels/af0ab699-a0a7-48a6-afd7-290f394b81d1
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            // set the hotel rating
            rating.setHotel(hotel);

            return rating;
            
        }).collect(Collectors.toList());


        // setting rating into user , so when we fire user , we also get the ratings provided by tjat user
        user.setRatings(ratingList);

        return user;
    }

}
