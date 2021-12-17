package com.revature.JJLZ.controller;

import com.revature.JJLZ.exception.UserNotFoundException;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.model.ServiceResponse;
import com.revature.JJLZ.repository.UserRepository;
import com.revature.JJLZ.service.UserService;
import com.revature.JJLZ.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService ;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtHandler;


//    @GetMapping
//    public ResponseEntity<?> getAllUsers(){
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<User> listUser  = userService.getAllUsers();
        if (listUser == null) // if null will throw exception
            throw new UserNotFoundException("Users not founds - " + listUser);
        ServiceResponse<List<User>> response = new ServiceResponse<>("success", listUser);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
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

    @PostMapping("/deposit")
    public ResponseEntity<?> makeDeposit(@RequestHeader (name="Authorization") String token, @RequestParam ("amount") Integer amount) {
        try {
            User u = jwtHandler.parseToken(token);
            if (u == null) {
                throw new UserNotFoundException("bad authentication");
            }
            if (!userService.validate(u)) {
                throw new UserNotFoundException("good token");
            }
            userService.deposit(u, amount);
            return ResponseEntity.ok(200);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid");
        }
    }
    @PostMapping("/withdraw")
    public ResponseEntity<?> makeWithdrawal(@RequestHeader (name="Authorization") String token, @RequestParam ("amount") Integer amount) {
        try {
            User u = jwtHandler.parseToken(token);
            if (u == null) {
                throw new UserNotFoundException("bad authentication");
            }
            if (!userService.validate(u)) {
                throw new UserNotFoundException("good token");
            }
            userService.withdraw(u, amount);
            return ResponseEntity.ok(200);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid");
        }
    }
}
