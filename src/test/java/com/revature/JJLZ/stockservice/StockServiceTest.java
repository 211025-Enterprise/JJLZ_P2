package com.revature.JJLZ.stockservice;

import com.revature.JJLZ.service.StockService;
import com.revature.JJLZ.stockmodel.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StockServiceTest {
    @Autowired
    private StockService stockService;

    @Test
    void invoke() throws IOException {
        final StockWrapper stock = stockService.findStock("AMZN");
        System.out.println(stock.getStock());

        final BigDecimal price = stockService.findPrice(stock);
        System.out.println("current price "+price);

//        final BigDecimal change = stockService.findPercentChange(stock);
//        System.out.println("change in percentage "+change+"%");
//
//        final BigDecimal percent200dma = stockService.findChangein200MAPercent(stock);
//        System.out.println("change in 200 day moving average "+percent200dma+"%");

        List<String> stockList = new ArrayList<String>();
        stockList.add("AMZN");
        stockList.add("WMT");
        stockList.add("TSLA");
        stockList.add("SPY");
        stockList.add("QQQ");
        final List<StockWrapper> allstocks = stockService.findStocks(stockList);
        System.out.println(allstocks.toString());
    }




}
