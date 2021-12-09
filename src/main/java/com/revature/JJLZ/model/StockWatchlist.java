package com.revature.JJLZ.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "watchlist")
public class StockWatchlist {

    @Column(name = "stock_name")
    public String stockName;

    @ManyToOne( cascade = CascadeType.ALL)
    @JsonBackReference
    public User watcher;

}
