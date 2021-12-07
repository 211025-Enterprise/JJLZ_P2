package com.revature.JJLZ.service;

import com.revature.JJLZ.model.StockWrapper;
import com.revature.JJLZ.model.Stocks;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.StockRepository;

import com.revature.JJLZ.repository.UserRepository;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;



@Service
public class StockService {

    private StockRepository stockRepository;
    @Autowired
    private UserRepository userRepository;

    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    public Stocks findStock(final String ticker) {
        try {
            //System.out.println(new Stocks(YahooFinance.get(ticker)));
            return new Stocks(YahooFinance.get(ticker));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Stock getStockByName(String name){
        try {
            return YahooFinance.get(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //finds list of stocks
    public List<Stocks> findStocks(final List<String> tickers) {
        return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
        //iterate through given tickers, map them to a stock(pass ticker through
        //findStock return new stockwrapper then filtered if null and whats left collected to a list
    }

    public List<Stocks> getAllStocksByUser(Integer userId){
        return stockRepository.findAllByHolder_UserId(userId);
    }

    public BigDecimal findPrice(final Stocks stock) throws IOException {
        return getStockByName(stock.getName()).getQuote().getPrice();
    }
    public BigDecimal findPercentChange(final Stocks stock) throws IOException{
        return getStockByName(stock.getName()).getQuote().getChangeInPercent();
    }
    public BigDecimal findChangein200MAPercent(final Stocks stock) throws IOException{
        return getStockByName(stock.getName()).getQuote().getChangeFromAvg200InPercent();
    }



    public Stocks createNewStockHolding(Stocks stocks){
        return stockRepository.save(stocks);
    }


    public void buyStock(String stockname, int amount, User user) throws IOException {
        Stocks stock = findStock(stockname);
        BigDecimal stockP = findPrice(stock);
        stockP = stockP.setScale(2, RoundingMode.CEILING);
        double stockPrice = stockP.doubleValue();
        stockPrice = stockPrice * amount;
        stock.setHolder(user);
        if (stockPrice <= user.getBalance()) {
            user.setBalance(user.getBalance()-stockPrice);
            System.out.println(user.getBalance());
            //if not have add to list
            List<Stocks> stonks;
            stonks = getAllStocksByUser(user.getUserId());
            if (stonks.contains(stock)){
                stock.setQuantity(stock.getQuantity()+amount);
                //System.out.println(stock.getQuantity()+"   "+stock.getHolder());

//                System.out.println("new balance: " + user.getBalance());
//                System.out.println(user.getFirstName()+"bought: " + amount + " of stock: "+  stock);
            }
            else{
                user.holding.add(stock);
                stock.setQuantity(amount);
//                System.out.println(stock.getQuantity()+" asdasd  "+stock.getHolder());
//                System.out.println(user.getFirstName()+"bought: " + amount + " of stock: "+  stock);
            }
            stockRepository.save(stock);
        }
        else{
            System.out.println("insufficient funds.");
        }
    }
    public void sellStock(String stockname, int amount, User user) throws IOException {
        Stocks stock = findStock(stockname);
        BigDecimal stockP = findPrice(stock);
        stockP = stockP.setScale(2, RoundingMode.CEILING);
        double stockPrice = stockP.doubleValue();
        stockPrice = stockPrice * amount;
        stock.setHolder(user);
        //if last of stock remove from list
        // make a list of all the stocks the user is holding
        List<Stocks> stonks;
        stonks = getAllStocksByUser(user.getUserId());
        // if user holds the stock they want to sell
        System.out.println(Arrays.toString(stonks.toArray()));
        for (Stocks st : stonks) {
            //System.out.println(st.getName() + "   "+ stockname);
            if (st.getName().equals(stockname)) {
                System.out.println("we're in");

                // if user has >= amount of stock remove the amount from the quantity
                if (st.getQuantity() >= amount) {
                    //System.out.println("quantity: "+st.getQuantity());
                    st.setQuantity(st.getQuantity() - amount);
                    //System.out.println("currently have left: "+st.getQuantity());
                    user.setBalance(user.getBalance() + stockPrice);
                    System.out.println("Current balance: "+user.getBalance());
                    stockRepository.save(st);
                }
                // the user wants to sell more than they have, dont let them smh
                else {
                    System.out.print("Dont have enough of that stock to sell, current amount: ");
                    System.out.println(st.getQuantity());
                }
                if (st.getQuantity() == 0) {
                    user.holding.remove(st);
                    stockRepository.delete(st);
                }

            }
            // if the user doesnt own any of that stock
            else {
                System.out.println("You dont own this stock, cant sell what you dont have dummy");
            }
        }
    }

//    public boolean validateHolding(Stocks stocks){
//        //lookup
//        return stockRepository.findByName(stocks.getName()).getHolder().equals(stocks.getHolder());
//    }


}
