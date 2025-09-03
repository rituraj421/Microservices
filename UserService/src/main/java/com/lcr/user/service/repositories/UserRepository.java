package com.lcr.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcr.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
    
}
