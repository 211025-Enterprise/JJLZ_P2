
package com.revature.JJLZ.controller;

import com.revature.JJLZ.exception.UserNotFoundException;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.model.ServiceResponse;
import com.revature.JJLZ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<User> listUser  = userService.getAllUsers();
        if (listUser == null) // if null will throw exception
            throw new UserNotFoundException("Users not founds - " + listUser);
        ServiceResponse<List<User>> response = new ServiceResponse<>("success", listUser);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public User getByUserId(@PathVariable String userId) {

        User theUser = userService.getAllUsers().get(Integer.parseInt(userId));
        if (theUser == null) // if null will throw exception
            throw new UserNotFoundException("User id not found - " + userId);
        return userService.findUserById(Integer.parseInt(userId));
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable String userId) {
        User theUser = userService.getAllUsers().get(Integer.parseInt(userId));
        if (theUser == null)
            throw new UserNotFoundException("User id not found - " + userId);
        userService.delete(Integer.parseInt(userId));
    }
      @PutMapping("/detail")
    public ResponseEntity<Object> userDetail(@RequestBody User user) {
        userService.update(user);
        ServiceResponse<User> response = new ServiceResponse<>("success", user);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}


