package com.revature.JJLZ.service;


import com.revature.JJLZ.model.StockWrapper;
import com.revature.JJLZ.model.Stocks;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import yahoofinance.Stock;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    private StockService stockService;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createNewUser(User user){
        user.setUserId(0);
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Integer userId){
        return userRepository.findById(userId).orElse(null);
    }

    // checks if the user exists with that username, then compares the password to the password in the database
    public boolean validate(User user){
        return userRepository.findUserByUsername(user.getUsername()).getPassword().equals(user.getPassword());
    }

	public User getUserByUsername(User user) {
		return userRepository.findUserByUsername(user.getUsername());
	}

    public double totalBalance(User user) throws IOException {
        double total = user.getBalance();
//        get list of stocks user is holding
//        in for loop for each stock, find the quantity and price and multiply
//        for each price*quantity add the value to total
        List<Stocks> st;
        st = stockService.getAllStocksByUser(user.getUserId());

        for(Stocks stonk:st){
            System.out.println("in loop");
            System.out.println(stonk.toString());
            BigDecimal price = stockService.findPrice(stonk);
            price = price.setScale(2, RoundingMode.CEILING);
            double stockPrice = price.doubleValue();
            System.out.println("stock: "+ stonk+" price: "+ stockPrice);
            System.out.println("Quantity: "+stonk.getQuantity());
            stockPrice *= stonk.getQuantity();
            System.out.println("price*quantity: " +stockPrice);
            total += stockPrice;
            System.out.println(total);
        }
        return total;
    }
    /* display user firstname and lastname
    1. view total balance/value
    2. view current stocks user has(null if new account)
    3. search for stocks(already held or new) type in ticker name find the rest of the info
        3.1. name:
             price:
             percentfortheday: increase/decrease
    4. buy stocks
    4.1. if not have stock, add to holding list
    4.2. subtract value of stock*quantity from balance
    5. sell stocks
    5.1. remove quantity or the stock from the list of holding
    5.2. add price*quantity sold to balance
    6. logout
     */

	public String getFullName(User user) {
		StringBuffer s = new StringBuffer();
		s.append(userRepository.findUserByUsername(user.getUsername()).getFirstName());
		s.append(" ");
		s.append(userRepository.findUserByUsername(user.getUsername()).getLastName());
		return s.toString();
	}

    public User update(User user) {
        userRepository.save(user);
        return user;
    }

    public void delete(Integer userId){
        userRepository.deleteById(userId);
    }

}
