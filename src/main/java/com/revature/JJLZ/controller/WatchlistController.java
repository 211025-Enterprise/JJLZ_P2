package com.revature.JJLZ.controller;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.StockRepository;
import com.revature.JJLZ.repository.UserRepository;
import com.revature.JJLZ.repository.WatchlistRepository;
import com.revature.JJLZ.service.StockService;
import com.revature.JJLZ.service.UserService;
import com.revature.JJLZ.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {
    @Autowired
    private WatchlistService watchlistService;
    @Autowired
    private WatchlistRepository watchlistRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/{userid}")
    public ResponseEntity<?> getAllWatchedStocksByUser(@PathVariable int userId){

        return ResponseEntity.ok(watchlistService.getAllStocksByWatcher(userId));
    }
    @GetMapping
    public ResponseEntity<?> getAllWatchedStocksByUser(){

        return ResponseEntity.ok(watchlistService.getAllStocksByWatcher(1));
    }
    @PostMapping("/addtowatchlist")
    public ResponseEntity<?> addStockstoWatchlist(@RequestParam("stockname") String stockname, @RequestParam("userId") Integer userId){
        watchlistService.addStocktoWatchlist(stockname,userService.findUserById(userId));
        System.out.println("were are working");
        return ResponseEntity.ok(200);
   }

    @PostMapping("/removefromwatchlist")
    public ResponseEntity<?> removeStockFromWatchlist(@RequestParam("stockname") String stockname, @RequestParam("userId") Integer userId) {
        watchlistService.removeStocksFromWatchlist(stockname, userService.findUserById(userId));
        return ResponseEntity.ok(200);
    }
    }
