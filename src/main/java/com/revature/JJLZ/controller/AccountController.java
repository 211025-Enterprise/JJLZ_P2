package com.revature.JJLZ.controller;

import com.revature.JJLZ.model.ServiceResponse;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private UserService userService;

    @PostMapping("logged")
    public ResponseEntity<Object> validation(@RequestBody User user) {
        if (userService.validate(user)) {
            ServiceResponse<Object> response = new ServiceResponse<>("success", user);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.ok("invalid credentials");
        }
    }
    @PostMapping("createUser")
    public ResponseEntity<Object> createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
        ServiceResponse<User> response = new ServiceResponse<>("success", user);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}