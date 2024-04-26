package com.example.backend.repository;

import com.example.backend.entity.AuthToken;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthToken, Integer> {

    AuthToken findByUser(User user);
    AuthToken findByToken(String token);


}
