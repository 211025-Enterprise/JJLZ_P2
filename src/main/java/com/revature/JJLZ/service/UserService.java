package com.revature.JJLZ.service;


import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    //add to list



    public void buyStock(BigDecimal stockP, int amount,User user){
        stockP = stockP.setScale(2, RoundingMode.CEILING);
        double stockPrice = stockP.doubleValue();
        stockPrice = stockPrice * amount;
        if (stockPrice <= user.getBalance()) {
            user.setBalance(user.getBalance()-stockPrice);
            System.out.println(user.getBalance());
            //if not have add to list
        }
        else{
            System.out.println("insufficient funds.");
        }
    }
    public void sellStock(BigDecimal stockP, int amount, User user){
        stockP = stockP.setScale(2, RoundingMode.CEILING);
        double stockPrice = stockP.doubleValue();
        stockPrice = stockPrice * amount;
        user.setBalance(user.getBalance()+stockPrice);
        System.out.println(user.getBalance());
        //if last of stock remove from list
    }
}
