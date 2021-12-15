package com.revature.JJLZ.service;


import com.revature.JJLZ.model.StockWatchlist;
import com.revature.JJLZ.model.StockWatchlist;
import com.revature.JJLZ.model.StockWatchlist;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.StockRepository;
import com.revature.JJLZ.repository.UserRepository;
import com.revature.JJLZ.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WatchlistService {
//    public User user;
//    public StockWatchlist watchlist;

    private WatchlistRepository watchlistRepository;
    @Autowired
    private UserRepository userRepository;

    public WatchlistService(WatchlistRepository watchlistRepository){this.watchlistRepository = watchlistRepository;}

    public StockWatchlist findStock(final String ticker) {
        try {
            //System.out.println(new Stocks(YahooFinance.get(ticker)));
            return new StockWatchlist(YahooFinance.get(ticker));
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
    public List<StockWatchlist> getAllStocksByWatcher(Integer userId){
        return watchlistRepository.findAllByWatcher_UserId(userId);
    }
    public List<StockWatchlist> getAllStocksByWatcher(){
        return watchlistRepository.findAllByWatcher_UserId(1);
    }
    public BigDecimal findPrice(final StockWatchlist stock) throws IOException {
        return getStockByName(stock.getStockName()).getQuote().getPrice();
    }
    public BigDecimal findPercentChange(final StockWatchlist stock) throws IOException{
        return getStockByName(stock.getStockName()).getQuote().getChangeInPercent();
    }
    //finds list of stocks
    public List<StockWatchlist> findStocks(final List<String> tickers) {
        return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
        //iterate through given tickers, map them to a stock(pass ticker through
        //findStock return new stockwrapper then filtered if null and whats left collected to a list
    }


    public StockWatchlist createNewStockHolding(StockWatchlist watchlist){
        return watchlistRepository.save(watchlist);
    }

    public void addStocktoWatchlist(String stockname, User user){
        System.out.println("inside the method");
        StockWatchlist watchlist = findStock(stockname);
        watchlist.setWatcher(user);
        System.out.println("after watchlist ="+watchlist);
        user.userWatchList.add(watchlist);
        System.out.println("after add");
        watchlistRepository.save(watchlist);
        System.out.println("after save");

    }

    public void removeStocksFromWatchlist(String stockname, User user){
        StockWatchlist watchlist = findStock(stockname);
        watchlist.setWatcher(user);
        watchlistRepository.delete(watchlist);
        user.userWatchList.remove(watchlist);

    }



}
