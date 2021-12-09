package com.revature.JJLZ.controller;

import com.revature.JJLZ.model.ServiceResponse;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.service.UserService;
import com.revature.JJLZ.util.JwtUtil;
import com.revature.JJLZ.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.*;

@RestController
public class AccountController {
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtHandler;

	@PostMapping("logged")
	public ResponseEntity<String> validation(@RequestBody User user) {
		if (userService.validate(user)) {
			user = userService.getUserByUsername(user);
			String token = jwtHandler.generateToken(user);
			return ResponseEntity.status(HttpStatus.OK).body(token);
		} else {
			System.out.println("invalid");
			return ResponseEntity.status(HttpStatus.OK).body("invalid");
		}
	}

	@PostMapping("totalvalue")
	public ResponseEntity<String> getTotalValue(@RequestHeader (name="Authorization") String token) {
		try {
			User u = jwtHandler.parseToken(token);
			if (u == null) {
				throw new UserNotFoundException("bad authentication");
			}
			if (!userService.validate(u)) {
				throw new UserNotFoundException("good token");
			}
			return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(userService.totalBalance(u)));
		} catch (Exception e) {
			return ResponseEntity.ok("Failed to fetch");
		}
	}

	@PostMapping("createUser")
	public ResponseEntity<Object> createNewUser(@RequestBody User user) {
		userService.createNewUser(user);
		ServiceResponse<User> response = new ServiceResponse<>("success", user);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
