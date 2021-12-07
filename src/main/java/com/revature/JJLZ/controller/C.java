package com.revature.JJLZ.controller;

import com.revature.JJLZ.model.ServiceResponse;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class C {
    @Autowired
    private UserService userService;
    @PostMapping("create")
    public ResponseEntity<Object> createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
        ServiceResponse<User> response = new ServiceResponse<>("success", user);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
