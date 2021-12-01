package com.revature.JJLZ.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import yahoofinance.Stock;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    public String firstName;

    public String lastName;
    @Column(unique = true)
    public String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private double balance;

    public List<Stock> holding;

    @OneToMany(mappedBy = "watcher")
    @JsonManagedReference
    public List<StockWatchlist> userWatchList;

}