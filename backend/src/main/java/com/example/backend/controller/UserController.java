package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.SigninDto;
import com.example.backend.dto.SigninResponseDto;
import com.example.backend.dto.SignupDto;
import com.example.backend.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    //two apis
    //signup
    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignupDto signupDto){

        ResponseDto signUpDto = userServiceImpl.newsignUp(signupDto);
        return signUpDto;

    }


    //signin
    @PostMapping("/signin")
    public SigninResponseDto signIn(@RequestBody SigninDto signinDto) throws NoSuchAlgorithmException {
        SigninResponseDto signinResponseDto = userServiceImpl.newsingIn(signinDto);
        return signinResponseDto;
    }

}
