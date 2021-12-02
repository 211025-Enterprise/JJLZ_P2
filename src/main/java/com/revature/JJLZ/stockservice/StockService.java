package com.revature.JJLZ.stockservice;
import com.revature.JJLZ.model.Stocks;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.stockmodel.StockWrapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class StockService {
//    private User user;
    private final RefreshService refreshService;

//    public boolean addToHolder(Stocks stock){
//        return user.holding.add(stock);
//    }
    //finds 1 stock
    public StockWrapper findStock(final String ticker){
        try{
            return new StockWrapper(YahooFinance.get(ticker));
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    //finds list of stocks
    public List<StockWrapper> findStocks(final List<String> tickers){
        return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
        //iterate through given tickers, map them to a stock(pass ticker through
        //findStock return new stockwrapper then filtered if null and whats left collected to a list
    }

    //gets price of a stock
    public BigDecimal findPrice(final StockWrapper stock)throws IOException{
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getPrice();
    }

    //change in % of a stock
    public BigDecimal findPercentChange(final StockWrapper stock) throws IOException{
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeInPercent();
    }

    //gets the 200 day moving average change in a percentage most used indicator for sentiment
    public BigDecimal findChangein200MAPercent(final StockWrapper stock) throws IOException{
        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeFromAvg200InPercent();
    }


}
