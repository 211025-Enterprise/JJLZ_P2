package com.revature.JJLZ.service;

import com.revature.JJLZ.model.Stocks;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.StockRepository;
import com.revature.JJLZ.stockmodel.StockWrapper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;



@Service
public class StockService {
    //    private User user;
//    private final RefreshService refreshService;

//    public boolean addToHolder(Stocks stock){
//        return user.holding.add(stock);
//    }


    //--------------------------------------------------------------------------
    //finds 1 stock
    public StockWrapper findStock(final String ticker) {
        try {
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //finds list of stocks
    public List<StockWrapper> findStocks(final List<String> tickers) {
        return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
        //iterate through given tickers, map them to a stock(pass ticker through
        //findStock return new stockwrapper then filtered if null and whats left collected to a list
    }



    public BigDecimal findPrice(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote().getPrice();
    }
    public BigDecimal findPercentChange(final StockWrapper stock) throws IOException{
        return stock.getStock().getQuote().getChangeInPercent();
    }
    public BigDecimal findChangein200MAPercent(final StockWrapper stock) throws IOException{
        return stock.getStock().getQuote().getChangeFromAvg200InPercent();
    }

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    public Stocks createNewStockHolding(Stocks stocks){
        return stockRepository.save(stocks);
    }


    public void buyStock(BigDecimal stockP, int amount, User user){
        stockP = stockP.setScale(2, RoundingMode.CEILING);
        double stockPrice = stockP.doubleValue();
        stockPrice = stockPrice * amount;
        if (stockPrice <= user.getBalance()) {
            user.setBalance(user.getBalance()-stockPrice);
            System.out.println(user.getBalance());
            //if not have add to list
        }
        else{
            System.out.println("insufficient funds.");
        }
    }
    public void sellStock(BigDecimal stockP, int amount, User user){
        stockP = stockP.setScale(2, RoundingMode.CEILING);
        double stockPrice = stockP.doubleValue();
        stockPrice = stockPrice * amount;
        user.setBalance(user.getBalance()+stockPrice);
        System.out.println(user.getBalance());
        //if last of stock remove from list
    }

//    public boolean validateHolding(Stocks stocks){
//        //lookup
//        return stockRepository.findByName(stocks.getName()).getHolder().equals(stocks.getHolder());
//    }


}
