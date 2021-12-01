package com.revature.JJLZ.controller;


import com.revature.JJLZ.model.User;
import com.revature.JJLZ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public User getByUserId(@PathVariable String userId){
        return userService.findUserById(Integer.parseInt(userId));
    }

    @PostMapping
    public User createNewUser(@RequestBody User user){
        return userService.createNewUser(user);
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
