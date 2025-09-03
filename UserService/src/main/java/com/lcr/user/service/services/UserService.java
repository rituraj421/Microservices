package com.lcr.user.service.services;

import java.util.List;

import com.lcr.user.service.entities.User;

public interface UserService {

    // user operations

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    
}
