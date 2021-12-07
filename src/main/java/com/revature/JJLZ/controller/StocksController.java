package com.revature.JJLZ.controller;

import com.revature.JJLZ.model.Stocks;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.StockRepository;
import com.revature.JJLZ.repository.UserRepository;
import com.revature.JJLZ.service.StockService;
import com.revature.JJLZ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/stocks")
public class StocksController {
    @Autowired
    private StockService stockService;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userid}")
    public ResponseEntity<?> getAllStocksByUser(@PathVariable int userId){

        return ResponseEntity.ok(stockService.getAllStocksByUser(userId));
    }

    @PostMapping
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buyStocks(@RequestParam("stockname") String stockname, @RequestParam("amount") Integer amount,
                                          @RequestParam("userId") Integer userId) throws IOException {
        stockService.buyStock(stockname, amount, userService.findUserById(userId));
        //System.out.println(userService.findUserById(userId));
        return ResponseEntity.ok(200);
    }
    @PostMapping("/sell")
    public ResponseEntity<?> sellStocks(@RequestParam("stockname") String stockname, @RequestParam("amount") Integer amount,
                                       @RequestParam("userId") Integer userId) throws IOException {
        stockService.sellStock(stockname, amount, userService.findUserById(userId));
        //System.out.println(userService.findUserById(userId));
        return ResponseEntity.ok(200);
    }
}
