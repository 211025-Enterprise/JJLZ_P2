package com.revature.JJLZ.stockservice;

import com.revature.JJLZ.stockmodel.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceTest {
    @Autowired
    private StockService stockService;

    @Test
    void invoke(){
        final StockWrapper stock = stockService.findStock("AMZN");
        System.out.println(stock.getStock());
    }
}