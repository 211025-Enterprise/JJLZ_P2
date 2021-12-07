package com.revature.JJLZ.controller;



import com.revature.JJLZ.exception.UserNotFoundException;

import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.UserRepository;
import com.revature.JJLZ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService ;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public User getByUserId(@PathVariable String userId){

        User theUser = userService.getAllUsers().get(Integer.parseInt(userId));
        if ( theUser == null ) // if null will throw exception
            throw new UserNotFoundException("User id not found - " + userId);
            return userService.findUserById(Integer.parseInt(userId));
    }
    @GetMapping("/totalvalue")
    public ResponseEntity<?> getTotalValue(@RequestParam("userId") Integer userId) throws IOException {
        User richMan = userService.findUserById(userId);
        System.out.println(richMan.toString());
        double money = userService.totalBalance(richMan);
        System.out.println(money);
        return ResponseEntity.ok(money);

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

        User current = userRepository.findUserByUsername(username);
        current.setUsername(username);
        current.setPassword(password);

        if (userService.validate(current)){
            return ResponseEntity.ok("logged in as \n name:"+current.getFirstName() +
                    "\nlastname: "+current.getLastName()+
                    "\nHas a whopping amount of $" + current.getBalance());
        }
        else{
            return ResponseEntity.ok("invalid credentials");
        }
    }
    @PostMapping
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }
}
