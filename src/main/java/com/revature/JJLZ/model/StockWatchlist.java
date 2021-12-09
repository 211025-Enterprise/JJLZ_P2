package com.revature.JJLZ.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yahoofinance.Stock;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "watchlist")
public class StockWatchlist {
    @Id
    @Column(name = "stock_name")
    public String stockName;

    @ManyToOne( cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonBackReference
    public User watcher;

    public StockWatchlist(Stock stock) {
        stockName = stock.getSymbol();
    }
    public StockWatchlist(User user){
        this.watcher = user;
    }


    @Override
    public String toString() {
        return "StockWatchlist{" +
                "stockName='" + stockName + '\'' +
                ", watcher=" + watcher +
                '}';
    }

}
