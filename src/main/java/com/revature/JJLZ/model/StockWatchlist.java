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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_name")
    public String stockName;

    @ManyToOne( cascade = {CascadeType.PERSIST,CascadeType.MERGE,
          CascadeType.DETACH, CascadeType.REFRESH})
    @JsonBackReference
    public User watcher;
}
