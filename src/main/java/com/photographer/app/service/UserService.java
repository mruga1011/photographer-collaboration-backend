package com.photographer.app.service;

import com.photographer.app.dto.LoginRequest;
import com.photographer.app.entity.User;
import com.photographer.app.exception.CustomException;
import com.photographer.app.repository.UserRepository;
import com.photographer.app.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public User register(User user){
        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            throw new CustomException("Email alredy exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String Login(LoginRequest request){
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException("User not found"));

        boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!isPasswordMatch){
            throw new CustomException("Password is incorrect");
        }
        return jwtUtil.generateToken(user.getEmail());

    }

}
