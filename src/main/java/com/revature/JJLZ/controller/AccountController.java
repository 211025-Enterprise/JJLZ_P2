package com.revature.JJLZ.controller;

import com.revature.JJLZ.model.ServiceResponse;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.service.UserService;
import com.revature.JJLZ.service.StockService;
import com.revature.JJLZ.service.WatchlistService;
import com.revature.JJLZ.util.JwtUtil;
import com.revature.JJLZ.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.time.*;
import java.io.StringWriter;
import java.io.IOException;

import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;
import tech.tablesaw.io.json.JsonWriteOptions;

@RestController
public class AccountController {
	@Autowired
	private UserService userService;

	@Autowired
	private StockService stockService;

	@Autowired
	private WatchlistService watchlistService;

	@Autowired
	private JwtUtil jwtHandler;

	private User authorizeToken(String token) {
		try {
			User u = jwtHandler.parseToken(token);
			if (u == null) {
				throw new UserNotFoundException("bad token");
			}
			if (!userService.validate(u)) {
				throw new UserNotFoundException("good token");
			}
			return u;
		} catch (Exception e) {
			return null;
		}
	}

	@PostMapping("logged")
	public ResponseEntity<String> validation(@RequestBody User user) {
		if (userService.validate(user)) {
			user = userService.getUserByUsername(user);
			String token = jwtHandler.generateToken(user);
			return ResponseEntity.status(HttpStatus.OK).body(token);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("invalid");
		}
	}

	@PostMapping("totalvalue")
	public ResponseEntity<String> getTotalValue(@RequestHeader (name="Authorization") String token) throws IOException {
		User u = authorizeToken(token);
		if (u != null) {
			return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(userService.totalBalance(u)));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid");
		}
	}

	@PostMapping("fullname")
	public ResponseEntity<String> getFullName(@RequestHeader (name="Authorization") String token) {
		User u = authorizeToken(token);
		if (u != null) {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getFullName(u));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid");
		}
	}

	@PostMapping("stocklist")
	public ResponseEntity<?> getStockList(@RequestHeader (name="Authorization") String token) {
		User u = authorizeToken(token);
		if (u != null) {
			return ResponseEntity.status(HttpStatus.OK).body(stockService.getAllStocksByUser(u.getUserId()));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid");
		}
	}

	@PostMapping("watchlist")
	public ResponseEntity<?> getWatchList(@RequestHeader (name="Authorization") String token) {
		User u = authorizeToken(token);
		if (u != null) {
			return ResponseEntity.status(HttpStatus.OK).body(watchlistService.getAllStocksByWatcher(u.getUserId()));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid");
		}
	}

	@PostMapping("createUser")
	public ResponseEntity<Object> createNewUser(@RequestBody User user) {
		user.setBalance(10000);
		userService.createNewUser(user);
		ServiceResponse<User> response = new ServiceResponse<>("success", user);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@PostMapping("buystock")
	public ResponseEntity<?> buyStocks(@RequestHeader (name="Authorization") String token, @RequestParam("stockname") String stockname, @RequestParam("amount") Integer amount) throws IOException {
		System.out.println("trying to buy stock");
		User u = authorizeToken(token);
		if (u != null) {
			System.out.println(stockname);
			System.out.println(amount);
			stockService.buyStock(stockname, amount, userService.findUserById(u.getUserId()));
			return ResponseEntity.status(HttpStatus.OK).body("bought stock");
		} else {
			System.out.println("failing");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid");
		}
	}

	@PostMapping("sellstock")
	public ResponseEntity<?> sellStocks(@RequestHeader (name="Authorization") String token, @RequestParam("stockname") String stockname, @RequestParam("amount") Integer amount) throws IOException {
		User u = authorizeToken(token);
		if (u != null) {
			stockService.sellStock(stockname, amount, userService.findUserById(u.getUserId()));
			return ResponseEntity.status(HttpStatus.OK).body("sold stock");
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid");
		}
	}

	@PostMapping("addtowatchlist")
	public ResponseEntity<?> addStockstoWatchlist(@RequestHeader (name="Authorization") String token, @RequestParam("stockname") String stockname){
		User u = authorizeToken(token);
		if (u != null) {
			watchlistService.addStocktoWatchlist(stockname,userService.findUserById(u.getUserId()));
			return ResponseEntity.status(HttpStatus.OK).body("added to watchlist");
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid");
		}
	}

	@PostMapping("removefromwatchlist")
	public ResponseEntity<?> removeStockFromWatchlist(@RequestHeader (name="Authorization") String token, @RequestParam("stockname") String stockname) {
		User u = authorizeToken(token);
		if (u != null) {
			watchlistService.removeStocksFromWatchlist(stockname, userService.findUserById(u.getUserId()));
			return ResponseEntity.status(HttpStatus.OK).body("removed from watchlist");
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid");
		}
	}
}
