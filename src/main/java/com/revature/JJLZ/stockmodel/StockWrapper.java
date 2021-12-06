package com.revature.JJLZ.stockmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;
@Getter
@With
@AllArgsConstructor
public class StockWrapper {
    private final Stock stock;
    private final LocalDateTime lastAccessed;

    public StockWrapper(final Stock stock){
        this.stock = stock;
        lastAccessed = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return
                "stock=" + stock ;
    }
}
