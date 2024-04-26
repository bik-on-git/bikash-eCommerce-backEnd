package com.example.backend.service;

import com.example.backend.entity.AuthToken;
import com.example.backend.entity.User;
import com.example.backend.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl {

    @Autowired
    private AuthRepository authRepository;
    public void saveConfirmationToken(AuthToken authenticationToken) {
        authRepository.save(authenticationToken);
    }


    //get Token
    public AuthToken getToken(User user) {
        return authRepository.findByUser(user);

    }

    //get User: from token
    public User getUser(String token){

        AuthToken authenticationToken = authRepository.findByToken(token);
        if(Objects.isNull(token)){
            return null;
        }
        return authenticationToken.getUser();

    }



    //authenticate(): authenticate token method
    public void authenticate(String token)
    {
        if(Objects.isNull(token)){
            throw new RuntimeException("Token not present");
        }

        //check if user is null or not
        if(Objects.isNull(getUser(token))){
            throw new RuntimeException("User token not present");
        }

    }




}
