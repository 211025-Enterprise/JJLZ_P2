package com.revature.JJLZ.service;


import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User createNewUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Integer userId){
        return userRepository.findById(userId).orElse(null);
    }

    // checks if the user exists with that username, then compares the password to the password in the database
    public boolean validate(User user){
        return userRepository.findUserByUsername(user.getUsername()).getPassword().equals(user.getPassword());
    }
}
