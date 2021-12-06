package com.revature.JJLZ.controller;

import com.revature.JJLZ.exception.UserNotFoundException;
import com.revature.JJLZ.model.ServiceResponse;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping("createUser")
    public ResponseEntity<Object> createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
        ServiceResponse<User> response = new ServiceResponse<>("success", user);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<Object> getAllUsers() {
        List<User> listUser  = userService.getAllUsers();
        if (listUser == null) // if null will throw exception
            throw new UserNotFoundException("Users not founds - " + listUser);
        ServiceResponse<List<User>> response = new ServiceResponse<>("success", listUser);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}