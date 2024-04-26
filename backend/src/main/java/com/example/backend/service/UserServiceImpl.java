package com.example.backend.service;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.SigninDto;
import com.example.backend.dto.SigninResponseDto;
import com.example.backend.dto.SignupDto;
import com.example.backend.entity.AuthToken;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @Transactional
    public ResponseDto newsignUp(SignupDto signupDto) {
        //check if the user is present
        //save the user
        //hash,encrypt the password
        //create the token
        if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail())))
        {
            throw new RuntimeException("user already present");
        }
        
        String encryptedPassword = signupDto.getPassword();
        try{
            encryptedPassword = hashPassord(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        //save the user
        User user = new User();
        user.setEmail(signupDto.getEmail());
        user.setFirstName(signupDto.getFirstName());
        user.setLastName(signupDto.getLastName());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        AuthToken authenticationToken = new AuthToken(user);
        authServiceImpl.saveConfirmationToken(authenticationToken);


        ResponseDto responseDto = new ResponseDto("success", "dummy response");
        return responseDto;
    }

    private String hashPassord(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return java.util.Base64.getEncoder().encodeToString(digest);

    }

    public SigninResponseDto newsingIn(SigninDto signinDto) throws NoSuchAlgorithmException {
        //find user by email
        User user = userRepository.findByEmail(signinDto.getEmail());
        if(Objects.isNull(user)){
            throw new RuntimeException("user not valid");
        }

        //hash the password
        try
        {
            if(!user.getPassword().equals(hashPassord(signinDto.getPassword()))){
                throw new RuntimeException("wrong password");
            }
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        //compare the password in DB

        //if password match
        AuthToken siginToken = authServiceImpl.getToken(user);

        //retreieve the token
        if(Objects.isNull(siginToken)){
            throw new RuntimeException("token is not available");
        }

        //return response
        SigninResponseDto signinResponseDto = new SigninResponseDto("success", siginToken.getToken());
        return signinResponseDto;

        //return new SigninResponseDto(success, dummy response)

    }
}
