package com.revature.JJLZ.controller;



import com.revature.JJLZ.exception.UserNotFoundException;

import com.revature.JJLZ.model.User;
import com.revature.JJLZ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService ;


    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @GetMapping("/{userId}")
    public User getByUserId(@PathVariable String userId){

        User theUser = userService.getAllUsers().get(Integer.parseInt(userId));
        if ( theUser == null ) // if null will throw exception
            throw new UserNotFoundException("User id not found - " + userId);
            return userService.findUserById(Integer.parseInt(userId));
    }
    @PutMapping
    public User updateUser(@RequestBody User user){
       return userService.update(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable String userId){
        User theUser = userService.getAllUsers().get(Integer.parseInt(userId));
        if ( theUser == null )
            throw new UserNotFoundException("User id not found - " + userId);

        userService.delete(Integer.parseInt(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<?> validateCredentials(@RequestParam("username") String username,
                                                 @RequestParam("password") String password){
        User current = new User();
        current.setUsername(username);
        current.setPassword(password);

        if (userService.validate(current)){
            return ResponseEntity.ok("logged in");
        }
        else{
            return ResponseEntity.ok("invalid credentials");
        }
    }
}
