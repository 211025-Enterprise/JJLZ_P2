package com.revature.JJLZ.service;

import com.revature.JJLZ.model.StockWrapper;
import com.revature.JJLZ.model.Stocks;
import com.revature.JJLZ.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

@SpringBootTest
class StockServiceTest {
    @Autowired
    private StockService stockService;


    @Test
    void invoke() throws IOException {
        final Stocks stock = stockService.findStock("AMZN");
        System.out.println();

        final BigDecimal price = stockService.findPrice(stock);
        System.out.println("current price "+price);

        final BigDecimal change = stockService.findPercentChange(stock);
        System.out.println("change in percentage "+change+"%");

        final BigDecimal percent200dma = stockService.findChangein200MAPercent(stock);
        System.out.println("change in 200 day moving average "+percent200dma+"%");

    }
}